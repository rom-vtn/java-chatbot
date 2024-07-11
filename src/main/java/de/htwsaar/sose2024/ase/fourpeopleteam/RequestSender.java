package de.htwsaar.sose2024.ase.fourpeopleteam;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/** The class RequestSender  manages the sending of HTTP requests to a server
 *  to obtain responses for the chatbot's conversation. */
public class RequestSender {
  private String baseUrl;
  public static int MAX_ATTEMPTS = 3;

  public RequestSender(String baseUrl) {
    this.baseUrl = baseUrl;
  }

  /** Requests the next message in a conversation to the server and returns the new message. */
  public Conversation.Message requestNextMessage(Conversation conversation)
      throws ChatbotException {
    String requestBody = encodeRequest(conversation);
    String responseBody = null;
    int i;
    for (i = 0; i < MAX_ATTEMPTS; i++) {
      try {
        responseBody = attemptSendingMessage(requestBody);
        break;
      } catch (URISyntaxException e) {
        throw new RuntimeException(e);
      } catch (IOException | InterruptedException e) {
        //do nothing, try again
      }
    }

    if (i == MAX_ATTEMPTS) {
      throw new ChatbotException("request limit exceeded");
    }

    Conversation.Message message = decodeResponse(responseBody);
    return message;
  }

  /** Decodes a String response from the server into a Conversation.Message object.
   *
   * @param response The JSON response string
   * @return The new message returned by the server
   * @throws ChatbotException if something went wrong
   */
  private Conversation.Message decodeResponse(String response)
      throws ChatbotException {
    try {
      //cast to JSON
      JSONObject jsonResponse = new JSONObject(response);
      //pick from the response choices
      JSONArray choices = jsonResponse.optJSONArray("choices");
      if (choices == null) {
        throw new ChatbotException("null choices");
      }
      //get first choice
      JSONObject firstChoice = choices.getJSONObject(0);
      if (firstChoice == null) {
        throw new ChatbotException("null first choice");
      }
      JSONObject jsonMessage = firstChoice.getJSONObject("message");
      if (jsonMessage == null) {
        throw new ChatbotException("null json message");
      }
      //then convert to Conversation.Message
      return Conversation.Message.fromJsonObject(jsonMessage);
    } catch (JSONException e) {
      throw new ChatbotException(e);
    }
  }

  /** Encodes a conversation into a JSON request String which can be sent to the server.
   *
   * @param conversation the conversation to be encoded
   * @return the JSON request to be sent to the server
   */
  private String encodeRequest(Conversation conversation) {
    JSONObject conv = new JSONObject();
    conv.put("messages", conversation.toJson());
    conv.put("max_tokens", 256);
    return conv.toString();
  }

  /** Attempts to send a (string) message to the server's endpoint
   * and returns the response body String.
   *
   * @param bodyString the string to be sent as a request body
   * @return the response body
   */
  private String attemptSendingMessage(String bodyString)
      throws URISyntaxException, IOException, InterruptedException {
    BodyPublisher bp = BodyPublishers.ofString(bodyString);

    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request = HttpRequest
        .newBuilder(new URI(baseUrl + "/v1/chat/completions")).POST(bp).build();

    //NOTE: synchronous
    HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

    return response.body();
  }
}

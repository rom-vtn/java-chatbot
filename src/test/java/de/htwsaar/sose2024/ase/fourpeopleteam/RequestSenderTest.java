package de.htwsaar.sose2024.ase.fourpeopleteam;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

/** Tests for the RequestSender. */
public class RequestSenderTest {
  @Test
  public void decodeTest() throws ChatbotException {
    // source: a JSON body returned by the server
    String sampleResponse = "{\"id\":\"chatcmpl-a9a8ba0e-ad5d-44f6-ac83-dda6bed596cf\","
        + "\"object\":\"chat.completion\",\"created\":1720696512,"
        + "\"model\":\"/models/llama-2-7b-chat.bin\",\"choices\":"
        + "[{\"index\":0,\"message\":{\"role\":\"assistant\",\"content\":"
        + "\"Greetings, how may I assist you today?\"},\"finish_reason\":\"stop\"}]"
        + ",\"usage\":{\"prompt_tokens\":32,\"completion_tokens\":11,\"total_tokens\":43}}";
    
    Conversation.Message expectedMsg = Conversation.Message.makeAssistantMessage(
        "Greetings, how may I assist you today?");
    Conversation.Message actualMsg = RequestSender.decodeResponse(sampleResponse);

    assertEquals(expectedMsg, actualMsg);

  }

  @Test
  public void encodeRequestTest() throws ChatbotException {
    Conversation conversation = Conversation.makeStandardConversation();
    conversation.add(Conversation.Message.makeUserMessage("test content"));

    String encoded = RequestSender.encodeRequest(conversation);

    JSONObject decoded = new JSONObject(encoded);
    JSONArray messages = decoded.getJSONArray("messages");

    Conversation recoded = Conversation.fromJsonArray(messages);

    assertEquals(conversation, recoded);

  }

  @Test
  public void testRequestNextMessage() throws Exception {

    JSONObject jsonMessage = new JSONObject();
    jsonMessage.put("role", "assistant");
    jsonMessage.put("content", "Hello");

    JSONObject firstChoice = new JSONObject();
    firstChoice.put("message", jsonMessage);

    JSONObject jsonResponse = new JSONObject();
    jsonResponse.put("choices", new JSONArray().put(firstChoice));

    RequestSender realRequestSender = new RequestSender("http://example.com");
    RequestSender spyRequestSender = spy(realRequestSender);
    doReturn(jsonResponse.toString())
        .when(spyRequestSender)
        .attemptSendingMessageTo(anyString(), anyString());

    Conversation conversation = Conversation.makeStandardConversation();
    Conversation.Message expectedMessage = Conversation.Message.makeAssistantMessage("Hello");

    Conversation.Message actualMessage = spyRequestSender.requestNextMessage(conversation);

    assertEquals(expectedMessage, actualMessage);
  }
}
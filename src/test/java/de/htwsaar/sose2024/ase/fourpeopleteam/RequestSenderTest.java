package de.htwsaar.sose2024.ase.fourpeopleteam;

import static org.junit.Assert.assertEquals;

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
}
package de.htwsaar.sose2024.ase.fourpeopleteam;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

/** Testing the conversation. */
public class ConversationTest {
  //first, test Conversation.Message

  @Test
  public void messageEqualityTest() {
    Conversation.Message baseMsg = new Conversation.Message(
        "system",
        "A sample message.");
    Conversation.Message identicalBaseMsg = new Conversation.Message(
        "system",
        "A sample message.");
    Conversation.Message changedContentMsg = new Conversation.Message(
        "system",
        "A different message.");
    Conversation.Message changedRoleMsg = new Conversation.Message(
        "user",
        "A sample message.");
    
    assertEquals(baseMsg, identicalBaseMsg);
    assertNotEquals(baseMsg, changedContentMsg);
    assertNotEquals(baseMsg, changedRoleMsg);
  }

  @Test
  public void messageInnerTest() {
    String role = "system";
    String content = "a sample message.";
    Conversation.Message msg = new Conversation.Message(role, content);
    
    assertTrue(role.equals(msg.getRole()));
    assertTrue(content.equals(msg.getContent()));
  }

  @Test
  public void messageJsonTest() throws ChatbotException {
    Conversation.Message msg = Conversation.Message.makeAssistantMessage("test assistant message.");
    JSONObject asJson = msg.toJson();
    String asString = asJson.toString();
    JSONObject reconstructedJson = new JSONObject(asString);
    Conversation.Message reconstructed = Conversation.Message.fromJsonObject(reconstructedJson);
    assertEquals(msg, reconstructed);
  }

  @Test
  public void conversationEqualityTest() {
    Conversation c1 = new Conversation();
    Conversation c2 = new Conversation();
    Conversation.Message msg = Conversation.Message.makeUserMessage("sample user message content.");
    assertEquals(c1, c2);
    c1.add(msg);
    assertNotEquals(c1, c2);
    c2.add(msg);
    assertEquals(c1, c2);
  }

  @Test
  public void conversationJsonTest() throws ChatbotException {
    Conversation conversation = Conversation.makeStandardConversation();
    Conversation.Message msg = Conversation.Message.makeUserMessage("custom user string.");
    conversation.add(msg);
    JSONArray asJson = conversation.toJson();
    System.out.println(asJson);
    Conversation reconstructed = Conversation.fromJsonArray(asJson);
    assertEquals(conversation, reconstructed);
  }

  //TODO make sure Conversation is tested appropriately
}
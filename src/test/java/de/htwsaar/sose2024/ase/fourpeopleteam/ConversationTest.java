package de.htwsaar.sose2024.ase.fourpeopleteam;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
    
    assertTrue(baseMsg.equals(identicalBaseMsg));
    assertFalse(baseMsg.equals(changedContentMsg));
    assertFalse(baseMsg.equals(changedRoleMsg));
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
  public void messageJsonTest() {
    //TODO convert to JSON and back
  }
}
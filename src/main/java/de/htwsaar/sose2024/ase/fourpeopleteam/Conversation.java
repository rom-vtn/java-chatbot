package de.htwsaar.sose2024.ase.fourpeopleteam;

import java.util.ArrayList;
import java.util.Objects;

import org.json.JSONArray;
import org.json.JSONObject;

/** TODO docs. */
public class Conversation {
  private ArrayList<Message> messages;

  public Conversation() {
    messages = new ArrayList<>();
  }

  /** TODO docs. */
  public static Conversation makeStandardConversation() {
    Conversation conv = new Conversation();
    Message firstMessage = new Message(
        "system",
        "You are a very helpful AI assistant. Please respond very concisely.");

    conv.add(firstMessage);

    return conv;
  }

  /** TODO docs. */
  public void add(Message message) {
    messages.add(message);
  }

  /** TODO docs. */
  public JSONArray toJson() {
    JSONArray arr = new JSONArray();
    for (Message m : messages) {
      arr.put(m.toJson());
    }
    return arr;
  }

  /** TODO docs. */
  public static class Message {
    private String role;
    private String content;

    public Message(String role, String content) {
      this.role = role;
      this.content = content;
    }

    /** TODO docs. */
    public static Message makeUserMessage(String content) {
      return new Message("user", content);
    }

    /** TODO docs. */
    public static Message makeAssistantMessage(String content) {
      return new Message("assistant", content);
    } 

    /** TODO docs. */
    public JSONObject toJson() {
      JSONObject obj = new JSONObject();
      obj.put("role", this.role);
      obj.put("content", this.content);
      return obj;
    }

    /** TODO docs. */
    public String getRole() {
      return role;
    }

    /** TODO docs. */
    public String getContent() {
      return content;
    }

    @Override
    public boolean equals(Object other) {
      if (!(other instanceof Message)) {
        return false;
      }
      Message otherMessage = (Message) other;
      return otherMessage.getRole().equals(role)
        && otherMessage.getContent().equals(content);
    }

    @Override
    public int hashCode() {
      return Objects.hash(this.role, this.content);
    }
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Conversation)) {
      return false;
    }
    Conversation otherConv = (Conversation) other;
    if (messages.size() != otherConv.messages.size()) {
      return false;
    }
    for (int i = 0; i < messages.size(); i++) {
      Message ourMessage = messages.get(i);
      Message theirMessage = otherConv.messages.get(i);
      if (!ourMessage.equals(theirMessage)) {
        return false;
      }
    }
    return true;
  }

  @Override
  public int hashCode() {
    return this.messages.hashCode();
  }
}

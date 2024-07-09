package de.htwsaar.sose2024.ase.fourpeopleteam;

import java.util.ArrayList;
import java.util.Objects;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Represents a conversation consisting of messages exchanged between different roles
 * (the User and the assistant).
 */
public class Conversation {
  private ArrayList<Message> messages;

  public Conversation() {
    messages = new ArrayList<>();
  }

  /**
   * Creates a standard conversation with an initial system message.
   *
   * @return new conversation with a predefined message
   */
  public static Conversation makeStandardConversation() {
    Conversation conv = new Conversation();
    Message firstMessage = new Message(
        "system",
        "You are a very helpful AI assistant. Please respond very concisely.");

    conv.add(firstMessage);

    return conv;
  }

  /**
   * Add a message to the conversation.
   *
   * @param message to be added.
   */
  public void add(Message message) {
    messages.add(message);
  }

  /**
   * converts the conversation to a JSON array representing its messages.
   *
   * @return the JSON array
   */
  public JSONArray toJson() {
    JSONArray arr = new JSONArray();
    for (Message m : messages) {
      arr.put(m.toJson());
    }
    return arr;
  }

  /** The Message class represents a single message in the conversation,
   * including its role and content.
   */
  public static class Message {
    private String role;
    private String content;

    public Message(String role, String content) {
      this.role = role;
      this.content = content;
    }

    /** 
     * Creates a user message with the specified content.
     *
     * @param content the content of the user message
     * @return a new Message with the role "user"
     */
    public static Message makeUserMessage(String content) {
      return new Message("user", content);
    }

    /** 
     * Creates an assistant message with the specified content.
     *
     * @param content the content of the assistant message
     * @return a new Message with the role "assistant"
     */
    public static Message makeAssistantMessage(String content) {
      return new Message("assistant", content);
    }

    /**
     * Creates a message from the given JSON object.
     *
     * @param jsonMessage The JSON Object representing the message to decode.
     * @return the message contained in the response
     */
    public static Message fromJsonObject(JSONObject jsonMessage) throws ChatbotException {
      String content = jsonMessage.getString("content");
      if (content == null) {
        throw new ChatbotException("null message content");
      }
      String role = jsonMessage.getString("role");
      if (role == null) {
        throw new ChatbotException("null role for message");
      }
      
      return new Message(role, content);
    }

    /**
     * Converts the message to a JSON object representing its attributes.
     *
     * @return the JSON object representing the message
     */
    public JSONObject toJson() {
      JSONObject obj = new JSONObject();
      obj.put("role", this.role);
      obj.put("content", this.content);
      return obj;
    }

    /** 
     * Gets the role of the sender of the message.
     *
     * @return the role of the sender
     */
    public String getRole() {
      return role;
    }

    /** 
     * Gets the content of the message.
     *
     * @return the content of the sender
     */
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

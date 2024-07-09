package de.htwsaar.sose2024.ase.fourpeopleteam;

/** Represents a chatbot exception. */
public class ChatbotException extends Exception {
  public ChatbotException(Throwable cause) {
    super(cause);
  }

  public ChatbotException(String reason) {
    super(reason);
  }
}

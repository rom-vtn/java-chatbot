package de.htwsaar.sose2024.ase.fourpeopleteam;

/** Represents a chatbot that combines a user interface,
 * a stored conversation and a request sender.
 */
public class Chatbot {
  private UserInterface userInterface;
  private Conversation conversation;
  private RequestSender requestSender;
  
  /**
   * Constructs a chatbot using the given userInterface and requestSender,
   * as well as a new empty conversation.
   *
   * @param userInterface the user interface to be used
   * @param requestSender the request sender to be used
   */
  public Chatbot(UserInterface userInterface, RequestSender requestSender) {
    this.userInterface = userInterface;
    this.requestSender = requestSender;
    this.conversation = Conversation.makeStandardConversation();
  }

  /**
   * Runs a conversation round (ask for message, send, recieve and display response).
   *
   * @return whether the conversation should keep going (true) or exit (false)
   */
  public boolean conversationRound() {
    String input = userInterface.getInput();
    Conversation.Message userMessage = Conversation.Message.makeUserMessage(input);
    conversation.add(userMessage);
    Conversation.Message response = null;
    try {
      response = requestSender.requestNextMessage(conversation);
    } catch (Exception e) {
      return false;
    }
    conversation.add(response);
    userInterface.show(response.getContent());
    return true;
  }
}

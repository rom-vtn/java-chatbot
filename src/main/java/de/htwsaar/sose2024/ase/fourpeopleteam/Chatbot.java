package de.htwsaar.sose2024.ase.fourpeopleteam;

/** TODO add docs. */
public class Chatbot {
  private UserInterface userInterface;
  private Conversation conversation;
  private RequestSender requestSender;
  
  /** TODO: add docs. */
  public Chatbot(UserInterface userInterface, RequestSender requestSender) {
    this.userInterface = userInterface;
    this.requestSender = requestSender;
    this.conversation = Conversation.makeStandardConversation();
  }

  /** TODO docs. */
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

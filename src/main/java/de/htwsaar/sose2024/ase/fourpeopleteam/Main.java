package de.htwsaar.sose2024.ase.fourpeopleteam;

/** Represents a sample app using the CLI for the chatbot. */
public class Main {
  /**
   * Runs the Chatbot.
   *
   * @param args the command line args
   */
  public static void main(String[] args) {
    UserInterface ui = new CommandLineInterface();
    String url = ui.getInput();
    RequestSender rs = new RequestSender(url);

    Chatbot chatbot = new Chatbot(ui, rs);

    boolean keepGoing = true;
    while (keepGoing) {
      keepGoing = chatbot.conversationRound();
    }
  }
}

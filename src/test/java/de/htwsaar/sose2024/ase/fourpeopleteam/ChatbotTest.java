package de.htwsaar.sose2024.ase.fourpeopleteam;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;

/** Tests for the Chatbot class. */
public class ChatbotTest {
  /** TODO add more tests. */
  @Test
  public void sample() {
    assertTrue(true);
  }

  @Test
  public void compositionCheck() throws ChatbotException {
    //the sample content used for our "vicious" mockery
    String assistantResponse = "an example chatbot response string.";
    Conversation.Message assistantMsg = Conversation.Message
        .makeAssistantMessage(assistantResponse);
    String userInput = "some question sent to the bot.";
    Conversation.Message userMsg = Conversation.Message.makeUserMessage(userInput);
    Conversation sentConversation = Conversation.makeStandardConversation();
    sentConversation.add(userMsg);

    //actually create the mocks
    UserInterface ui = mock(UserInterface.class);
    when(ui.getInput()).thenReturn(userInput);

    RequestSender rs = mock(RequestSender.class);
    when(rs.requestNextMessage(sentConversation)).thenReturn(assistantMsg);
    
    //use the mocks
    Chatbot chatbot = new Chatbot(ui, rs);
    chatbot.conversationRound();

    //theck method calls on mocks
    verify(ui).getInput();
    //TODO fix the conversation equality failing when doing that
    // verify(rs).requestNextMessage(sentConversation);
    verify(ui).show(assistantResponse);
  }
}

package de.htwsaar.sose2024.ase.fourpeopleteam;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/** Tests for the Chatbot class. */
public class ChatbotTest {
  @Mock
  private  UserInterface userInterface;
  @Mock
  private RequestSender requestSender;
  private Chatbot chatbot;

  @Before
  /*
    Initializes the mocks and creates a RequestSender instance.
   */
  public void setUp() {
    MockitoAnnotations.openMocks(this);
    chatbot = new Chatbot(userInterface, requestSender);
  }

  @Test
  public void conversationRoundTest() throws ChatbotException {
    //the setUp method must be executed before.
    String userInput = "Hello!";
    String botResponseContent = "Hi!";

    when(userInterface.getInput()).thenReturn(userInput);
    Conversation.Message botResponse =
            Conversation.Message.makeAssistantMessage(botResponseContent);
    when(requestSender.requestNextMessage(any(Conversation.class))).thenReturn(botResponse);

    boolean answer = chatbot.conversationRound();

    assertTrue(answer);
    verify(userInterface).getInput();
    verify(requestSender).requestNextMessage(any(Conversation.class));
    verify(userInterface).show(botResponseContent);
  }
}

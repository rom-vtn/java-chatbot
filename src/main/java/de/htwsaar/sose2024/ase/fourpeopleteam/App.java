package de.htwsaar.sose2024.ase.fourpeopleteam;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        UserInterface ui = new CommandLineInterface();
        RequestSender rs = new RequestSender("https://gptapi.oc.romaneviton.fr");
        Chatbot chatbot = new Chatbot(ui, rs);
        while (chatbot.conversationRound()) {
        }
        System.out.println("Done!!");
    }

    public static int add(int a, int b) {
        return a+b;
    }

    public static int throwError() {
        throw null;
    }
}

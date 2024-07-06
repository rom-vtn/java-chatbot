package de.htwsaar.sose2024.ase.fourpeopleteam;

/**
 * It is an interface for defining a user interface that interacts with a chatbot application openAI.
 * An implementation of this interface should provide the methods show(displays a specific content to the user)
 * and getInput(retrieves input from the user)
 */
public interface UserInterface {


  void show(String content);

  
  String getInput();

}
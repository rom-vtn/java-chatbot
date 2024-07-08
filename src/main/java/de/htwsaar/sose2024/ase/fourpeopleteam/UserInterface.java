package de.htwsaar.sose2024.ase.fourpeopleteam;

/**
 * An interface that interacts with a chatbot application.
 * An implementation of this interface should provide the following methods:
 */
public interface UserInterface {

  /**
   * Dispays the given string to the interface.
   *
   * @param content the content to be displayed
   */
  void show(String content);

  /**
   * Asks the user for input and returns it.
   *
   * @return the string typed by the user
   */
  String getInput();

}
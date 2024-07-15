package de.htwsaar.sose2024.ase.fourpeopleteam;

/**
 * An interface that interacts with a chatbot application.
 * An implementation of this interface should provide the following methods:
 */
public interface UserInterface {

  /**
   * Displays the given string to the interface.
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

  /**
   * Shows a prompt and gets input. Is equivalent to show(prompt) followed by getInput().
   *
   * @param prompt the prompt to be shown
   * @return the string typed by the user
   */
  default String getInput(String prompt) {
    show(prompt);
    return getInput();
  }
}
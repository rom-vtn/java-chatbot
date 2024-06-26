package de.htwsaar.sose2024.ase.fourpeopleteam;

import java.util.Scanner;

/** TODO docs. */
public class CommandLineInterface implements UserInterface {
  private Scanner scanner;

  /** TODO docs. */
  @Override
  public void show(String content) {
    System.out.println(content);
  }

  /** TODO docs. */
  @Override
  public String getInput() {
    System.out.print("> ");
    return scanner.nextLine();
  }

  /** TODO docs. */
  public CommandLineInterface() {
    scanner = new Scanner(System.in, "UTF_8");
  }
}

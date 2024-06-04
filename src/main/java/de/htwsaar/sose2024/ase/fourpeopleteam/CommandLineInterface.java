package de.htwsaar.sose2024.ase.fourpeopleteam;

import java.util.Scanner;

public class CommandLineInterface implements UserInterface {
    private Scanner scanner;

    public void show(String content) {
        System.out.println(content);
    }

    public String getInput() {
        System.out.print("> ");
        return scanner.nextLine();
    }

    public CommandLineInterface() {
        scanner = new Scanner(System.in);
    }
}

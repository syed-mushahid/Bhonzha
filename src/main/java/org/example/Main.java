package org.example;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameController gameController = GameController.getInstance();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Bohnanza!");

        System.out.print("Enter number of players: ");
        int numPlayers = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < numPlayers; i++) {
            gameController.addPlayer(new Player());
        }

        gameController.startGame();

        while (!gameController.isGameOver()) {
            gameController.playRound(scanner);
            System.out.print("Continue to next round? (yes/no): ");
            String continueGame = scanner.nextLine();
            if (!continueGame.equalsIgnoreCase("yes")) {
                break;
            }
        }

        Player winner = gameController.determineWinner();
        System.out.println("Game over!");
        System.out.println("The winner is: " + winner + " with " + winner.getCoins() + " coins.");

        scanner.close();
    }
}


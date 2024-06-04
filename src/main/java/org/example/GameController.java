package org.example;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameController {
    private static GameController instance;
    private List<Player> players;
    private Deck deck;
    private List<Card> discardPile;

    private static final int MAX_RESHUFFLES = 3;

    private GameController() {
        players = new ArrayList<>();
        deck = new Deck();
        discardPile = new ArrayList<>();
    }

    public static synchronized GameController getInstance() {
        if (instance == null) {
            instance = new GameController();
        }
        return instance;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Deck getDeck() {
        return deck;
    }

    public List<Card> getDiscardPile() {
        return discardPile;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void startGame() {
        for (Player player : players) {
            player.drawCards(deck, 5);  // Draw initial hand of 5 cards
        }
    }

    public void playRound(Scanner scanner) {
        for (Player player : players) {
            System.out.println("\nPlayer's turn:");
            System.out.println(player);

            // Draw phase
            player.drawCards(deck, 2);
            System.out.println("You drew 2 cards. Your hand: " + player.getHand());

            // Planting phase
            System.out.println("You must plant the first card in your hand.");
            player.plantFirstCard(scanner);
            System.out.println("Planted first card. Fields: " + player.getFields());

            // Trading phase
            System.out.println("Trading phase:");
            player.performTrade(scanner, players);

            // Planting and harvesting phase
            System.out.println("Planting traded cards and harvesting.");
            player.plantTradedCards(scanner);
            player.harvest(scanner);
            System.out.println("Harvested. Fields: " + player.getFields());

            // Draw new cards phase
            player.drawCards(deck, 3);
            System.out.println("You drew 3 cards. Your hand: " + player.getHand());

            System.out.println("End of turn for this player. Current state: " + player);
        }
    }

    public boolean isGameOver() {
        return deck.getReshuffles() >= MAX_RESHUFFLES;
    }

    public Player determineWinner() {
        Player winner = null;
        int maxCoins = 0;
        for (Player player : players) {
            if (player.getCoins() > maxCoins) {
                maxCoins = player.getCoins();
                winner = player;
            }
        }
        return winner;
    }
}


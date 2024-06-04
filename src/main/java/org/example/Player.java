package org.example;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


public class Player {
    private List<Card> hand;
    private List<Field> fields;
    private int coins;

    public Player() {
        hand = new ArrayList<>();
        fields = new ArrayList<>();
        fields.add(new Field());
        fields.add(new Field());
        coins = 0;
    }

    public void drawCards(Deck deck, int number) {
        for (int i = 0; i < number; i++) {
            Card card = deck.draw();
            if (card != null) {
                hand.add(card);
            }
        }
    }

    public void plantFirstCard(Scanner scanner) {
        if (!hand.isEmpty()) {
            System.out.println("Your hand: " + hand);
            System.out.println("Choose a field to plant the first card (0 or 1): ");
            int fieldIndex = Integer.parseInt(scanner.nextLine());

            if (fieldIndex < fields.size() && fieldIndex >= 0) {
                fields.get(fieldIndex).plant(hand.remove(0));
            } else {
                System.out.println("Invalid field index. Planting in field 0 by default.");
                fields.get(0).plant(hand.remove(0));
            }
        }
    }

    public void plantTradedCards(Scanner scanner) {
        while (!hand.isEmpty()) {
            System.out.println("Your hand: " + hand);
            System.out.println("Choose a field to plant the card (0 or 1): ");
            int fieldIndex = Integer.parseInt(scanner.nextLine());

            if (fieldIndex < fields.size() && fieldIndex >= 0) {
                fields.get(fieldIndex).plant(hand.remove(0));
            } else {
                System.out.println("Invalid field index. Planting in field 0 by default.");
                fields.get(0).plant(hand.remove(0));
            }
        }
    }

    public void harvest(Scanner scanner) {
        for (int i = 0; i < fields.size(); i++) {
            System.out.println("Field " + i + ": " + fields.get(i).getBeans());
            System.out.println("Do you want to harvest this field? (yes/no): ");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("yes")) {
                addCoins(fields.get(i).getBeans().size());
                fields.get(i).harvest();
            }
        }
    }

    public void performTrade(Scanner scanner, List<Player> players) {
        System.out.println("Your hand: " + hand);
        System.out.println("Enter card index to offer for trade (or -1 to skip): ");
        int offerIndex = Integer.parseInt(scanner.nextLine());

        if (offerIndex == -1 || offerIndex >= hand.size()) {
            System.out.println("Skipping trade.");
            return;
        }

        Card offeredCard = hand.remove(offerIndex);
        boolean tradeAccepted = false;

        for (Player player : players) {
            if (player != this) {
                System.out.println("Player's hand: " + player.getHand());
                System.out.println("Enter card index to accept trade (or -1 to reject): ");
                int acceptIndex = Integer.parseInt(scanner.nextLine());

                if (acceptIndex != -1 && acceptIndex < player.getHand().size()) {
                    Card acceptedCard = player.getHand().remove(acceptIndex);
                    hand.add(acceptedCard);
                    player.getHand().add(offeredCard);
                    tradeAccepted = true;
                    System.out.println("Trade accepted!");
                    break;
                }
            }
        }

        if (!tradeAccepted) {
            hand.add(offeredCard);
            System.out.println("Trade rejected.");
        }
    }

    public List<Card> getHand() {
        return hand;
    }

    public List<Field> getFields() {
        return fields;
    }

    public int getCoins() {
        return coins;
    }

    public void addCoins(int coins) {
        this.coins += coins;
    }

    @Override
    public String toString() {
        return "Player{" +
                "hand=" + hand +
                ", fields=" + fields +
                ", coins=" + coins +
                '}';
    }
}



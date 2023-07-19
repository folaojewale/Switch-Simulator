package SwitchSim;

import java.util.ArrayList;
import java.util.List;
import SwitchSim.StrategyType.strategy;

// Player class represents a player in the Switch card game.
public class Player {

    Deck deckClass = new Deck();

    // Instance variables for the player.
    private int playerTag; // Player's identifier.
    private List<Card> cardsInHand; // List of cards in the player's hand.
    private strategy type; // Player's chosen strategy type.

    // Constructor to initialize a player with a playerTag and a chosen strategy type.
    public Player(int playerTag, strategy type) {
        this.playerTag = playerTag;
        cardsInHand = new ArrayList<Card>();
        this.type = type;
    }

    // Check if the player's hand is empty.
    public boolean isHandEmpty() {
        return cardsInHand.isEmpty();
    }

    // Get the player's identifier (tag).
    public int getPlayerTag() {
        return playerTag;
    }

    // Get the player's chosen strategy type.
    public strategy getPlayerStrategyType() {
        return type;
    }

    // Get the number of cards in the player's hand.
    public int cardsSize() {
        return cardsInHand.size();
    }

    // Add a list of cards to the player's hand.
    public void addCards(List<Card> cards) {
        for (int i = 0; i < cards.size(); i++) {
            cardsInHand.add(cards.get(i));
        }
    }

    // Add a list of starting cards to the player's hand at the start of the game.
    public void startingCards(List<Card> cards) {
        cardsInHand.addAll(cards);
    }

    // Get a list of playable cards based on the current topCard, drawMore condition, and aceSuit.
    public List<Card> getPlayableCards(Card topCard, boolean drawMore, String aceSuit) {
        List<Card> playableCards = new ArrayList<>();

        // Iterate through the player's hand to find playable cards based on the game rules.
        for (Card checkCard : this.cardsInHand) {
            if (!drawMore && aceSuit.isEmpty()) {
                if (checkCard.getRank().equals(topCard.getRank()) || checkCard.getSuit().equals(topCard.getSuit()) || checkCard.getRank().equals("Ace")) {
                    playableCards.add(checkCard);
                }
            } else {
                if ((checkCard.getSuit().equals(aceSuit) || checkCard.getRank().equals("Ace")) && !drawMore) {
                    playableCards.add(checkCard);
                }
            }

            // Check if the player needs to pick up cards based on the topCard's rank.
            if (drawMore) {
                if (topCard.getRank().equals("2")) {
                    if (checkCard.getRank().equals(topCard.getRank()) || (topCard.getSuit().equals("Hearts") && checkCard.getRank().equals("Queen") && checkCard.getSuit().equals("Hearts"))) {
                        playableCards.add(checkCard);
                    } else if (checkCard.getRank().equals(topCard.getRank()) || (topCard.getSuit().equals("Hearts") && checkCard.getRank().equals("King") && checkCard.getSuit().equals("Hearts"))) {
                        playableCards.add(checkCard);
                    } else if (topCard.getSuit().equals("Hearts") && checkCard.getRank().equals("5") && checkCard.getSuit().equals("Hearts")) {
                        playableCards.add(checkCard);
                    }
                } else if (topCard.getRank().equals("Queen") && topCard.getSuit().equals("Hearts")) {
                    if ((checkCard.getRank().equals("2") || checkCard.getRank().equals("King")) && checkCard.getSuit().equals("Hearts")) {
                        playableCards.add(checkCard);
                    } else if (checkCard.getRank().equals("5") && checkCard.getSuit().equals("Hearts")) {
                        playableCards.add(checkCard);
                    }
                } else if (topCard.getRank().equals("King") && topCard.getSuit().equals("Hearts")) {
                    if ((checkCard.getRank().equals("2") || checkCard.getRank().equals("Queen")) && checkCard.getSuit().equals("Hearts")) {
                        playableCards.add(checkCard);
                    } else if (checkCard.getRank().equals("5") && checkCard.getSuit().equals("Hearts")) {
                        playableCards.add(checkCard);
                    }
                }
            }
        }

        return playableCards;
    }

    // Choose cards to play based on the current topCard, drawMore condition, and aceSuit using the player's strategy.
    public List<Card> chosenCard(Card topCard, boolean drawMore, String aceSuit) {
        List<Card> playedCards = new ArrayList<>();
        List<Card> useableCards = getPlayableCards(topCard, drawMore, aceSuit);

        // If there are no usable cards, return an empty list (pass).
        if (useableCards.isEmpty()) {
            return playedCards;
        }

        // Use the player's chosen strategy to select the best card from the list of usable cards.
        StrategyType strategyClass;
        switch (type) {
            case OFFENSIVE:
                strategyClass = new Offensive(useableCards, topCard, cardsInHand, aceSuit);
                break;
            case DEFENSIVE:
                strategyClass = new Defensive(useableCards, topCard, cardsInHand, aceSuit);
                break;
            default:
                strategyClass = new Random(useableCards, topCard, cardsInHand, aceSuit);
        }

        playedCards.addAll(strategyClass.chosenCards(drawMore));

        // Remove the chosen card(s) from the player's hand.
        for (Card card : playedCards) {
            cardsInHand.remove(card);
        }

        return playedCards;
    }

    // Determine the most frequent suit in the player's hand.
    public String maxSuit() {
        String suit = "";
        String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};
        int max = 0;

        for (String currentSuit : suits) {
            int count = 0;
            for (Card card : cardsInHand) {
                if (card.getSuit().equals(currentSuit)) {
                    count++;
                }
            }
            if (count > max) {
                suit = currentSuit;
                max = count;
            }
        }

        return suit;
    }

    // Print the player's tag and each card in their hand.
    public void printDeck() {
        System.out.println(this.toString() + " - Strategy: " + this.getPlayerStrategyType());
        for (Card card : cardsInHand) {
            System.out.println(card);
        }
    }

    @Override
    public String toString() {
        return "Player " + playerTag;
    }
}

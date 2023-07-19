package SwitchSim;

import java.util.List;
import java.util.ArrayList;

// Offensive class represents a specific strategy type that aims to play offensively.
public class Offensive extends StrategyType {

    // Constructor to initialise the Offensive strategy with availableCards, topCard, cardsInHand, and aceSuit.
    public Offensive(List<Card> availableCards, Card topCard, List<Card> cardsInHand, String aceSuit) {
        super(availableCards, topCard, cardsInHand, aceSuit);
    }

    // Override the chosenCards method to implement the offensive strategy for card selection.
    @Override
    public List<Card> chosenCards(boolean drawMore) {
        List<Card> play = new ArrayList<>();

        // If there are no available cards to play, return an empty list (pass).
        if (availableCards.isEmpty()) {
            return play;
        }

        // Define the firstCard as null, it will be set based on the specific offensive conditions.
        Card firstCard = null;

        // Check if any card in the player's hand meets the offensive criteria and set it as the firstCard.
        for (Card card : cardsInHand) {
            if (card.getRank().equals("King") && card.getSuit().equals("Hearts") && (topCard.getSuit().equals(card.getSuit()) || aceSuit.equals(card.getSuit()))) {
                firstCard = card;
                break;
            } else if (card.getRank().equals("Queen") && card.getSuit().equals("Hearts") && (topCard.getSuit().equals(card.getSuit()) || aceSuit.equals(card.getSuit()))) {
                firstCard = card;
                break;
            } else if (card.getRank().equals("2") && (topCard.getSuit().equals(card.getSuit()) || aceSuit.equals(card.getSuit()))) {
                firstCard = card;
                break;
            } else if (card.getRank().equals("7") && (topCard.getSuit().equals(card.getSuit()) || aceSuit.equals(card.getSuit()))) {
                firstCard = card;
                break;
            } else {
                firstCard = availableCards.get(0);
            }
        }

        // Check if any card in the player's hand matches the firstCard's rank (except for Aces).
        for (Card card : cardsInHand) {
            if (card.getRank().equals(firstCard.getRank()) && !"Ace".equals(firstCard.getRank())) {
                play.add(card);
            } else if ("Ace".equals(card.getRank()) && play.isEmpty() && !drawMore) {
                play.add(card);
                return play;
            }
        }

        // Rearrange the cards in the play list to prioritise cards that match the topCard or aceSuit.
        if (play.size() > 1 && (play.get(0).getRank().equals(topCard.getRank()) || !play.get(0).getSuit().equals(topCard.getSuit()) || !play.get(0).getSuit().equals(aceSuit))) {
            for (Card card : play) {
                if (card.getRank().equals(topCard.getRank()) || card.getSuit().equals(topCard.getSuit()) || card.getSuit().equals(aceSuit)) {
                    play.remove(card);
                    play.add(0, card);
                    break;
                }
            }
        }

        return play;
    }
}

package SwitchSim;

import java.util.List;
import java.util.ArrayList;

// Defensive class represents a specific strategy type that aims to play defensively.
public class Defensive extends StrategyType {

    // Constructor to initialise the Defensive strategy with availableCards, topCard, cardsInHand, and aceSuit.
    public Defensive(List<Card> availableCards, Card topCard, List<Card> cardsInHand, String aceSuit) {
        super(availableCards, topCard, cardsInHand, aceSuit);
    }

    // Override the chosenCards method to implement the defensive strategy for card selection.
    @Override
    public List<Card> chosenCards(boolean drawMore) {
        List<Card> play = new ArrayList<>();

        // If there are no available cards to play, return an empty list (pass).
        if (availableCards.isEmpty()) {
            return play;
        }

        // Get the first available card in the availableCards list.
        Card firstCard = availableCards.get(0);

        // Check if any card in the player's hand is eligible for play according to the defensive strategy.
        for (Card card : cardsInHand) {
            if (drawMore && (card.getRank().equals("2") || (card.getRank().equals("Queen") || card.getRank().equals("King")) && card.getSuit().equals("Hearts"))) {
                play.add(card);
                return play;
            } else if (card.getRank().equals(firstCard.getRank()) && !"Ace".equals(firstCard.getRank())) {
                play.add(card);
            } else if ("Ace".equals(card.getRank()) && play.isEmpty() && !drawMore) {
                play.add(card);
                return play;
            }
        }

        // Rearrange the cards in the play list to prioritise cards that match the topCard or aceSuit.
        if (play.size() > 1 && (play.get(0).getRank() != topCard.getRank() || !play.get(0).getSuit().equals(topCard.getSuit()) || !play.get(0).getSuit().equals(aceSuit))) {
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

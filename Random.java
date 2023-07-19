package SwitchSim;

import java.util.ArrayList;
import java.util.List;

// Random class represents a specific strategy type that plays randomly.
public class Random extends StrategyType {
	
	// Constructor to initialize the Random strategy with availableCards, topCard, cardsInHand, and aceSuit.
	public Random(List<Card> availableCards, Card topCard, List<Card> cardsInHand, String aceSuit) {
		super(availableCards, topCard, cardsInHand, aceSuit);
	}
	
	// Override the chosenCards method to implement the random card selection strategy.
	@Override
	public List<Card> chosenCards(boolean drawMore) {
	    List<Card> play = new ArrayList<>();

	    // If there are no available cards to play, return an empty list (pass).
	    if(availableCards.isEmpty()) {
	        return play;
	    }

	    // Choose the first card available in the availableCards list.
	    Card firstCard = availableCards.get(0);

	    // Check if any card in the player's hand matches the firstCard's rank (except for Aces).
	    for(Card card : cardsInHand) {
	        if(card.getRank().equals(firstCard.getRank()) && !"Ace".equals(firstCard.getRank())) {
	            play.add(card);
	        } 
	        else if("Ace".equals(card.getRank()) && play.isEmpty() && !drawMore) {
	            play.add(card);
	            return play;
	        }
	    }

	    // Rearrange the cards in the play list to prioritize cards that match the topCard or aceSuit.
	    if(play.size() > 1 && (play.get(0).getRank() != topCard.getRank() || !play.get(0).getSuit().equals(topCard.getSuit()) || !play.get(0).getSuit().equals(aceSuit))) {
	        for(Card card : play) {
	            if(card.getRank().equals(topCard.getRank()) || card.getSuit().equals(topCard.getSuit()) || card.getSuit().equals(aceSuit)) {
	                play.remove(card);
	                play.add(0, card);
	                break;
	            }
	        }
	    }

	    return play;
	}
}

package SwitchSim;

import java.util.List;

// StrategyType is an abstract class representing different card playing strategies.
abstract class StrategyType {

    // Instance variables for the strategy type.
    protected List<Card> availableCards; // List of available cards that can be played.
    protected List<Card> cardsInHand; // List of cards in the player's hand.
    protected Card topCard; // The top card on the deck.
    protected String aceSuit; // The suit chosen by an Ace card.
    
    // Enum to represent different strategy types.
    enum strategy {RANDOM, OFFENSIVE, DEFENSIVE}

    // Constructor to initialize a strategy with availableCards, topCard, cardsInHand, and aceSuit.
    public StrategyType(List<Card> availableCards, Card topCard, List<Card> cardsInHand, String aceSuit) {
        this.availableCards = availableCards;
        this.cardsInHand = cardsInHand;
        this.topCard = topCard;
        this.aceSuit = aceSuit;
    }

    // Abstract method to be implemented by the subclasses representing the card playing logic for each strategy type.
    public abstract List<Card> chosenCards(boolean drawMore);
}

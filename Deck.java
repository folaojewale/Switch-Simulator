package SwitchSim;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Deck {
    private static List<Card> cards; // The main deck of cards.
    private static List<Card> placedCards; // Cards that have been played or placed.

    // Constructor to initialize the deck with all 52 cards and shuffle them.
    public Deck() {
        cards = new ArrayList<>();
        placedCards = new ArrayList<>();
        initializeDeck();
    }
    
    // Get the entire deck of cards, shuffling them before returning.
    public List<Card> getDeck(){
        Collections.shuffle(cards);
        return cards;
    }
    
    // Draw a specified number of cards from the deck.
    public List<Card> drawCardFromDeck(int amount) {
        if (amount < 1) {
            throw new IllegalArgumentException("Card(s) drawn must be at least 1");
        }
        
        // If the main deck is empty or doesn't have enough cards, refill it from the placed cards and shuffle.
        if(cards.isEmpty() || cards.size() < amount) {
            cards.addAll(placedCards);
            cards.remove(0); // Remove the last drawn card from the placed cards.
            placedCards.subList(1, placedCards.size()).clear();
            this.shuffle();
        }
        
        List<Card> returnCards = new ArrayList<>();
        
        // Draw the specified number of cards from the main deck and add them to the returnCards list.
        for(int i = 0; i < amount; i++) {
            Card card = cards.get(0);
            returnCards.add(card);
            cards.remove(0);
        }
        
        return returnCards;
    }
    
    // Get the first card from the main deck and remove it from the deck.
    public Card startCard() {
        Card card = cards.get(0);
        cards.remove(0);
        return card;
    }
    
    // Get the top card from the placed cards list without removing it.
    public Card getTopCard() {
        Card card = placedCards.get(0);
        return card;
    }
    
    // Add a card to the placed cards list at the top.
    public void addToPlacedCards(Card card) {
        placedCards.add(0, card);
    }

    // Shuffle the main deck of cards.
    public void shuffle() {
        Collections.shuffle(cards);
    }

    // Print each card in the main deck.
    public void printDeck() {
        for (Card card : cards) {
            System.out.println(card);
        }
    }
    
    // Create a deck of 52 cards by combining ranks and suits.
    private void initializeDeck() {
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
        String[] suits = {"Spades", "Hearts", "Diamonds", "Clubs"};

        for (String suit : suits) {
            for (String rank : ranks) {
                cards.add(new Card(rank, suit));
            }
        }
    }
}

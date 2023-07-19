package SwitchSim;

// Card class represents a playing card with a rank and suit.
public class Card {
    private String rank; // The rank of the card, e.g., "Ace", "2", "King", etc.
    private String suit; // The suit of the card, e.g., "Hearts", "Diamonds", etc.

    // Constructor to create a new Card object with the specified rank and suit.
    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
    }

    // Getter method to retrieve the rank of the card.
    public String getRank() {
        return rank;
    }

    // Getter method to retrieve the suit of the card.
    public String getSuit() {
        return suit;
    }

    // Override the toString method to provide a custom string representation of the card.
    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}

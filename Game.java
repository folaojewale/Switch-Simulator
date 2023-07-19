package SwitchSim;

import java.util.List;

// Game class represents the main game controller for the Switch card game.
public class Game {
    // Create instances of the Deck and Players classes.
    Deck deckClass = new Deck();
    Players playersClass = new Players(5);

    // Instance variables for the game.
    private List<Player> players; // List of players in the game.
    private boolean keepPlaying; // Indicates whether the game should continue.
    private boolean drawMore; // Indicates if a player must draw more cards.
    private boolean replay; // Indicates if the current player's turn should be replayed.
    private boolean skip; // Indicates if the next player's turn should be skipped.
    private int stack; // Counter for the number of cards to draw.
    private String aceSuit; // The suit chosen after playing an Ace card.

    // Constructor to initialise the game and start the game setup.
    public Game() {
        deckClass.shuffle();
        players = playersClass.getPlayers();
        keepPlaying = true;
        drawMore = false;
        replay = false;
        stack = 0;
        aceSuit = "";

        // Deal starting cards to each player and display their hands.
        for (Player player : players) {
            player.startingCards(deckClass.drawCardFromDeck(4));
            player.printDeck();
        }

        // Set the starting card for the game by placing it on the played cards pile.
        deckClass.addToPlacedCards(deckClass.startCard());
        System.out.println("Starting card is: " + deckClass.getTopCard());
    }

    // Start the game and control the flow of turns and actions.
    public void start() throws InterruptedException {
        while (keepPlaying) {
            for (int i = 0; i < players.size(); i++) {
                Player player = players.get(i);

                // Handle replay or skip turns based on the previous turn's action.
                if (replay) {
                    replay = false;
                    i = (i - 1 < 0) ? players.size() - 1 : i - 1;
                    player = players.get(i);
                } else if (skip) {
                    skip = false;
                    i = (i + 1) % players.size();
                    player = players.get(i);
                }

                // Delay between each player's turn for better visualisation.
                Thread.sleep(500);

                // Get the cards selected by the current player for play.
                List<Card> returnedCard = player.chosenCard(deckClass.getTopCard(), drawMore, aceSuit);

                if (!returnedCard.isEmpty()) {
                    System.out.print("\n" + player + " plays ");

                    // Process the cards played by the current player.
                    if (returnedCard.size() > 0) {
                        for (Card card : returnedCard) {
                            if (returnedCard.size() > 1 && card != returnedCard.get(0)) {
                                System.out.print(", ");
                            }

                            System.out.print(card);
                            aceSuit = "";
                            deckClass.addToPlacedCards(card);

                            // Check if the top card's rank requires special actions.
                            String topRank = deckClass.getTopCard().getRank();
                            String topSuit = deckClass.getTopCard().getSuit();

                            if ("Ace".equals(topRank)) {
                                aceSuit = player.maxSuit();
                                System.out.print("\n" + player + " changed the suit to " + aceSuit);
                            } else if ("Jack".equals(topRank)) {
                                replay = true;

                                if (player.isHandEmpty()) {
                                    player.addCards(deckClass.drawCardFromDeck(1));
                                    System.out.print("\n" + player + " draws from the deck. ");
                                }
                            } else if ("7".equals(topRank)) {
                                skip = true;
                            } else if ("2".equals(topRank)) {
                                stack += 2;
                                drawMore = true;
                            } else if ("Queen".equals(topRank) && "Hearts".equals(topSuit)) {
                                stack += 3;
                                drawMore = true;
                            } else if ("King".equals(topRank) && "Hearts".equals(topSuit)) {
                                stack += 5;
                                drawMore = true;
                            } else if ("5".equals(topRank) && "Hearts".equals(topSuit)) {
                                stack = 0;
                                drawMore = false;
                            } else {
                                stack = 0;
                                drawMore = false;
                            }
                        }
                    }
                } else if ("2".equals(deckClass.getTopCard().getRank()) && drawMore) {
                    player.addCards(deckClass.drawCardFromDeck(stack));
                    System.out.print("\n" + player + " draws " + stack + " cards from the deck.");
                    drawMore = false;
                    stack = 0;
                    aceSuit = "";
                } else if ("Queen".equals(deckClass.getTopCard().getRank()) && "Hearts".equals(deckClass.getTopCard().getSuit()) && drawMore) {
                    player.addCards(deckClass.drawCardFromDeck(stack));
                    System.out.print("\n" + player + " draws " + stack + " cards from the deck.");
                    drawMore = false;
                    stack = 0;
                    aceSuit = "";
                } else if ("King".equals(deckClass.getTopCard().getRank()) && "Hearts".equals(deckClass.getTopCard().getSuit()) && drawMore) {
                    player.addCards(deckClass.drawCardFromDeck(stack));
                    System.out.print("\n" + player + " draws " + stack + " cards from the deck.");
                    drawMore = false;
                    stack = 0;
                    aceSuit = "";
                } else {
                    player.addCards(deckClass.drawCardFromDeck(1));
                    System.out.print("\n" + player + " draws from the deck. ");
                }

                // Check if any player has won the game.
                if (ifWinner(players) > -1) {
                    System.out.print("\nGame Finished: Winner is Player " + ifWinner(players));
                    keepPlaying = false;
                    drawMore = false;
                    break;
                }
            }
        }
    }

    // Helper method to check if any player has an empty hand (wins the game).
    public int ifWinner(List<Player> players) {
        int playerTag = -1;

        for (Player player : players) {
            if (player.isHandEmpty()) {
                playerTag = player.getPlayerTag();
                return playerTag;
            }
        }

        return playerTag;
    }
}

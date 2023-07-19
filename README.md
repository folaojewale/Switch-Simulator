# Switch-Simulator

How to Play Switch:
Switch is a card game that is played with a standard deck of 52 cards. The objective of the game is to be the first player to get rid of all the cards in their hand. Players take turns playing cards, and the card they play must match the rank or suit of the top card on the discard pile. If a player cannot play a card, they must draw cards from the deck until they can play one. Special cards, such as Aces, Twos, Queens, Kings, and Sevens, have specific effects in the game.

Classes:
The simulation is divided into several classes to manage different aspects of the game:

Card: Represents a single playing card with a rank and a suit.
Deck: Represents the deck of cards used in the game. It provides methods for shuffling and drawing cards.
Player: Represents a player in the game. Players can implement different strategies: Random, Offensive, and Defensive.
StrategyType: An abstract class representing different card-playing strategies. Subclasses implement specific strategies for card selection.
Offensive, Defensive, and Random: Subclasses of StrategyType, implementing different card-playing strategies.

Player Strategies:
The simulation provides three player strategies that players can choose from:

Random Strategy: The player selects cards randomly from the playable cards in their hand.
Offensive Strategy: The player follows a more aggressive approach, trying to play powerful cards like Kings, Queens, and Twos.
Defensive Strategy: The player adopts a more conservative approach, saving cards like Twos and Queens to use defensively.

Game Simulation:
The Game class orchestrates the entire game simulation. Players take turns playing cards based on their chosen strategy until a player runs out of cards, winning the game. The game keeps track of special cards' effects, such as Twos, Queens, Kings, and Sevens and more.

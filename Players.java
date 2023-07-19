package SwitchSim;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import SwitchSim.StrategyType.strategy;
import java.util.Random;

public class Players {
	
	private static List<Player> players;
	private Random random = new Random();
	
	Class<strategy> strategyEnum = strategy.class;
	
	public Players() {
		players = new ArrayList<>();
		
		// Create 4 players and add them to the players list
		for(int i = 0; i < 4; i++) {
			int randomIndex = random.nextInt(strategyEnum.getEnumConstants().length);
			strategy randomStrategy = strategyEnum.getEnumConstants()[randomIndex];
			players.add(new Player(i+1, randomStrategy));
		}
	}

	public Players(int playerAmount) {
		players = new ArrayList<>();
		
		// Create 'playerAmount' number of players and add them to the players list
		for(int i = 0; i < playerAmount; i++) {
			int randomIndex = random.nextInt(strategyEnum.getEnumConstants().length);
			strategy randomStrategy = strategyEnum.getEnumConstants()[randomIndex];
			players.add(new Player(i+1, randomStrategy));
		}
	}
	
	public List<Player> getPlayers() {
		// Shuffle the players in the players list and return the list
		Collections.shuffle(players);
		return players;
	}
}

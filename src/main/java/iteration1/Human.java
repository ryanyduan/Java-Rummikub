package iteration1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;

public class Human extends Player {
	
	public HashMap<Integer, ArrayList<Tile>> turnOptions;
	public ArrayList<Tile> played;
	public int choice = 0;

	public Human(Table table, String name) {
		super(table, name);
	}

	@Override
	public boolean turn() {
		
		//Finds all melds.  If no melds, draw a card and end turn.
		//If player has not broken initial 30 points, require they play a meld with 30 points
		//Shows player list of melds to play and executes the move the player chooses
		
		runs = this.findRuns();
		sets = this.findSets();
		
		if (runs.isEmpty() && sets.isEmpty()) {
			System.out.println("You draw a card since there are no tiles to play.");
			if (this.table.Deck.isEmpty()) {
				System.out.println("Deck is empty. No card was drawn.");
			}
			else {
				this.draw();
			}
			this.displayHand();
			Collections.sort(this.Hand);
			return false;
		}
		
		if (!this.is30) {
			for (Iterator<ArrayList<Tile>> it = runs.iterator(); it.hasNext(); ) {
				ArrayList<Tile> run = it.next();
				if (value(run) < 30) {
					it.remove();
				}
			}
			
			for (Iterator<ArrayList<Tile>> it = sets.iterator(); it.hasNext(); ) {
				ArrayList<Tile> set = it.next();
				if (value(set) < 30) {
					it.remove();
				}
			}
		}
		
		turnOptions = new HashMap<Integer, ArrayList<Tile>>();
		
		int counter = 0;
		for (ArrayList<Tile> run: runs) {
			turnOptions.put(counter, run);
			counter++;
		}
		
		for (ArrayList<Tile> set: sets) {
			turnOptions.put(counter, set);
			counter++;
		}
		
		if (!turnOptions.isEmpty()) {
			System.out.println("Here are your options of melds to play");
			printMap(turnOptions);
			System.out.println("Choose the number corresponding to the tiles you want to play.");
			Scanner scan = new Scanner(System.in);
			choice = scan.nextInt();
			while (!turnOptions.containsKey(choice)) {
				System.out.println("Choose the number corresponding to the tiles you want to play.");
				choice = scan.nextInt();
			}
			
			executeMove();
		}
		
		else {
			return emptyMessage();
		}
		
		return true;
	}
	
	@Override
	public void executeMove() {
		
		// Play the meld onto the board (showing with '*' that it was just played) and also remove the corresponding tiles from player's hand 
		// If player has just "broken" his rule about not being able to play until he has 30 points, the rule is now broken (is30 = true)
		// Displays Hand
		// Displays Table
		
		played = turnOptions.remove(choice);
		
		for (Tile t: played) {
			t.justPlayed = true;
		}
		
		table.Board.add(played);
		
		for (Iterator<Tile> tiles = this.Hand.iterator(); tiles.hasNext();) {
			Tile toRemove = tiles.next();
			if (played.contains(toRemove)) {
				tiles.remove();
			}
		}
		
		this.is30 = true;
		
		Collections.sort(this.Hand);
		this.displayHand();
		table.displayBoard();
		for (Tile t: played) {
			t.justPlayed = false;
		}
	}	
	
	public void printMap(HashMap<Integer, ArrayList<Tile>> map) {
		
		// Print each of the player's possible melds he/she can play
		
	    Iterator<Entry<Integer, ArrayList<Tile>>> it = map.entrySet().iterator();
	    while (it.hasNext()) {
	        HashMap.Entry pair = (HashMap.Entry)it.next();
	        System.out.println(pair.getKey() + " = " + pair.getValue());
	    }
	}
	

}

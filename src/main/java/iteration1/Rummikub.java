package iteration1;

public class Rummikub {	
	
	public static Table table;
	public static Player human;
	public static Player[] players;
	public static Player currentPlayer;
	public static boolean win;

	public static void main(String[] args) {
		
		win = false;
		
		table = new Table();
		players = new Player[4];
		human = new Human(table, "Human");
		players[0] = human;
		human.displayHand();
		
		while (!win) {
			int counter = 0;
			currentPlayer = players[counter%4];
			
			while (currentPlayer.turn()) { //this line will also execute the player's turn
				if (currentPlayer.Hand.isEmpty()) {
					System.out.println(currentPlayer.getName() + " is the winner!");
					win = true;
					break;
				}
			}
			
			counter++; //next player's turn
		}
	}
		
}

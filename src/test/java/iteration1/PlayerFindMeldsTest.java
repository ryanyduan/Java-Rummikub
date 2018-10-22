package iteration1;

import junit.framework.TestCase;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class PlayerFindMeldsTest extends TestCase {
	
	public void testRun(){
		Table testTable = new Table();
		Player player = new Player("Human" ,testTable);
		ArrayList<ArrayList<Tile>> testRuns = new ArrayList<ArrayList<Tile>>();
		ArrayList<Tile> testRun = new ArrayList<Tile>();
		Tile one = new Tile('O', 8);
		Tile two = new Tile('O', 9);
		Tile three = new Tile('O', 10);
		player.Hand.add(one);
		player.Hand.add(two);
		player.Hand.add(three);
		testRun.add(one);
		testRun.add(two);
		testRun.add(three);
		testRuns.add(testRun);
		assertEquals(true, testRuns.equals(player.findRuns()));
		Tile four = new Tile('O', 11);
		player.Hand.add(four);
		testRuns.get(0).add(four);
		assertEquals(testRuns, player.findRuns());
		assertEquals(true, testRuns.equals(player.findRuns()));
		Tile five = new Tile('G', 1);
		Tile six = new Tile('G', 2);
		Tile seven = new Tile('G', 3);
		player.Hand.add(five);
		player.Hand.add(six);
		player.Hand.add(seven);
		ArrayList<Tile> testRun2 = new ArrayList<Tile>();
		testRun2.add(five);
		testRun2.add(six);
		testRun2.add(seven);
		testRuns.add(testRun2);
		assertEquals(testRuns, player.findRuns());
		
		ArrayList<Tile> testRun3 = new ArrayList<Tile>();
		Tile eight = new Tile('O', 2);
		Tile nine = new Tile('O', 3);
		Tile ten = new Tile('O', 4);
		testRun3.add(eight);
		testRun3.add(nine);
		testRun3.add(ten);
		testRuns.add(testRun3);
		player.Hand.add(eight);
		player.Hand.add(nine);
		player.Hand.add(ten);
		assertEquals(testRuns, player.findRuns());
		
	}
	
	public void testSet() {
		Table testTable = new Table();
		Player player = new Player("Human",testTable);
		ArrayList<ArrayList<Tile>> testSets = new ArrayList<ArrayList<Tile>>();
		ArrayList<Tile> testSet = new ArrayList<Tile>();
		Tile one = new Tile('O', 8);
		Tile two = new Tile('G', 8);
		Tile three = new Tile('B', 8);
		player.Hand.add(one);
		player.Hand.add(two);
		player.Hand.add(three);
		testSet.add(one);
		testSet.add(two);
		testSet.add(three);
		testSets.add(testSet);
		assertEquals(testSets, player.findSets());
	}
	
	public void testNoSets() {
		Table testTable = new Table();
		Player player = new Player("Human",testTable);
		ArrayList<ArrayList<Tile>> testSets = new ArrayList<ArrayList<Tile>>();
		ArrayList<Tile> testSet = new ArrayList<Tile>();
		Tile one = new Tile('O', 8);
		Tile two = new Tile('G', 8);
		Tile three = new Tile('B', 8);
		player.Hand.add(one);
		player.Hand.add(two);
		testSet.add(one);
		testSet.add(two);
		testSet.add(three);
		testSets.add(testSet);
		assertThat(testSets, not(player.findSets()));
	}
	
	public void testMultipleSets() {
		Table testTable = new Table();
		Player player = new Player("Human",testTable);
		ArrayList<ArrayList<Tile>> testSets = new ArrayList<ArrayList<Tile>>();
		ArrayList<Tile> testSet = new ArrayList<Tile>();
		Tile one = new Tile('O', 8);
		Tile two = new Tile('G', 8);
		Tile three = new Tile('B', 8);
		Tile four = new Tile('G', 8);
		Tile five = new Tile('R', 8);
		Tile six = new Tile('O', 10);
		Tile seven = new Tile ('G', 10);
		Tile eight = new Tile('R', 10);
		player.Hand.add(one);
		player.Hand.add(two);
		player.Hand.add(three);
		player.Hand.add(four);
		player.Hand.add(five);
		player.Hand.add(six);
		player.Hand.add(seven);
		player.Hand.add(eight);
		testSet.add(one);
		testSet.add(two);
		testSet.add(three);
		testSet.add(five);
		testSets.add(testSet);
		
		ArrayList<Tile> testSet2 = new ArrayList<Tile>();
		testSet2.add(four);
		testSet2.add(one);
		testSet2.add(three);
		testSet2.add(five);
		testSets.add(testSet2);
		
		ArrayList<Tile> testSet3 = new ArrayList<Tile>();
		testSet3.add(six);
		testSet3.add(seven);
		testSet3.add(eight);
		
		testSets.add(testSet3);
		assertEquals(testSets, player.findSets());
		
	}
	
	public void testMultipleSetsWithProperComparableTile() {
		Table test = new Table();
		Player player = new Player("Human", test);
		
		ArrayList<ArrayList<Tile>> testSets = new ArrayList<ArrayList<Tile>>();
		ArrayList<Tile> testSet = new ArrayList<Tile>();
		
		Tile one = new Tile('O', 8);
		Tile two = new Tile('G', 8);
		Tile three = new Tile('B', 8);
		Tile four = new Tile('G', 8);
		Tile five = new Tile('R', 8);
		Tile six = new Tile('O', 10);
		Tile seven = new Tile ('G', 10);
		Tile eight = new Tile('R', 10);
		player.Hand.add(one);
		player.Hand.add(two);
		player.Hand.add(three);
		player.Hand.add(four);
		player.Hand.add(five);
		player.Hand.add(six);
		player.Hand.add(seven);
		player.Hand.add(eight);
		Collections.shuffle(player.Hand);
		Collections.sort(player.Hand);
		
		testSet.add(three);
		testSet.add(two);
		testSet.add(one);
		testSet.add(five);
		testSets.add(testSet);
		
		ArrayList<Tile> testSet2 = new ArrayList<Tile>();
		testSet2.add(four);
		testSet2.add(three);
		testSet2.add(one);
		testSet2.add(five);
		testSets.add(testSet2);
		
		ArrayList<Tile> testSet3 = new ArrayList<Tile>();
		testSet3.add(seven);
		testSet3.add(six);
		testSet3.add(eight);
		
		testSets.add(testSet3);
		assertEquals(testSets, player.findSets());
		
		
		
		
	}
}

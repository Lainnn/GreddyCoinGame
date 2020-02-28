/**
 * Plays Greedy Coin game such that the computer never loses.
 * 
 * [ YOUR NAME GOES HERE]
 */
import java.io.*;
import java.util.Scanner;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;



public class GreedyCoinGame <E>{
	List <E> coins = new LinkedList();
	 int robo = 0;
	 int human = 0;
	public GreedyCoinGame(String file) throws FileNotFoundException {
		Scanner inFile = new Scanner(new File(file));
		int i = 0;
		while (inFile.hasNext()) {
			//System.out.println(inFile.nextInt());
			// TO-DO store each coin in a linked list
			coins.add((E)((Integer)inFile.nextInt()));
		}
		
		inFile.close();
	}

	// prints the coins and their position
	public void printCoins() {
		System.out.println("+++++++++++++++++");
		
		// TO-DO print out each element and its position in the linked list
		for (int i = 0; i < coins.getLength(); i++) {
			System.out.print("Coins: " + coins.get(i));
		    System.out.print("        ");
			}
		System.out.println();
		for (int i = 0; i < coins.getLength(); i++) {
			System.out.print("Posistions: " + i);
		    System.out.print("    ");
			}
		System.out.println();
		System.out.println("ROBO: " + robo);
		System.out.println("HUMAN: " + human);
		
		System.out.println("+++++++++++++++++");
	}
	
	@SuppressWarnings("unchecked")
	public void playGame() throws NoCoinsPossibleException {
		System.out.println("Let's play the coin game!");
		printCoins();

		// get the keyboard for the silly human
		Scanner keyboard = new Scanner(System.in);
		
		// TO-DO Play the game using the Red Blue strategy
		List <E> red = new LinkedList();
		List <E>blue = new LinkedList();
		List countR = new LinkedList();
		List countB = new LinkedList();
		int valueR = 0;
		int valueB = 0;
		
		
		//put coins in different list
		for (int i = 0; i < coins.getLength(); i++) {
			if (i % 2 == 0) {
				red.add((E)coins.get(i));
				countR.add(i);
				valueR = valueR + (int)coins.get(i);
			}
			else {
				blue.add((E)coins.get(i));
				countB.add(i);
				valueB = valueB + (int)coins.get(i);
		}
		}
		
		//if value of red is greater than blue
		//choose the biggest number of red as the one
		if (valueR >= valueB) {
			System.out.println("I Choose " + coins.indexOf(red.getMax()));//print choice
			robo = robo + (int)(red.getMax());//record score
			coins.remove(red.getMax());//remove from coins
			red.remove(red.getMax());//remove from red list

		}
		else {
			System.out.println("I Choose " + coins.indexOf(blue.getMax()));//print choice
			robo = robo + (int)blue.getMax();//record score
		    coins.remove(blue.getMax());//remove from coins
		    blue.remove(blue.getMax());//remove from blue list
		}
		
		printCoins();//print current available coins
		
		NoCoinsPossibleException exception = new NoCoinsPossibleException();//exception, occurs when player choose a coin that is not in the index
		System.out.println("Indicate the position of the coin you choose: ");
		int humanChoice = keyboard.nextInt();
		if(humanChoice >= coins.getLength())
			throw exception;
		printCoins();
		//keyboard.close();
		System.out.println("You choosed " + humanChoice);
		human = human + (int)coins.get(humanChoice);//record human score
		coins.remove(humanChoice);
		
		System.out.println("ROBO Score is : " + robo);
		System.out.println("HUMAN Score is : " + human);
		System.out.println();

	}

	public static void main(String[] args) throws IOException, NoCoinsPossibleException {
		if (args.length != 1) {
			System.err.println("Pass a file on the command line");
			System.exit(0);
		}
		
		
		GreedyCoinGame game = new GreedyCoinGame(args[0]);
		while (!game.coins.isEmpty()) {
		game.playGame();
		}
		System.out.println("Game Over!");
		game.printCoins();
	}

}

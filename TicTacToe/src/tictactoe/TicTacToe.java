package tictactoe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Formatter;
import java.util.Random;
import java.util.Scanner;


/**
 * @author Calen Sullivan
 * @version 1.0
 * @since 4-22-21
 * 
 * Big thanks to Steve for helping me put the finishing touches up on it!
 */

@SuppressWarnings("unused")
public class TicTacToe {

	private static int computerWins;
	private static int playerWins;
	private static int ties;
	private static Scanner input;
	private static Formatter output;
	
	
	@SuppressWarnings("resource")
	public static void main(String[] args) {
	
		openFileForRead();
		readFromFile();
		closeFileForRead();
	
	String[][] gameBoard = { {" ", "A",  "_", "B",  "_", "C"},
							 {"1", " ",  "|", " ",  "|", " "},
							 {" ", "-",  "|", "-",  "|", "-"},
							 {"2", " ",  "|", " ",  "|", " "},
							 {" ", "-",  "|", "-",  "|", "-"},
							 {"3", " ",  "|", " ",  "|", " "}
							};
	
		printGameBoard(gameBoard);
		
		
		int count = 0;
		
		while(true) {
			
			Scanner input = new Scanner(System.in);
			String userChoice;
			
			Random rand = new Random();
			
			int computerChoice = rand.nextInt(9) + 1;
			
			
			/*
			 * Checks to see if the spot is empty by seeing if the index doesn't contain the original value of a space. If it doesn't, the computer is prompted to try again.
			 */

			placeComputerPiece(gameBoard,computerChoice);
			System.out.println();
			System.out.println("Computer's Turn: ");
			printGameBoard(gameBoard);
			if (checkWinnerComputer(gameBoard)) {
				computerWins++;
				gameOver();
			}
			
			
			if (checkFull(gameBoard)) {
				System.out.println("That is a tie.");
				ties++;
				gameOver();
			}
			
			
		while (true) {
			
			System.out.printf("Enter where you would like to go (A-C,1-3): ");
			userChoice = input.nextLine();
			
			/*
			 * Checks to see if the user actually enters (A-C,1-3).
			 */
			
			if (!userChoice.equalsIgnoreCase("A1") && !userChoice.equalsIgnoreCase("A2") && !userChoice.equalsIgnoreCase("A3") && !userChoice.equalsIgnoreCase("B1") &&
						!userChoice.equalsIgnoreCase("B2") && !userChoice.equalsIgnoreCase("B3") && !userChoice.equalsIgnoreCase("C1") && !userChoice.equalsIgnoreCase("C2") &&
							!userChoice.equalsIgnoreCase("C3")) {
				System.out.println("Enter (A-C,1-3)!");
				continue;
			}
			
			/*
			 * Checks to see if the spot if empty before placing the game piece.
			 */
			
			if (userChoice.equalsIgnoreCase("A1") && !gameBoard[1][1].equals(" ") || (userChoice.equalsIgnoreCase("A2") && !gameBoard[3][1].equals(" ")) || (userChoice.equalsIgnoreCase("A3") && !gameBoard[5][1].equals(" ")) ||
					(userChoice.equalsIgnoreCase("B1") && !gameBoard[1][3].equals(" ")) || (userChoice.equalsIgnoreCase("B2") && !gameBoard[3][3].equals(" ")) || (userChoice.equalsIgnoreCase("B3") && !gameBoard[5][3].equals(" ")) ||
						(userChoice.equalsIgnoreCase("C1") && !gameBoard[1][5].equals(" ")) || (userChoice.equalsIgnoreCase("C2") && !gameBoard[3][5].equals(" ")) || (userChoice.equalsIgnoreCase("C3") && !gameBoard[5][5].equals(" "))) {
				System.out.println("Spot taken, enter again.");
				continue;
			}
			break;
		}
			
			placePlayerPiece(gameBoard,userChoice);
			printGameBoard(gameBoard);
			
			if (checkWinnerUser(gameBoard)) {
				playerWins++;
				gameOver();
			}
			
			if (checkFull(gameBoard)) {
				System.out.println("That is a tie.");
				ties++;
				gameOver();
			}
		}
	}
	
	/**
	 * This method prints the gameBoard based off of a nested for loop.
	 * @param gameBoard
	 */
	
	public static void printGameBoard(String[][] gameBoard) {
		for (int i = 0; i < gameBoard.length; i ++) {
			for (int j = 0; (j < gameBoard[i].length); j++) {
				System.out.print(gameBoard[i][j]+ " ");
			}
			System.out.println();
		}
	}
		
	/**
	 * This method places the user's "x" into the gameBoard Array, based on their input.
	 * @param gameBoard
	 * @param userChoice
	 */
	
	public static void placePlayerPiece(String[][] gameBoard, String userChoice) {
		
			if (userChoice.equalsIgnoreCase("A1")) {
				gameBoard[1][1] = "x";
			}
			if (userChoice.equalsIgnoreCase("A2")) {
				gameBoard[3][1] = "x";
			}
			if (userChoice.equalsIgnoreCase("A3")) {
				gameBoard[5][1] = "x";
			}
			if (userChoice.equalsIgnoreCase("B1")) {
				gameBoard[1][3] = "x";
			}
			if (userChoice.equalsIgnoreCase("B2")) {
				gameBoard[3][3] = "x";
			}
			if (userChoice.equalsIgnoreCase("B3")) {
				gameBoard[5][3] = "x";
			}
			if (userChoice.equalsIgnoreCase("C1")) {
				gameBoard[1][5] = "x";
			}
			if (userChoice.equalsIgnoreCase("C2")) {
				gameBoard[3][5] = "x";
			}
			if (userChoice.equalsIgnoreCase("C3")) {
				gameBoard[5][5] = "x";
			}
			
	}
		
	/**
	 *  This method places the computer's piece based off of a randomly generated number, 1-9. Slightly recursive!
	 * @param gameBoard
	 * @param computerChoice
	 */
	
	public static void placeComputerPiece(String[][] gameBoard, int computerChoice) {
		
		if (checkFull(gameBoard)) {
			System.out.println("Can't Place Piece!");
			return;
		}
		
		switch(computerChoice) {
		
		case 1: 
			if (gameBoard[1][1].equals(" ")) {
				gameBoard[1][1] = "o";
				break;
			}
		case 2: 
			if (gameBoard[3][1].equals(" ")) {
				gameBoard[3][1] = "o";
				break;
			}
		case 3: 
			if (gameBoard[5][1].equals(" ")) {
				gameBoard[5][1] = "o";
				break;
			}
		case 4:
			if (gameBoard[1][3].equals(" ")) {
				gameBoard[1][3] = "o";
				break;
			}
		case 5:
			if (gameBoard[3][3].equals(" ")) {
				gameBoard[3][3] = "o";
				break;
			}
		case 6:
			if (gameBoard[5][3].equals(" ")) {
				gameBoard[5][3] = "o";
				break;
			}
		case 7: 
			if (gameBoard[1][5].equals(" ")) {
				gameBoard[1][5] = "o";
				break;
			}
		case 8:
			if (gameBoard[3][5].equals(" ")) {
				gameBoard[3][5] = "o";
				break;
			}
		case 9:
			if (gameBoard[5][5].equals(" ")) {
				gameBoard[5][5] = "o";
				break;
			}
			
			default: 
				placeComputerPiece(gameBoard,1);
		}
		
	}
	

	/**
	 * This method checks the gameBoard to see if the user wins based on if the user's inputs match 3 in a row, or column, or across.
	 * @param gameBoard
	 * @return
	 */
	
	public static boolean checkWinnerUser(String[][] gameBoard) {
			
			//A1-B1-C1
			if(gameBoard[1][1] == "x" && gameBoard[1][3] == "x" && gameBoard[1][5] == "x") {
				System.out.printf("You win (A1-B1-C1)!");
				return true;
			}
			//A2-B2-C2
			if(gameBoard[3][1] == "x" && gameBoard[3][3] == "x" && gameBoard[3][5] == "x") {
				System.out.printf("You win (A2-B2-C2)!");
				return true;
			}
			//A3-B3-C3
			if(gameBoard[5][1] == "x" && gameBoard[5][3] == "x" && gameBoard[5][5] == "x") {
				System.out.printf("You win (A3-B3-C3)!");
				return true;
			}
			//A1-A2-A3
			if(gameBoard[1][1] == "x" && gameBoard[3][1] == "x" && gameBoard[5][1] == "x") {
				System.out.printf("You win (A1-A2-A3)!");
				return true;
			}
			//B1-B2-B3
			if(gameBoard[1][3] == "x" && gameBoard[3][3] == "x" && gameBoard[5][3] == "x") {
				System.out.printf("You win (B1-B2-B3)!");
				return true;
			}
			//C1-C2-C3
			if(gameBoard[1][5] == "x" && gameBoard[3][5] == "x" && gameBoard[5][5] == "x") {
				System.out.printf("You win (C1-C2-C3)!");
				return true;
			}
			//A2-B2-C3
			if(gameBoard[1][1] == "x" && gameBoard[3][3] == "x" && gameBoard[5][5] == "x") {
				System.out.printf("You win (A1-B2-C3)!");
				return true;
			}
			//A3-B2-C1
			if(gameBoard[5][1] == "x" && gameBoard[3][3] == "x" && gameBoard[1][5] == "x") {
				System.out.printf("You win (A3-B2-C1)!");
				return true;
			}
			return false;
		}
	

	/**
	 * This method checks the gameBoard to see if the computer wins based on if the user's inputs match 3 in a row, or column, or across.
	 * @param gameBoard
	 * @return
	 */
	
	public static boolean checkWinnerComputer(String[][] gameBoard) {
			//A1-B1-C1
			if(gameBoard[1][1] == "o" && gameBoard[1][3] == "o" && gameBoard[1][5] == "o") {
				System.out.printf("Computer wins (A1-B1-C1)!");
				return true;
			}
			//A2-B2-C2
			if(gameBoard[3][1] == "o" && gameBoard[3][3] == "o" && gameBoard[3][5] == "o") {
				System.out.printf("Computer wins (A2-B2-C2)!");
				return true;
			}
			//A3-B3-C3
			if(gameBoard[5][1] == "o" && gameBoard[5][3] == "o" && gameBoard[5][5] == "o") {
				System.out.printf("Computer wins (A3-B3-C3)!");
				return true;
			}
			//A1-A2-A3
			if(gameBoard[1][1] == "o" && gameBoard[3][1] == "o" && gameBoard[5][1] == "o") {
				System.out.printf("Computer wins (A1-A2-A3)!");
				return true;
			}
			//B1-B2-B3
			if(gameBoard[1][3] == "o" && gameBoard[3][3] == "o" && gameBoard[5][3] == "o") {
				System.out.printf("Computer wins (B1-B2-B3)!");
				return true;
			}
			//C1-C2-C3
			if(gameBoard[1][5] == "o" && gameBoard[3][5] == "o" && gameBoard[5][5] == "o") {
				System.out.printf("Computer wins (C1-C2-C3)!");
				return true;
			}
			//A2-B2-C3
			if(gameBoard[1][1] == "o" && gameBoard[3][3] == "o" && gameBoard[5][5] == "o") {
				System.out.printf("Computer wins (A1-B2-C3)!");
				return true;
			}
			//A3-B2-C1
			if(gameBoard[5][1] == "o" && gameBoard[3][3] == "o" && gameBoard[1][5] == "o") {
				System.out.printf("Computer Wins (A3-B2-C1)!");
				return true;
			}
			return false;
	}
	
	/**
	 * This method checks to see if the gameBoard is full based of if there is a "space" in the index.
	 * @param gameBoard
	 * @return
	 */
	public static boolean checkFull(String[][] gameBoard) {
		
		if (!gameBoard[1][1].equals(" ") && !gameBoard[3][1].equals(" ") && !gameBoard[5][1].equals(" ") && !gameBoard[1][3].equals(" ")
			&& !gameBoard[3][3].equals(" ")  && !gameBoard[5][3].equals(" ") && !gameBoard[1][5].equals(" ")  && !gameBoard[3][5].equals(" ")  && !gameBoard[5][5].equals(" ")) {
			return true;
	}
		return false;
	}

	/**
	 * This method opens the file for read.
	 */
	
	public static void openFileForRead() {
		
		try {
		input = new Scanner(Paths.get("GameRecord.txt"));	
		}
		catch (IOException ioException) {
			File myFile = new File("GameRecord.txt");
			try {
				myFile.createNewFile();
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
			try {
				input = new Scanner(Paths.get("GameRecord.txt"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * This method reads from the file.
	 */
	
	public static void readFromFile () {
		
		 if (!input.hasNextLine()) {
			 computerWins = 0;
			 playerWins = 0;
			 ties = 0;
			 return;
		 }
		 String lineReadFromFile = input.nextLine();
		 String[] strNums = lineReadFromFile.split(",");
		 computerWins = Integer.parseInt(strNums[0]);
		 playerWins = Integer.parseInt(strNums[1]);
		 ties = Integer.parseInt(strNums[2]);
		 
	}
	
	/**
	 * This method closes the file from read.
	 */
	
	public static void closeFileForRead() {
		if (input != null)
			input.close();
	}
	
	/**
	 * This method opens the file for write.
	 */
	
	public static void openFileForWrite() {
		
		try {
			output = new Formatter("GameRecord.txt");
			}
		    catch (SecurityException securityException) {
		    	System.out.println("Write permission denied. Terminating");
		    	System.exit(1);
		    }
		    catch (FileNotFoundException fileNotFoundException) {
		    	System.out.println("Error opening file. Terminating");
		    	System.exit(1);
		    }
		}
		
	/**
	 * This method writes to the file all of the total records of wins/ties and adds them to a .txt file.
	 */
	
	public static void writeToFile() {
		output.format("%d,%d,%d", computerWins, playerWins, ties);
		}
	
	/**
	 * This method closes the file from write.
	 */
	
		public static void closeFileForWrite() {
		  if (output != null)	
			 output.close();
		}
		
	/**
	 * This method calls all of the write to File methods after a the game is over and displays the records and some percanateges.	
	 */
		
		public static void gameOver() {
			openFileForWrite();
			writeToFile();
			closeFileForWrite();
			System.out.printf("%nComputer Wins: %d, Player Wins: %d, Ties: %d%n", computerWins, playerWins, ties);
			System.out.printf("Player Win Percentage: %.2f%%",(playerWins*1.0/(computerWins+playerWins+ties))*100.0);
			System.exit(0);
		}
}
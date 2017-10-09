package control;

import java.io.IOException;

import enums.Difficulty;
import model.GameBoard;
import model.Player;
import view.Console;
import view.InstructionBox;

public class NimController {
	private static int turnVal;
	private static Enum difficulty;
	private static Player[] players = new Player[2];
	private static GameBoard gameBoard;
	private static InstructionBox instructionBox;
	private static final int PLAYER_ONE_TURN_VAL = 0;
	private static final int PLAYER_TWO_TURN_VAL = 1;

	public static void runApp() throws IOException {
		String playerChoice = "";
		int executable = 0;
		int turn = 0;
		
		String[] versus = {
		"1) Player versus Player",
		"2) Player versus Wall-E the Robot"
		};
		
		String[] difficulty = {
			"1) Easy 2 x 2",
			"2) Medium 2 x 5 x 7",
			"3) Hard 2 x 3 x 8 x 9"
		};
		
		Console.writeLine("Hello, and welcome to the game of Nim");
		while(true) {
			do {
				do {
					Console.writeLine("Who will you be playing against: ");
					playerChoice = Console.promptUserForMenuChoice(versus);
				}while(!isValidInt(playerChoice));
				executable = Integer.parseInt(playerChoice);
				if(executable < 1  || executable > 2) {
					Console.writeLine("\n!!! The number input excedes the bounds of the options !!!\n");
				}
			}while(executable != 1 & executable != 2);
			
			do {
				playerChoice = Console.promptUserForInput("What will be Player 1's name?");
			}while(!isValidPlayerName(playerChoice));
			players[0] = new Player(playerChoice);
			
			do {
				if(executable == 2) {
					playerChoice = "Wall-E";
					players[1] = new Player(playerChoice);
					players[1].setIsHumanPlayer(false);
				} else {
					playerChoice = Console.promptUserForInput("What will be Player 2's name?");
				}
			}while(!isValidPlayerName(playerChoice));
			
			do {
				Console.writeLine("What difficulty setting would you prefer: ");
				playerChoice = Console.promptUserForMenuChoice(difficulty);
			}while(!isValidDifficultyChoice(playerChoice));
			
			String[] first = {
					"1) " + players[0],
					"2) " + players[1]
			};
			
			do {
				do {
					Console.writeLine("Who will go first: ");
					playerChoice = Console.promptUserForMenuChoice(first);
				}while(!isValidInt(playerChoice));
				executable = Integer.parseInt(playerChoice);
				if(executable < 1  || executable > 2) {
					Console.writeLine("\n!!! The number input excedes the bounds of the options !!!\n");
				}
			}while(executable != 1 & executable != 2);
			
			turn = executable - 1;
			
			do {
				playGame();
			}while(!isGameOver());			
		}
	}
	private static void playGame() {
		
	}
	
	private static boolean isValidMoveInput(String userInput) {
		
		return false;
	}
	private static boolean isValidMove(int rowNumber, int tokenCount) {
		
		return true;
	}
	private static int[] generateValidRandomMove() {
		
		return null;
	}
	private static boolean isGameOver() {
		boolean allEmpty = false;
		for(int i = 0; i < gameBoard.getNumOfRows(); i++) {
			if(gameBoard.getRowTokenValue(i) == 0) {
				allEmpty = true;
			}else {
				return false;
			}
		}
		return allEmpty;
	}
	private static boolean isValidDifficultyChoice(String userInput) {
		
		switch(userInput) {
		
		case "1":
			gameBoard = new GameBoard(Difficulty.Easy);
			break;
			
		case "2":
			gameBoard = new GameBoard(Difficulty.Medium);
			break;
			
		case "3":
			gameBoard = new GameBoard(Difficulty.Hard);
			break;
			
		default:
			Console.writeLine("\n!!! The option you requested does not exist. !!!\n!!! Please be sure you input a number between 1 and 3 !!!\n");
			return false;
		}
		return true;
	}
	private static boolean isValidPlayerName(String userInput) {
		userInput.trim();
		if(userInput.length() > 0 && userInput != null) {
			return true;
		}
		Console.writeLine("\n!!! We did not recieve any input. Please be sure to input something !!!\n");
		return false;
	}
	private static boolean isValidInt(String userInput) {
		try {
			int check = Integer.parseInt(userInput);
			return true;
		} catch(NumberFormatException e) {
			Console.writeLine("\n!!! The input recieved was not a number !!!\n");
			return false;
		}
	}
	
}

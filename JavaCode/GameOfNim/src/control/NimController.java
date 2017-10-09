package control;

import java.io.IOException;
import java.util.Random;

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
		boolean again = true;
		String playerChoice = "";
		int executable = 0;
		
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
		while(again) {
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
			players[0].setIsHumanPlayer(true);
			
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
			
			turnVal = executable - 1;
			
			do {
				Console.writeLine(gameBoard.toString() + "\n");
				playGame();
			}while(!isGameOver());
			
			String[] end = {
					"1) yes",
					"2) no"
			};
			
			Console.writeLine("Congradulations: " + players[turnVal].getName() + "\nPlay Again?");
			playerChoice = Console.promptUserForMenuChoice(end);
			
			if(playerChoice.equals(2)) {
				again = false;
			}
		}
	}
	private static void playGame() {

		if (players[turnVal].getIsHumanPlayer()) {
			String response = "";
			String[] split = new String[2];
			do {
				do {
					try {
						response = Console.promptUserForInput(
								players[turnVal] + ") Please input your action in to format: row, amount");
					} catch (IOException e) {
						e.printStackTrace();
					}
				} while (!isValidMoveInput(response));

				split = response.split(",");
			} while (!isValidMove(Integer.parseInt(split[0])-1, Integer.parseInt(split[1])-1));
			gameBoard.takeTokens(Integer.parseInt(split[0])-1, Integer.parseInt(split[1])-1);
		} else {
			int[] split = generateValidRandomMove();
			gameBoard.takeTokens(split[0], split[1]);
		}
		
		if(turnVal == 1) {
			turnVal--;
		} else {
			turnVal++;
		}
	}
	
	private static boolean isValidMoveInput(String userInput) {
		String[] split = userInput.split(",");
		try{
			Integer.parseInt(split[0].trim());
			Integer.parseInt(split[1].trim());
			return true;
		}catch(Exception e) {
			Console.writeLine("\n!!! The format in which you input your action was incorrect !!!\n");
			e.printStackTrace();
		}
		return false;
	}
	private static boolean isValidMove(int rowNumber, int tokenCount) {
		if(gameBoard.getNumOfRows() <= rowNumber-1 && gameBoard.getRowTokenValue(rowNumber) > 0) {
			return true;
		}
		Console.writeLine("\n!!! The number's you input excede either the number of rows available !!!\n!!! or do not have Objects in which to take from. !!!\n");
		return false;
	}
	private static int[] generateValidRandomMove() {
		Random randalf = new Random();
		
		int rand = randalf.nextInt(gameBoard.getNumOfRows());
		int amount = randalf.nextInt(gameBoard.getRowTokenValue(rand)-1)+1;
		
		int[] move = {
				rand,
				amount
		};
		
		return move;
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

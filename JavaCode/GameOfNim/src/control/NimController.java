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

	/*
	 * The primary method for controlling the operation of the application
	 */
	public static void runApp() throws IOException {
		boolean again = true;
		String playerChoice = "";
		int executable = 0;

		String[] versus = { "1) Player versus Player", "2) Player versus Wall-E the Robot" };

		String[] difficulty = { "1) Easy 2 x 2", "2) Medium 2 x 5 x 7", "3) Hard 2 x 3 x 8 x 9" };

		instructionBox.show();
		while (again) {
			do {
				do {
					Console.writeLine("Who will you be playing against: ");
					playerChoice = Console.promptUserForMenuChoice(versus);
				} while (!isValidInt(playerChoice));
				executable = Integer.parseInt(playerChoice);
				if (executable < 1 || executable > 2) {
					Console.writeLine("\n!!! The number input excedes the bounds of the options !!!\n");
				}
			} while (executable != 1 & executable != 2);

			do {
				playerChoice = Console.promptUserForInput("What will be Player 1's name?");
			} while (!isValidPlayerName(playerChoice));
			players[0] = new Player(playerChoice);
			players[0].setIsHumanPlayer(true);

			do {
				if (executable == 2) {
					playerChoice = "Wall-E";
					players[1] = new Player(playerChoice);
					players[1].setIsHumanPlayer(false);
				} else {
					playerChoice = Console.promptUserForInput("What will be Player 2's name?");
					players[1] = new Player(playerChoice);
					players[1].setIsHumanPlayer(true);
				}
			} while (!isValidPlayerName(playerChoice));

			do {
				Console.writeLine("What difficulty setting would you prefer: ");
				playerChoice = Console.promptUserForMenuChoice(difficulty);
			} while (!isValidDifficultyChoice(playerChoice));

			String[] first = { "1) " + players[0].getName(), "2) " + players[1].getName() };

			do {
				do {
					Console.writeLine("Who will go first: ");
					playerChoice = Console.promptUserForMenuChoice(first);
				} while (!isValidInt(playerChoice));
				executable = Integer.parseInt(playerChoice);
				if (executable < 1 || executable > 2) {
					Console.writeLine("\n!!! The number input excedes the bounds of the options !!!\n");
				}
			} while (executable != 1 & executable != 2);

			turnVal = executable - 1;

			do {
				Console.writeLine(gameBoard.toString());
				playGame();
			} while (!isGameOver());

			String[] end = { "1) yes", "2) no" };

			Console.writeLine("Congradulations: " + players[turnVal].getName() + "\nPlay Again?");
			playerChoice = Console.promptUserForMenuChoice(end);

			if (playerChoice.equals("2")) {
				again = false;
			}
		}
	}

	/*
	 * Controls the logic for one single turn during game play. Controls interaction
	 * logic for either human or computer player and the gameBoard
	 */
	private static void playGame() {

		if (players[turnVal].getIsHumanPlayer()) {
			String response = "";
			String[] split;
			do {
				do {
					response = Console.promptUserForInput(
							players[turnVal].getName() + ") Please input your action in to format: row, amount");
				} while (!isValidMoveInput(response));
				split = response.split(",");
			} while (!isValidMove(Integer.parseInt(split[0].trim()) - 1, Integer.parseInt(split[1].trim())));
			gameBoard.takeTokens(Integer.parseInt(split[0].trim()) - 1, Integer.parseInt(split[1].trim()));
		} else {
			int[] split = generateValidRandomMove();
			gameBoard.takeTokens(split[0], split[1]);
		}

		if (turnVal == 1) {
			turnVal--;
		} else {
			turnVal++;
		}
	}

	/*
	 * Determines if the String retrieved from the user can be parsed into a valid
	 * game move. Input should be in a comma-separated format. (ie: “2, 4”)
	 */
	private static boolean isValidMoveInput(String userInput) {
		String[] split = userInput.split(",");
		try {
			Integer.parseInt(split[0].trim());
			Integer.parseInt(split[1].trim());
			return true;
		} catch (Exception e) {
			Console.writeLine("\n!!! The format in which you input your action was incorrect !!!\n");
		}
		return false;
	}

	/*
	 * Determines if the desired move on the gameBoard is possible. Checks that the
	 * rowNumber exists and that the tokenCount is within the acceptable range of
	 * that rowNumber. Which is 1-(n), where (n) is the number of tokens currently
	 * in that rowNumber
	 */
	private static boolean isValidMove(int rowNumber, int tokenCount) {
		if (rowNumber >= 0 && rowNumber <= gameBoard.getNumOfRows() && gameBoard.getRowTokenValue(rowNumber) > 0
				&& tokenCount > 0 && tokenCount <= gameBoard.getRowTokenValue(rowNumber)) {
			return true;
		}
		Console.writeLine(
				"\n!!! The number's you input excede either the number of rows available !!!\n!!! or has inadiquate number of Objects in which to take. !!!\n");
		return false;
	}

	/*
	 * Randomly generates a move which will be both valid and possible during the
	 * current game state
	 */
	private static int[] generateValidRandomMove() {
		Random randalf = new Random();
		int row;

		do {
			row = randalf.nextInt(gameBoard.getNumOfRows());
		} while (gameBoard.getRowTokenValue(row) == 0);
		int amount = randalf.nextInt(gameBoard.getRowTokenValue(row)) + 1;

		int[] move = { row, amount };

		return move;
	}

	/*
	 * Checks if the current player took the last token
	 */
	private static boolean isGameOver() {
		boolean allEmpty = false;
		for (int i = 0; i < gameBoard.getNumOfRows(); i++) {
			if (gameBoard.getRowTokenValue(i) == 0) {
				allEmpty = true;
			} else {
				return false;
			}
		}
		return allEmpty;
	}

	/*
	 * Determines if the user’s input is able to be parsed into a Difficulty Enum
	 * value
	 * 
	 */
	private static boolean isValidDifficultyChoice(String userInput) {

		switch (userInput) {

		case "1":
			gameBoard = new GameBoard(Difficulty.EASY);
			break;

		case "2":
			gameBoard = new GameBoard(Difficulty.MEDIUM);
			break;

		case "3":
			gameBoard = new GameBoard(Difficulty.HARD);
			break;

		default:
			Console.writeLine(
					"\n!!! The option you requested does not exist. !!!\n!!! Please be sure you input a number between 1 and 3 !!!\n");
			return false;
		}
		return true;
	}

	/*
	 * Determines if the user’s input is not null nor empty
	 */
	private static boolean isValidPlayerName(String userInput) {
		userInput.trim();
		if (userInput.length() > 0 && userInput != null) {
			return true;
		}
		Console.writeLine("\n!!! We did not recieve any input. Please be sure to input something !!!\n");
		return false;
	}

	/*
	 * Determines if the user’s input can be parsed into an
	 */
	private static boolean isValidInt(String userInput) {
		try {
			int check = Integer.parseInt(userInput);
			return true;
		} catch (NumberFormatException e) {
			Console.writeLine("\n!!! The input recieved was not a number !!!\n");
			return false;
		}
	}

}

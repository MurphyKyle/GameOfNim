package control;

import model.GameBoard;
import model.Player;
import view.InstructionBox;

public class NimController {
	private int turnVal;
	private Enum difficulty;
	private Player[] players = new Player[2];
	private GameBoard gameBoard;
	private InstructionBox instructionBox;
	private static final int PLAYER_ONE_TURN_VAL = 1;
	private static final int PLAYER_TWO_TURN_VAL = 2;

	public static void runApp() {
		
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
		
		return false;
	}
	private static boolean isValidDifficultyChoice(String userInput) {
		
		return false;
	}
	private static boolean isValidPlayerName(String userInput) {
		
		return false;
	}
	private static boolean isValidInt(String userInput) {
		
		return false;
	}
	
}

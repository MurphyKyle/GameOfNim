package model;

public class GameBoard {
	int[] rows;
	enums.Difficulty difficulty;
	
	public GameBoard(enums.Difficulty difficulty) {
		this.difficulty = difficulty;
	}
	
	public int getRowTokenValue(int rowNumber){
		return 0;
	}
	public int getNumOfRows() {
		return 0;
	}
	public void takeTokens(int rowNumber, int numOfTokens) {
		
	}
	
	@Override
	public String toString() {
		return "";
	}
}

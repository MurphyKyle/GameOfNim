package model;

public class GameBoard {
	private int[] rows;
	private enums.Difficulty difficulty;
	
	public GameBoard(enums.Difficulty difficulty) {
		this.difficulty = difficulty;
	}
	
	public int getRowTokenValue(int rowNumber){
		
		int num = rows[rowNumber];
		
		return num;
	}
	
	public int getNumOfRows() {
		
		int num = rows.length;
		
		return num;
	}
	public void takeTokens(int rowNumber, int numOfTokens) {
		
		if(numOfTokens > rows[rowNumber]){
			throw new IllegalArgumentException("Broke inside the game board model, validation broke inside the Nim Controller");
		}else{
			rows[rowNumber] -= numOfTokens;
		}
	}
	
	@Override
	public String toString() {
		
		for(int num : rows){
			
			for (int i = 0; i < num; i++) {
				System.out.println("X");
			}
		}
		
		
		return "";
	}
}

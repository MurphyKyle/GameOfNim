package enums;

import java.util.Arrays;

public enum Difficulty {
	EASY(new int[] {2, 2}),
	MEDIUM(new int[]{2, 5, 7}),
	HARD(new int[]{2, 3, 8, 9});
	
	private int[] rowTokenCounts;
	
	private Difficulty(int... rowTokenCounts) {
		this.rowTokenCounts = Arrays.copyOf(rowTokenCounts, rowTokenCounts.length);
	}
	
	public int[] getRowTokenCounts() {
		return this.rowTokenCounts;
	}
}

package model;

public class Player {
	private String name;
	private boolean isHumanPlayer;
	
	public Player(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void setIsHumanPlayer(boolean isHumanplayer) {
		this.isHumanPlayer = isHumanplayer;
	}
	public boolean getIsHumanPlayer() {
		return isHumanPlayer;
	}
	
	@Override
	public String toString() {
		return "";
	}
	
}

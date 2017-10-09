package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Console {	
	/**
	 * Writes a single message to the console with a carriage return
	 * @param message - The String message to be displayed to the user
	 * @return None
	 */
	public static void writeLine(String message) {
		System.out.println(message);
	}
	
	
	/**
	 * Writes each message from a collection. Each message is written on 
	 * its own line and is followed by a carriage return
	 * @param messages - The collection of messages to be displayed to the user
	 * @return None
	 */
	public static <T> void writeCollection(ArrayList<T> messages) {

		for (T obj : messages) {
			System.out.println(String.valueOf(obj));
		}
	}
	
	
	/**
	 * Reads a single line as String input from the windows console
	 * @param None
	 * @return The String retrieved from the windows console
	 */
	public static String readLine() {
		String result = null;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		try {
//		try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
			result = reader.readLine(); 
		} catch (IOException e) {
			System.err.println("Something went wrong!");
			e.printStackTrace();
		}
		
		return result;
	}
	
	
	/**
	 * Asks the user a question and waits for their response
	 * @param prompt - The String question to pose to the user via the windows console
	 * @return The String retrieved from the windows console representing the user's choice
	 */
	public static String promptUserForInput(String prompt) {
		writeLine(prompt);
		return readLine();
	}
	
	
	/**
	 * Prints out a collection of options for the user to choose from, and waits for their response
	 * @param options - String[] containing the collection of options to 
	 * display to the user at the windows console
	 * @return The String retrieved from the windows console representing the user's choice
	 */
	public static String promptUserForMenuChoice(String[] options) {
		for (String s : options) {
			writeLine(s);
		}
		
		return readLine();
	}
}

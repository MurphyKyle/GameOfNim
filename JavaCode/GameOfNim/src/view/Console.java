package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Console {
	public static void writeLine(String message) {
		System.out.println(message);
	}
	public static <T> void writeCollection(List<T> messages) {
		messages.stream().forEach(m -> System.out.println(m));
	}
	public static String readLine() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		return br.readLine();
	}
	public static String promptUserForInput(String Prompt) throws IOException {
		writeLine(Prompt);
		return readLine();
	}
	public static String promptUserForMenuChoice(String[] options) throws IOException {
		List<String> messages = new ArrayList<>();
		for(String s : options) {
			messages.add(s);
		}
		writeCollection(messages);
		return readLine();
	}
}

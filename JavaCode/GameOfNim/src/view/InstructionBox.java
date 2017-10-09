package view;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class InstructionBox extends Application {

	/**
	 * Displays an un-closeable window to display the given String value to the user
	 * @param displayData
	 * @return None
	 */
	public static void show(String displayData) {
		Stage stage = new Stage();
		stage.setTitle("Game of Nim");
		stage.setWidth(300.0);
		stage.setHeight(400.0);
		
		VBox vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		
		Label lblWelcome = new Label();
		lblWelcome.setAlignment(Pos.CENTER);
		lblWelcome.setText("Welcome to the Game of Nim!");
		
		Region region = new Region();
		
		Label lblInstructions = new Label();
		lblInstructions.setAlignment(Pos.CENTER);
		lblInstructions.setText(displayData);
		
		vbox.getChildren().addAll(lblWelcome, region, lblInstructions);
		
		StackPane pane = new StackPane();
		pane.getChildren().add(vbox);
	}

	
	/**
	 * Displays an un-closeable window to display the given collection of Strings to the user
	 * @param messages - The collection of Strings to display to the user
	 * @return None
	 */
	public static void show(ArrayList<String> messages) {
		
	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		
	}
}

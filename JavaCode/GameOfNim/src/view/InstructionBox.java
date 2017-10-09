package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.Border;

public class InstructionBox {
	/**
	 * Displays an un-closeable window to display the InstructionBox to the user
	 * @param displayData
	 * @return None
	 */
	public static void show() {
		Border paddingBorder = BorderFactory.createEmptyBorder(10,10,10,10);
		
		JFrame frame = new JFrame("Game of Nim");
		frame.setSize(520, 400);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		Panel panel = new Panel(new BorderLayout());
		panel.setSize(frame.getWidth() - 10, frame.getHeight() - 10);
		panel.setBackground(Color.LIGHT_GRAY);
		
		JLabel lblWelcome = new JLabel();
		lblWelcome.setBorder(paddingBorder);
		lblWelcome.setFont(new Font("Arial",0, 26));
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setText("Welcome to the Game of Nim!");
		
		StringBuilder sb = new StringBuilder();
		sb.append("<html>");
		sb.append("Instructions for gameplay:<br><br>");
		sb.append("You must take no less than one token, and can take no more than what is currently in that row<br><br>");
		sb.append("The tokens must be taken out of one single row each turn<br><br>");
		sb.append("The loser is the player who takes the last token on the game<br>");
		sb.append("</html>");
		
		JLabel lblInstructions = new JLabel();
		lblInstructions.setBorder(paddingBorder);		
		lblInstructions.setFont(new Font("Arial", 0, 22));
		lblInstructions.setText(sb.toString());		
		
		panel.add(lblWelcome, BorderLayout.NORTH);
		panel.add(lblInstructions, BorderLayout.CENTER);
		
		frame.add(panel);
		frame.setVisible(true);
	}
}

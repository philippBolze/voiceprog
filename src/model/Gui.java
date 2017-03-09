package model;

import javax.swing.*;
import javax.swing.UIManager;

public class Gui {

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		JFrame window = new JFrame();
		window.setVisible(true);
		window.setTitle("Voice2Key");
		window.setSize(647, 400);
		window.setLocationRelativeTo(null);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		window.add(panel);
		JButton button = new JButton("Button");
		panel.add(button);

	}

}

package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.UIManager;

public class Gui {
	
	
	public Gui() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		JFrame frame = new JFrame("voice2key");  
		final JTextField textField = new JTextField();  
		textField.setBounds(50, 50, 200, 20);  
		JButton button = new JButton("Start Listening");  
		button.setBounds(50, 100, 150, 30);  
		
		ActionListener buttonListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {  
	            textField.setText("voice2key is now listening...");  
	            new Sphinx4Runner();
			}  
		};
				
		button.addActionListener(buttonListener);  
		frame.add(button);
		frame.add(textField);  
		frame.setSize(400,400);  
		frame.setLayout(null);  
		frame.setVisible(true);  

	}


	public static void main(String[] args) {

		@SuppressWarnings("unused")
		Gui window = new Gui();

	}

}

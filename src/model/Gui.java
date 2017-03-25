
package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.UIManager;

public class Gui {
	
	JFrame frame;
	JButton startListeningButton;
	JButton stopListeningButton;
	JButton createCommandButton;
	JTextField textField;
	Sphinx4Runner listener = new Sphinx4Runner();
	

	public Gui() {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		frame = new JFrame("voice2key");  
		startListeningButton = new JButton("Start Listening");
		stopListeningButton = new JButton("Stop Listening");
		createCommandButton = new JButton("create command");
		textField = new JTextField();  
		
		layout();
		actions();

	}

	public void layout() {
		frame.add(startListeningButton);
		frame.add(stopListeningButton);
		frame.add(createCommandButton);
		frame.add(textField);  
		frame.setLayout(null);  
		frame.setVisible(true);  
		frame.setSize(800,600);  
		textField.setBounds(50, 50, 200, 20);  
		startListeningButton.setBounds(30, 480, 150, 40);  
		stopListeningButton.setBounds(210, 480, 150, 40);  
		
	}

	
	public void actions() {
		ActionListener startButtonListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {  
	            textField.setText("b1");  
	            listener.startSpeechThread();
			}  
		};
		ActionListener stopButtonListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {  
	            textField.setText("b2");  
	            listener.stopSpeechThread();
			}  
		};
		
		startListeningButton.addActionListener(startButtonListener);
		stopListeningButton.addActionListener(stopButtonListener);
	}
	
	public static void main(String[] args) {

		@SuppressWarnings("unused")
		Gui window = new Gui();

	}

}

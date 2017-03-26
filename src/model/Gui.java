
package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.UIManager;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
//import javax.swing.tree.TreeNode;

public class Gui {

	JFrame frame = new JFrame("voice2key");

	// command tree
	JScrollPane scrollPane;

	JButton startListeningButton;
	JButton stopListeningButton;

	JButton createCommandButton;
	JButton deleteCommandButton;

	//
	JLabel commandLabel;
	JLabel urlLabel;
	JTextField commandTextField;
	JTextField urlTextField;
	JButton confirmCommandButton;
	JButton cancelCommandButton;

	Sphinx4Runner listener;

	public Gui() {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Wurzel");
		JTree tree = new JTree(root);

		commandLabel = new JLabel("Command:");
		urlLabel = new JLabel("URL:");
		commandTextField = new JTextField();
		urlTextField = new JTextField();

		scrollPane = new JScrollPane(tree);
		startListeningButton = new JButton("Start Listening");
		stopListeningButton = new JButton("Stop Listening");
		createCommandButton = new JButton("create new command");
		deleteCommandButton = new JButton("delete command");
		confirmCommandButton = new JButton("Enter");
		cancelCommandButton = new JButton("Cancel");

		layout();
		actions();
	}

	public void layout() {
		frame.add(scrollPane);
		frame.add(commandLabel);
		frame.add(urlLabel);
		frame.add(startListeningButton);
		frame.add(stopListeningButton);
		frame.add(createCommandButton);
		frame.add(commandTextField);
		frame.add(urlTextField);
		frame.add(deleteCommandButton);
		frame.add(createCommandButton);
		frame.add(confirmCommandButton);
		frame.add(cancelCommandButton);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setSize(800, 600);
		scrollPane.setBounds(30, 20, 300, 300);

		createCommandButton.setBounds(360, 20, 200, 40);
		deleteCommandButton.setBounds(360, 80, 200, 40);

		commandLabel.setBounds(360, 130, 400, 30);
		commandTextField.setBounds(360, 160, 320, 30);
		urlLabel.setBounds(360, 200, 400, 30);
		urlTextField.setBounds(360, 230, 320, 30);
		confirmCommandButton.setBounds(360, 280, 150, 40);
		cancelCommandButton.setBounds(530, 280, 150, 40);

		startListeningButton.setBounds(30, 480, 150, 40);
		stopListeningButton.setBounds(210, 480, 150, 40);

		commandLabel.setVisible(false);
		urlLabel.setVisible(false);
		commandTextField.setVisible(false);
		urlTextField.setVisible(false);
		confirmCommandButton.setVisible(false);
		cancelCommandButton.setVisible(false);

	}

	public void actions() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ActionListener createButtonListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				commandLabel.setVisible(true);
				urlLabel.setVisible(true);
				commandTextField.setVisible(true);
				urlTextField.setVisible(true);
				confirmCommandButton.setVisible(true);
				cancelCommandButton.setVisible(true);
			}
		};
		ActionListener cancelButtonListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				commandLabel.setVisible(false);
				urlLabel.setVisible(false);
				commandTextField.setVisible(false);
				urlTextField.setVisible(false);
				confirmCommandButton.setVisible(false);
				cancelCommandButton.setVisible(false);
			}
		};
		ActionListener startButtonListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listener = new Sphinx4Runner();
				listener.startSpeechThread();
			}
		};
		ActionListener stopButtonListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.stopSpeechThread();
			}
		};

		createCommandButton.addActionListener(createButtonListener);
		cancelCommandButton.addActionListener(cancelButtonListener);
		startListeningButton.addActionListener(startButtonListener);
		stopListeningButton.addActionListener(stopButtonListener);
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Gui window = new Gui();
	}

}

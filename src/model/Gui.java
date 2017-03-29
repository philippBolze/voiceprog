
package model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;

public class Gui {

	JFrame frame = new JFrame("voice2key");

	// command tree
	JScrollPane scrollPane;
	JTree tree;

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
	CommandStateTree commandStateTree;
	XMLParser loader;

	TreePath selectionPath;

	public Gui() {

		/* native look and feel */
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		/* Build Menu */
		// Border bo = new LineBorder(Color.yellow);
		JMenuBar bar = new JMenuBar();
		// bar.setBorder(bo);
		JMenu menu = new JMenu("File");
		bar.add(menu);
		frame.setJMenuBar(bar);
		menu.insert(new JMenuItem("Save"), 0);
		menu.insert(new JMenuItem("Load"), 1);

		buildCommandTree();

		commandLabel = new JLabel("Command:");
		urlLabel = new JLabel("URL:");
		commandTextField = new JTextField();
		urlTextField = new JTextField();

		scrollPane = new JScrollPane(tree);
		startListeningButton = new JButton("Start Listening");
		stopListeningButton = new JButton("Stop Listening");
		createCommandButton = new JButton("create new command");
		deleteCommandButton = new JButton("Delete Command");
		confirmCommandButton = new JButton("Enter");
		cancelCommandButton = new JButton("Cancel");

		layout();
		actions();
	}

	private void buildCommandTree() {

		loader = new XMLParser("resources/grammars/commands.xml", "resources/grammars/grammar.gram");

		commandStateTree = new CommandStateTree(loader);

		listener = new Sphinx4Runner();

		listener.setCommandStateTree(commandStateTree);

		tree = new JTree(loader.getRootTreeNode());
	}

	private void layout() {
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

		createCommandButton.setEnabled(false);
		deleteCommandButton.setEnabled(false);

	}

	public void actions() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ActionListener confirmButtonListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loader.addCommand(selectionPath, "fun", "111.111.111.111");
			}
		};
		ActionListener createButtonListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				commandLabel.setVisible(true);
				urlLabel.setVisible(true);
				commandTextField.setVisible(true);
				urlTextField.setVisible(true);
				confirmCommandButton.setVisible(true);
				cancelCommandButton.setVisible(true);

				deleteCommandButton.setEnabled(false);
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
				deleteCommandButton.setEnabled(true);
			}
		};
		ActionListener deleteButtonListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new DeleteCommandDialog();
			}
		};
		ActionListener startButtonListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.startSpeechThread();
			}
		};
		ActionListener stopButtonListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				listener.stopSpeechThread();
			}
		};

		deleteCommandButton.addActionListener(deleteButtonListener);
		createCommandButton.addActionListener(createButtonListener);
		cancelCommandButton.addActionListener(cancelButtonListener);
		startListeningButton.addActionListener(startButtonListener);
		stopListeningButton.addActionListener(stopButtonListener);
		confirmCommandButton.addActionListener(confirmButtonListener);

		tree.addTreeSelectionListener(createSelectionListener());

	}

	private TreeSelectionListener createSelectionListener() {
		return new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				createCommandButton.setEnabled(true);
				deleteCommandButton.setEnabled(true);
				selectionPath = e.getPath();
			}
		};
	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		Gui window = new Gui();
	}

}

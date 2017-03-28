package model;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.UIManager;

public class DeleteCommandDialog extends JDialog {

	private static final long serialVersionUID = 2019784532217781110L;
	JLabel deleteText;
	JButton confirmButton;
	JButton cancelButton;

	public DeleteCommandDialog() {

		this.setSize(420, 200);
		this.setModal(true);

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		deleteText = new JLabel("Do you really want to delete this command?");
		confirmButton = new JButton("Delete");
		cancelButton = new JButton("Cancel");

		this.add(deleteText);
		this.add(confirmButton);
		this.add(cancelButton);

		deleteText.setBounds(40, 25, 400, 30);
		confirmButton.setBounds(80, 90, 100, 30);
		cancelButton.setBounds(210, 90, 100, 30);

		this.setLayout(null);
		this.setVisible(true);

		// ActionListener confirmButtonListener = new ActionListener() {
		// @Override
		// public void actionPerformed(ActionEvent e) {
		//
		// }
		// };
		//
		// ActionListener cancelButtonListener = new ActionListener() {
		// @Override
		// public void actionPerformed(ActionEvent e) {
		//
		// }
		// };

	}

	public static void main(String[] args) {
		@SuppressWarnings("unused")
		DeleteCommandDialog window = new DeleteCommandDialog();
	}
}

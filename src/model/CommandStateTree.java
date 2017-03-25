package model;

public class CommandStateTree {

	private CommandState currentState;

	// starting state
	final private CommandState rootState;

	// command that sets back the tree to start
	final private String breakWord;

	// combined words that led to the current state
	private String fullCommand;

	// TODO: Speech recognition is locked
	// private boolean locked;

	// The constructor should get the path to commands.xml as parameter
	public CommandStateTree() {
		rootState = XMLParser.readXML("resources/grammars/commands.xml","resources/grammars/grammar.gram");

		// should also be read from XML file. TODO: implement XMLParser.getBreakWord();
		breakWord = "break";

		// At start up the current State should be the root state
		currentState = rootState;
		fullCommand = rootState.getWord();
	}

	public void tryNewWord(String spokenInput) {

		// Reset tree if new word is break word
		if (spokenInput.equals(breakWord)) {
			setBack();
			System.out.println(breakWord);
			return;
		}

		// try to get next state with the name of the new word
		CommandState newCommandState = currentState.getChild(spokenInput);

		// check if currentState has no matching child
		// else matching child becomes new currentState
		if (newCommandState == null) {
			System.out.println("no match");
		} else {
			currentState = newCommandState;
			fullCommand += " " + currentState.getWord();
			System.out.println(fullCommand.substring(1));
			currentState.action();
			if (currentState.isLeaf()) {
				setBack();
			}
		}
	}

	// Set current state back to start
	public void setBack() {
		currentState = rootState;
		fullCommand = rootState.getWord();
	}

	public String getFullCommand() {
		if (currentState == rootState) {
			return "";
		} else {
			return fullCommand.substring(1);
		}
	}

}
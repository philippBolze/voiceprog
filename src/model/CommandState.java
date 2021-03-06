package model;

import java.util.HashMap;

public class CommandState {

	// Latest recognized word that led to this state
	private String spoken;

	// Hash map of states that might come after this State
	private HashMap<String, CommandState> children = new HashMap<String, CommandState>();

	private KeySequence keySequence;
	private HttpCommand httpCommand;

	public CommandState(String spoken) {
		this.spoken = spoken;
	}

	public void addKeyString(String keyString) {
		if (keySequence == null) {
			keySequence = new KeySequence();
		}
		keySequence.pushString(keyString);
	}

	public void addKeyKombination(int key_1) {
		if (keySequence == null) {
			keySequence = new KeySequence();
		}
		keySequence.pushKeyCombination(key_1);
	}

	public void addKeyKombination(int key_1, int key_2) {
		if (keySequence == null) {
			keySequence = new KeySequence();
		}
		keySequence.pushKeyCombination(key_1, key_2);
	}

	public void addKeyKombination(int key_1, int key_2, int key_3) {
		if (keySequence == null) {
			keySequence = new KeySequence();
		}
		keySequence.pushKeyCombination(key_1, key_2, key_3);
	}

	public void addHttpCommand(String url) {
		if (httpCommand == null) {
			httpCommand = new HttpCommand();
		}
		httpCommand.pushHttpCommand(url);
	}

	public void addChild(CommandState newChild) {
		children.put(newChild.getWord(), newChild);
	}

	public String getWord() {
		return spoken;
	}

	public CommandState getChild(String newWord) {
		return children.get(newWord);
	}

	public void action() {
		if (keySequence != null) {
			keySequence.typeSequence();
		}
		if (httpCommand != null) {
			httpCommand.sendCommand();
		}
	}

	public boolean isLeaf() {
		return children.isEmpty();
	}

}
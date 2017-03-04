package model;

import java.util.HashMap;

public class CommandState {
	
	//Latest recognized word that led to this state
	private String word;
	
	//Hashmap of states that might come after this State
	private HashMap<String, CommandState> children = new HashMap<String, CommandState>();
    
    private KeySequence keySequence;
	
	public CommandState(String word) {
		this.word = word;
	}
	
	public void addKeyString(String keyString) {
		if(keySequence == null) {
			keySequence = new KeySequence();
		}
		keySequence.pushString(keyString);
	}
	
	public void addKeyKombination(int key_1) {
		if(keySequence == null) {
			keySequence = new KeySequence();
		}
		keySequence.pushKeyCombination(key_1);
	}
	
	public void addKeyKombination(int key_1, int key_2) {
		if(keySequence == null) {
			keySequence = new KeySequence();
		}
		keySequence.pushKeyCombination(key_1, key_2);
	}
	
	public void addKeyKombination(int key_1, int key_2, int key_3) {
		if(keySequence == null) {
			keySequence = new KeySequence();
		}
		keySequence.pushKeyCombination(key_1, key_2, key_3);
	}
	
	public void addChild(CommandState newChild) {
		children.put(newChild.getWord(), newChild);
	}
	
	public String getWord() {	
		return word;
	}
	
	public CommandState getChild(String newWord) {
		return children.get(newWord);
	}
	
	public void action() {
		if (keySequence != null) {
			keySequence.typeSequence();	
		}
	}
	
	public boolean isLeaf() {
		return children.isEmpty();
	}
	
}
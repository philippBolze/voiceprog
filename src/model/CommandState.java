package model;

import java.util.HashMap;

public class CommandState {
	
	//Latest recognized word that led to this state
	private String word;
	
	//Hashmap of states that might come after this State
	private HashMap<String, CommandState> children = new HashMap<String, CommandState>();
	
	public CommandState(String word) {
		this.word = word;
	}
	
	public void addChild(CommandState newChild) {
		children.put(newChild.getWord(), newChild);
	}
	
	public CommandState getChild(String newWord) {
		return children.get(newWord);
	}

	public String getWord() {
		return word;
	}
	
	//Is not tested and used currently. Can be deleted?
	public boolean isLeaf() {
		return children.isEmpty();
	}
	
}
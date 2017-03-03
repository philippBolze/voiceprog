package model;

import java.util.HashMap;

public class CommandState {
	
	private String word;
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
	
	public boolean isLeaf() {
		return children.isEmpty();
	}
	
}
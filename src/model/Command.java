package model;

import java.util.HashMap;

public class Command {
	
	String word;
	HashMap <String, Command> children;
	boolean leaf;
	
	public Command(String word) {
		this.word = word;
	}

}

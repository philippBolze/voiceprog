package model;

import java.util.HashMap;

public class CommandStateTree {
	
	//Hashmap of Commands which can start a series of commands
	HashMap <String, Command> rootCommands;
	Command currentState;
	
	public CommandStateTree() {
		
	}
	

}

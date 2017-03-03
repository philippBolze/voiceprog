package model;

public class CommandStateTree {
	
	private CommandState currentState;
	final private CommandState rootState;
	private String fullCommand;
	
	public CommandStateTree() {
		//Create empty root State
		rootState = new CommandState("");
		
		//Delete this after dataload from commands.xml is implemented here
		CommandState cmd1 = new CommandState("new");
		CommandState cmd2 = new CommandState("class");	
		CommandState cmd3 = new CommandState("java project");
		CommandState cmd4 = new CommandState("save");
		rootState.addChild(cmd1);
		rootState.addChild(cmd4);
		cmd1.addChild(cmd2);
		cmd1.addChild(cmd3);
		
		//At start up the current State should be the root state
		currentState = rootState;	
		fullCommand = rootState.getWord();
	}
	
	public boolean tryNewWord(String newWord) {
		CommandState newCommandState = currentState.getChild(newWord);
		
		if( newCommandState == null) {
			System.out.println("no match");
			return false;
		} else {
			currentState = newCommandState;
			fullCommand += " " + currentState.getWord(); 
			System.out.println(fullCommand);
			return true;
		}
	}
	
	public void setBack() {
		currentState = rootState;
		fullCommand = rootState.getWord();
	}

	public String getFullCommand() {
		return fullCommand;
	}	

}
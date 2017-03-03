package model;

public class CommandStateTree {
	
	private CommandState currentState;
	
	//starting state
	final private CommandState rootState;
	
	private String breakWord;
	
	//combined words that led to the current state
	private String fullCommand;
	

	
	public CommandStateTree() {
		//Create empty root State
		rootState = new CommandState("");
		
		//Delete this after dataload from commands.xml is implemented here
		breakWord = "break";
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
		
		//Reset tree if new word is break word
		if(newWord.equals(breakWord)) {
			setBack();
			System.out.println(breakWord);
			return true;
		}
		
		//try to get next state with the name of the new word
		CommandState newCommandState = currentState.getChild(newWord);
		
		//check if currentState has no matching child
		//else matching child becomes new currentState
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
	
	
	//Set current state back to start
	public void setBack() {
		currentState = rootState;
		fullCommand = rootState.getWord();
	}

	//Is not in use currently. Can be deleted?
	public String getFullCommand() {
		return fullCommand;
	}	

}
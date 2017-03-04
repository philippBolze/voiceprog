package model;

import java.awt.event.KeyEvent;

public class CommandStateTree {
	
	private CommandState currentState;
	
	//starting state
	final private CommandState rootState;
	
	final private String breakWord;
	
	//combined words that led to the current state
	private String fullCommand;
	
	//Speech recognition is locked
	//private boolean locked;
	
	public CommandStateTree() {
		//Create empty root State
		rootState = new CommandState("");
		
		//Delete this after dataload from commands.xml is implemented here
		breakWord = "break";
		CommandState cmd3 = new CommandState("close");
		CommandState cmd2 = new CommandState("file");
		cmd2.addKeyKombination(KeyEvent.VK_CONTROL, 'W');
		CommandState cmd7 = new CommandState("program");
		cmd7.addKeyKombination(KeyEvent.VK_ALT, KeyEvent.VK_F4);
		CommandState cmd1 = new CommandState("new");
		CommandState cmd4 = new CommandState("other");
		cmd4.addKeyKombination(KeyEvent.VK_CONTROL, 'N');
		CommandState cmd5 = new CommandState("save");
		cmd5.addKeyKombination(KeyEvent.VK_CONTROL, 'S');
		CommandState cmd6 = new CommandState("comment");
		cmd6.addKeyKombination(KeyEvent.VK_CONTROL, KeyEvent.VK_CONTROL, '7');
		rootState.addChild(cmd3);
		rootState.addChild(cmd1);
		rootState.addChild(cmd5);
		rootState.addChild(cmd6);
		cmd1.addChild(cmd4);
		cmd3.addChild(cmd2);
		cmd3.addChild(cmd7);
		
		//At start up the current State should be the root state
		currentState = rootState;	
		fullCommand = rootState.getWord();
	}
	
	public boolean tryNewWord(String newWord) {
		
		//Reset tree if new word is break word
		if (newWord.equals(breakWord)) {
			setBack();
			System.out.println(breakWord);
			return true;
		}
		
		//try to get next state with the name of the new word
		CommandState newCommandState = currentState.getChild(newWord);
		
		//check if currentState has no matching child
		//else matching child becomes new currentState
		if ( newCommandState == null) {
			System.out.println("no match");
			return false;
		} else {
			currentState = newCommandState;
			fullCommand += " " + currentState.getWord(); 
			System.out.println(fullCommand.substring(1));
			if (currentState.action()) {
				 setBack();
			}
			return true;
		}
	}
	
	//Set current state back to start
	public void setBack() {
		currentState = rootState;
		fullCommand = rootState.getWord();
	}

}
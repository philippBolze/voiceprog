package model;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.HashMap;

public class CommandState {
	
	//Latest recognized word that led to this state
	private String word;
	
	//Hashmap of states that might come after this State
	private HashMap<String, CommandState> children = new HashMap<String, CommandState>();
	
	private Integer actionKey_1;
    private Integer actionKey_2; 
    private Integer actionKey_3; 
	
	public CommandState(String word) {
		this.word = word;
	}
	
	public CommandState(String word, int actionKey_1, int actionKey_2) {
		this.word = word;
		this.actionKey_1 = actionKey_1;
	    this.actionKey_2 = actionKey_2;
	}
	
	public CommandState(String word, int actionKey_1, int actionKey_2, int actionKey_3) {
		this.word = word;
		this.actionKey_1 = actionKey_1;
	    this.actionKey_2 = actionKey_2;
	    this.actionKey_3 = actionKey_3;
	}
	
	public void addChild(CommandState newChild) {
		children.put(newChild.getWord(), newChild);
	}
	
	public boolean action() {
		try {
	        // Simulate key press
	        Robot robot = new Robot();
	        if (actionKey_1 != null) robot.keyPress(actionKey_1);
	        if (actionKey_2 != null) robot.keyPress(actionKey_2);
	        if (actionKey_3 != null) robot.keyPress(actionKey_3);
	        if (actionKey_1 != null) robot.keyRelease(actionKey_1);
	        if (actionKey_2 != null) robot.keyRelease(actionKey_2);
	        if (actionKey_3 != null) robot.keyRelease(actionKey_3);
		} catch (AWTException e) {
			e.printStackTrace();
		}
		//return true if action was performed else false
		return (actionKey_1 != null) ? true : false;	
	}

	public String getWord() {	
		return word;
	}
	
	public CommandState getChild(String newWord) {
		return children.get(newWord);
	}
	
}
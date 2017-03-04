package model;

import java.util.ArrayList;
import java.awt.AWTException;
import java.awt.Robot;


public class KeySequence {
	
	ArrayList<ArrayList<Integer>> keySequence = new ArrayList<ArrayList<Integer>>();
	
	public KeySequence() {
		
	}
	
	public void pushKeyCombination(int key) {
		ArrayList<Integer> keyCombination = new ArrayList<Integer>();
		keyCombination.add(key);
		keySequence.add(keyCombination);
	}
	
	public void pushKeyCombination(int key_1, int key_2 ) {
		ArrayList<Integer> keyCombination = new ArrayList<Integer>();
		keyCombination.add(key_1);
		keyCombination.add(key_2);
		keySequence.add(keyCombination);
	}
	
	public void pushKeyCombination(int key_1, int key_2, int key_3) {
		ArrayList<Integer> keyCombination = new ArrayList<Integer>();
		keyCombination.add(key_1);
		keyCombination.add(key_2);
		keyCombination.add(key_3);
		keySequence.add(keyCombination);
	}
	
	public void pushString(String string) {
		for(int i=0; i<string.length(); i++) {
			pushKeyCombination(string.charAt(i));
		}
	}
	
	public boolean action() {
		try {
			Robot robot = new Robot();
			for(int i=0; i<keySequence.size(); i++) {
				ArrayList<Integer> combi = keySequence.get(i);
				for(int j=0; j<combi.size(); j++) {
					robot.keyPress(combi.get(j));
				}
				for(int j=0; j<combi.size(); j++) {
					robot.keyRelease(combi.get(j));
				}	
			}
			return true;
		} catch (AWTException e) {
			e.printStackTrace();
			return false;
		}
	}

}

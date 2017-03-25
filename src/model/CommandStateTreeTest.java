package model;

import static org.junit.Assert.*;

import org.junit.Test;

/*
 * This Junit class should be moved to a different location/folder in the same package
 * 
 *  After the dataload from xml is implemented, there should be a xml config with commands 
 *  which does not simulate important keys
 *  
 *  Is it possible to use a Keylistener?
 *  
 */
public class CommandStateTreeTest {

	@Test
	public void test() {

		CommandStateTree stateTree = new CommandStateTree();
		assertEquals(stateTree.getFullCommand(), "");

		stateTree.tryNewWord("save");
		assertEquals(stateTree.getFullCommand(), "");

		stateTree.setBack();
		assertEquals(stateTree.getFullCommand(), "");

		stateTree.tryNewWord("new");
		assertEquals(stateTree.getFullCommand(), "new");
		stateTree.tryNewWord("not in list");
		assertEquals(stateTree.getFullCommand(), "new");
		stateTree.tryNewWord("break");
		assertEquals(stateTree.getFullCommand(), "");

		stateTree.tryNewWord("close");
		assertEquals(stateTree.getFullCommand(), "close");

		stateTree.tryNewWord("class");
		stateTree.setBack();
	}

}

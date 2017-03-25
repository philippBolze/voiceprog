package model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class XMLParser {
	
	PrintWriter grammarWriter;
	String breakWord;

	public CommandState readXML(String xmlPath, String grammarPath) {
		
		try {
			Document doc = new SAXBuilder().build(xmlPath);
			grammarWriter = new PrintWriter(grammarPath, "UTF-8");
			grammarWriter.println("#JSGF V1.0;");
			grammarWriter.println("grammar grammar;");
			grammarWriter.print("public <commands> = ( ");
			Element root = doc.getRootElement();
			CommandState rootState = new CommandState("");
			recursiveXMLReader(root, rootState);
			breakWord = root. getChildText("break");
			grammarWriter.println(breakWord + " );");
			grammarWriter.close();
			return rootState;
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		// return null in case of Exception
		System.out.println("XML reader failed");
		return null;
	}

	public String getBreakWord() {
		return breakWord;
	}

	private void recursiveXMLReader(Element element, CommandState state) {
		// for all children of this element
		for (Element child : element.getChildren("cmd")) {
			
			grammarWriter.print(child.getAttributeValue("spoken") + " | ");

			// Create a CommandState that represents a node in the
			// CommandStateTree
			CommandState childState = new CommandState(child.getAttributeValue("spoken"));
			state.addChild(childState);

			// Build possible actions for this state
			buildAction(child, childState);

			// recursive call
			recursiveXMLReader(child, childState);
		}
	}

	private void buildAction(Element element, CommandState state) {
		for (Element action : element.getChildren()) {
			if (!action.getName().equals("cmd")) {
				if (action.getName().equals("keyCombination")) {
					List<Element> keys = action.getChildren();
					switch (keys.size()) {
					case 1:
						state.addKeyKombination(Integer.parseInt(keys.get(0).getText()));
						break;
					case 2:
						state.addKeyKombination(Integer.parseInt(keys.get(0).getText()),
								Integer.parseInt(keys.get(1).getText()));
						break;
					case 3:
						state.addKeyKombination(Integer.parseInt(keys.get(0).getText()),
								Integer.parseInt(keys.get(1).getText()), Integer.parseInt(keys.get(2).getText()));
						break;
					default:
						System.out.println("invalid number of key in KeyCombination");
						break;
					}
				} 
				if (action.getName().equals("keySequence")) {
					state.addKeyString(action.getText());
				}
				if (action.getName().equals("http")) {
					state.addHttpCommand(action.getText());
				}
			}
		}
	}

}

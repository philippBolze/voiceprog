package model;

import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class XMLParser {

	CommandState root = new CommandState("");

	public static void main(String[] args) {

		try {
			readXML();
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void readXML() throws JDOMException, IOException {
		Document doc = new SAXBuilder().build("resources/grammars/commands.xml");
		Element root = doc.getRootElement();
		CommandState rootState = new CommandState("");
		recursiveXMLTree(root, rootState);
	}

	private static void recursiveXMLTree(Element element, CommandState state) {
		if (element.getAttributeValue("spoken") != null) {
			System.out.println(element.getAttributeValue("spoken"));
		}
		List<Element> children = element.getChildren("cmd");
		if (children.isEmpty()) {
			return;
		}
		for (Element child : children) {
			CommandState childState = new CommandState(child.getAttributeValue("spoken"));
			state.addChild(childState);
			recursiveXMLTree(child, childState);
		}
	}
	
	public CommandState getRoot() {
		return root;
	}

}

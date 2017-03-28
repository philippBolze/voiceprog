package model;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class XMLParser {

	PrintWriter grammarWriter;
	String breakWord;
	CommandState rootState;
	DefaultMutableTreeNode rootTreeNode;
	Document doc;
	String xmlPath;
	Element root;
	String grammarPath;

	ArrayList<String> completeCommandList = new ArrayList<String>();

	public XMLParser(String xmlPath, String grammarPath) {
		this.xmlPath = xmlPath;
		this.grammarPath = grammarPath;

		buildTreeFromXML();

	}

	private void buildTreeFromXML() {
		try {
			doc = new SAXBuilder().build(xmlPath);
			grammarWriter = new PrintWriter(grammarPath, "UTF-8");
			grammarWriter.println("#JSGF V1.0;");
			grammarWriter.println("grammar grammar;");
			grammarWriter.print("public <commands> = ( ");
			root = doc.getRootElement();
			rootTreeNode = new DefaultMutableTreeNode("Root");
			rootState = new CommandState("");
			recursiveXMLReader(root, rootState, rootTreeNode);
			breakWord = root.getChildText("break");
			grammarWriter.println(breakWord + " );");
			grammarWriter.close();
		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public DefaultMutableTreeNode getRootTreeNode() {
		return rootTreeNode;
	}

	public CommandState getRootState() {
		return rootState;
	}

	public String getBreakWord() {
		return breakWord;
	}

	private void recursiveXMLReader(Element element, CommandState state, DefaultMutableTreeNode treeNode) {
		/* for all children of this element */
		for (Element child : element.getChildren("cmd")) {
			
			String spoken = child.getAttributeValue("spoken");
			
			/* Check if command is already known */
			if (!completeCommandList.contains(spoken)) {
				completeCommandList.add(spoken);
				grammarWriter.print(spoken + " | ");
			}

			/*
			 * Create and add a CommandState that represents a node in the
			 * CommandStateTree
			 */
			CommandState childState = new CommandState(spoken);
			state.addChild(childState);

			/* Create and add a JTree node for GUI */
			DefaultMutableTreeNode treeNodeChild = new DefaultMutableTreeNode(spoken);
			treeNode.add(treeNodeChild);

			/* Build possible actions for this state */
			buildAction(child, childState);

			/* recursive call */
			recursiveXMLReader(child, childState, treeNodeChild);
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

	public void addCommand(TreePath path, String name, String url) {

//		int pathCount = path.getPathCount();
//
//		for (int i = 0; i < pathCount; i++) {
//			System.out.print(path.getPathComponent(i).toString());
//			if (i + 1 != pathCount) {
//				System.out.print("|");
//			}
//		}
//		System.out.println("");

		recursiveAddCommand(root, 1, path, name, url);

		XMLOutputter xmlOutput = new XMLOutputter();

		xmlOutput.setFormat(Format.getPrettyFormat());
		try {
			xmlOutput.output(doc, System.out);
			xmlOutput.output(doc, new FileWriter(xmlPath));
		} catch (IOException e) {
			e.printStackTrace();
		}

		buildTreeFromXML();

	}

	private void recursiveAddCommand(Element element, int index, TreePath path, String name, String url) {

		if (index + 1 != path.getPathCount()) {
			System.out.println("END");
			Element cmd = new Element("cmd");
			cmd.setAttribute("spoken", name);
			Element http = new Element("http");
			http.addContent(url);
			cmd.addContent(http);
			element.addContent(cmd);
			return;
		}

		String cmdString = path.getPathComponent(index).toString();
		System.out.println(cmdString);
		for (Element child : element.getChildren("cmd")) {
			System.out.println("    " + child.getAttributeValue("spoken"));
			if (child.getAttributeValue("spoken").equals(cmdString)) {
				System.out.println("FOUND");
				recursiveAddCommand(child, ++index, path, name, url);

			}

		}

	}

}

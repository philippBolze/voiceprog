package model;

import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class XMLParser {

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
		Document doc = new SAXBuilder().build( "resources/grammars/commands.xml" );
		System.out.println(doc.getRootElement());
		
	}
	
	

}

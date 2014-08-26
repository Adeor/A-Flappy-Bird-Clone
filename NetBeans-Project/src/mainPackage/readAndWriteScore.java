package mainPackage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.jdom2.*;
import org.jdom2.input.*;
import org.jdom2.output.*;

public class readAndWriteScore {
	
	 static Element wurzel;
     static Document doc = null;
     static File scoresFile;
	 
	@SuppressWarnings("rawtypes")
	static String[] read(){
        // eine Parser-Klasse anlegen
        SAXBuilder dom = new SAXBuilder();
        scoresFile = new File("src/res/XML/scores.xml");
        
        if (!scoresFile.exists()) {
        	//creat file! TODO
        	System.out.println("Creat File!!!");
		}
        
        // das Dokument einlesen
		try {
			doc = dom.build(scoresFile);
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Die Wurzel und ihre direkten Kinder ausgeben 
       wurzel = doc.getRootElement();

        java.util.List kinder = wurzel.getChildren();	
		String[] allScores = new String[kinder.size()]; 
        java.util.Iterator it = kinder.iterator();
        Element tmp;

        for (int i = 0; it.hasNext(); i++) {
            tmp = (Element) it.next(); 
            allScores[i] = tmp.getText();
		}
		return allScores;
	}
	
	static void write (String Score){
		 // eine Parser-Klasse anlegen
        SAXBuilder dom = new SAXBuilder();

        // das Dokument einlesen
		try {
			doc = dom.build(new File("src/res/XML/scores.xml"));
		} catch (JDOMException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Die Wurzel und ihre direkten Kinder ausgeben 
       wurzel = doc.getRootElement();
        // zur Wurzel ein neues Element am Ende hinzufuegen
        Element neuerScore = new Element("score");

        Text score = new Text(Score); 
        neuerScore.addContent(score);

        wurzel.addContent(neuerScore); 
        // in eine XML-Datei ausgeben
        FileWriter fw = null;
		try {
			fw = new FileWriter("src/res/XML/scores.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        XMLOutputter ausgabe = new XMLOutputter();
        Format format = ausgabe.getFormat();
        format.setEncoding("iso-8859-1");
        ausgabe.setFormat(format);
        try {
			ausgabe.output(doc, fw);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
}

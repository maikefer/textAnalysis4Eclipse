package exampleanalysis;

import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

import javax.xml.stream.XMLEventFactory;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import textAnalysis.provider.AnalysisProvider;

public class WordCountAnalysis implements AnalysisProvider {

    @Override
    public String getName() {
        return "Word-Count";
    }

    @Override
    public boolean isValid() {
        return true;
    }

	@Override
	public List<XMLEvent> getXMLEvents(List<String> textToAnalyze) {
		List<XMLEvent> events = new LinkedList<>();
		XMLEventFactory eventFactory = XMLEventFactory.newInstance();
		XMLEvent end = eventFactory.createDTD("\n");
		XMLEvent tab = eventFactory.createDTD("\t");
		events.add(end);
		
		for (int i = 0; i < textToAnalyze.size(); i++) {
	    		StartElement sElement = eventFactory.createStartElement("", "", "line");
	    		
	    		StringTokenizer tokens = new StringTokenizer(textToAnalyze.get(i));
	            int count = tokens.countTokens();
	    		Characters characters = eventFactory.createCharacters(String.valueOf(count));
	    		
	    		EndElement eElement = eventFactory.createEndElement("", "", "line");
	    		events.add(tab);
	    		events.add(sElement);
	    		events.add(tab);
	    		events.add(characters);
	    		events.add(tab);
	    		events.add(eElement);
	    		events.add(end);
		}	            
		return events;
	}

}
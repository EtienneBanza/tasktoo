/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package task;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import java.io.Console;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.JSONObject;

public class App {

    public static void main(String[] args) {
        Console console = System.console();
            if (console != null) { 
            Console scanner = System.console();

            String xmlFilePath = console.readLine("Enter the XML file path: ");

            String fieldNamesString = console.readLine("Enter the comma-separated list of field names to output: ");
            String[] fieldNames = fieldNamesString.split(",");

            SAXParserFactory factory = SAXParserFactory.newInstance();
            try {
                SAXParser saxParser = factory.newSAXParser();
                XMLHandler handler = new XMLHandler(fieldNames);
                saxParser.parse(xmlFilePath, handler);
                String jsonOutput = handler.getJsonOutput();
                System.out.println(jsonOutput);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }

        }
        else{
            System.out.println("Please enter an input");
        }
    }

    static class XMLHandler extends DefaultHandler {
        private ArrayList<String> fieldNames;
        private JSONObject currentObject;
        private JSONArray jsonArray;
        private String currentValue;
        private boolean isValue = false;

        public XMLHandler(String[] fieldNames) {
            this.fieldNames = new ArrayList<>();
            for (String fieldName : fieldNames) {
                this.fieldNames.add(fieldName.trim());
            }
            jsonArray = new JSONArray();
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            currentObject = new JSONObject();
            isValue = true;
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if (fieldNames.contains(qName)) {
                try {
                    currentObject.put(qName, currentValue);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            if (qName.equals("record")) {
                jsonArray.put(currentObject);
            }
            isValue = false;
            currentValue = null;
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            if (isValue) {
                currentValue = new String(ch, start, length);
            }
        }

        public String getJsonOutput() {
            return jsonArray.toString();
        }

        
    }
}
    
    


import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLReader {
    public static void main(String[] args) {
        try {
            File inputFile = new File("input.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("record");

            Scanner scanner = new Scanner(System.in);
            ArrayList<String> validFields = new ArrayList<String>();
            validFields.add("name");
            validFields.add("PostalZip");
            validFields.add("Region");
            validFields.add("country");
            validFields.add("address");
            validFields.add("list");

            ArrayList<String> fieldsToOutput = new ArrayList<String>();
            while (fieldsToOutput.size() == 0) {
                System.out.print("Enter comma-separated fields to output (name, postalZip, region, country, address, list  : ");
                String input = scanner.nextLine();
                String[] fields = input.split(",");
                for (String field : fields) {
                    if (validFields.contains(field)) {
                        fieldsToOutput.add(field);
                    } else {
                        System.out.println("Invalid field: " + field);
                    }
                }
            }

            JSONArray jsonArray = new JSONArray();
            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    JSONObject obj = new JSONObject();
                    for (String field : fieldsToOutput) {
                        if (field.equals("name")) {
                                obj.put("Name: " , eElement.getElementsByTagName("name").item(0).getTextContent());
                            } else if (field.equals("postalZip")) {
                                obj.put("PostalZip: " , eElement.getElementsByTagName("postalZip").item(0).getTextContent());
                            } else if (field.equals("region")) {
                                obj.put("Region: " , eElement.getElementsByTagName("region").item(0).getTextContent());
                            
                            } else if (field.equals("country")) {
                                obj.put("Country: " , eElement.getElementsByTagName("country").item(0).getTextContent());  
                            } else if (field.equals("address")) {
                                obj.put("Address: " , eElement.getElementsByTagName("address").item(0).getTextContent());
                            } else if (field.equals("list")) {
                                obj.put("List: " , eElement.getElementsByTagName("list").item(0).getTextContent());
                            
                            }
                    }
                    jsonArray.put(obj);
                }
            }
            System.out.println(jsonArray.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

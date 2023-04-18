import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import java.io.File;
import java.util.Scanner;

public class XMLReader {

  public static void main(String[] args) {

    try {
      File inputFile = new File("C:/Users/Mukenge/Desktop/CSC2023/Software Development Practices/Prac 2/Task 2/data.xml");
      DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
      Document doc = dBuilder.parse(inputFile);
      doc.getDocumentElement().normalize();

      NodeList nList = doc.getElementsByTagName("record");

      Scanner scanner = new Scanner(System.in);
      System.out.print("Enter comma-separated field names to print (name,postalZip,region): ");
      String input = scanner.nextLine();
      String[] fields = input.split(",");

      for (int i = 0; i < nList.getLength(); i++) {
        Node nNode = nList.item(i);

        if (nNode.getNodeType() == Node.ELEMENT_NODE) {
          Element eElement = (Element) nNode;

          for (String field : fields) {
            if (field.equals("name")) {
              System.out.println("Name: " + eElement.getElementsByTagName("name").item(0).getTextContent());
            } else if (field.equals("postalZip")) {
              System.out.println("PostalZip: " + eElement.getElementsByTagName("postalZip").item(0).getTextContent());
            } else if (field.equals("region")) {
              System.out.println("Region: " + eElement.getElementsByTagName("region").item(0).getTextContent());
              
            } else if (field.equals("country")) {
              System.out.println("Country: " + eElement.getElementsByTagName("country").item(0).getTextContent());  
            } else if (field.equals("address")) {
              System.out.println("Address: " + eElement.getElementsByTagName("address").item(0).getTextContent());
            } else if (field.equals("list")) {
              System.out.println("List: " + eElement.getElementsByTagName("list").item(0).getTextContent());
              
            } else {
              System.out.println("Invalid field name: " + field);
            }
          }
          System.out.println();
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

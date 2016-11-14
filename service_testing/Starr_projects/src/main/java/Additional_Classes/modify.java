package Additional_Classes;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
public class modify {
	public static void main(String[] args) {
	      try {
	         File inputFile = new File("D:\\output.xml");
	         SAXReader reader = new SAXReader();
	         Document document = reader.read( inputFile );

	         Element classElement = document.getRootElement();

	         List<Node> nodes = document.selectNodes("/class/student[@rollno='493']" );		       
	         for (Node node : nodes) {
	            Element element = (Element)node;
	            Iterator<Element> iterator=element.elementIterator("marks");
	            while(iterator.hasNext()){
	               Element marksElement=iterator.next();
	               marksElement.setText("80");
	            }
	         }

	         // Pretty print the document to System.out
	         OutputFormat format = OutputFormat.createPrettyPrint();
	         XMLWriter writer;
	         writer = new XMLWriter( System.out, format );
	         writer.write( document );
	      } catch (DocumentException e) {
	         e.printStackTrace();
	      } catch (UnsupportedEncodingException e) {         
	         e.printStackTrace();
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
	   }	
}

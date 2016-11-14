package Supporting_Classes;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;


public class xml_manipulation {
	private String file_location;
	
	
	//***************constructor to load file location***************************************************************
	public xml_manipulation(String file_location)
	{
		this.file_location = file_location;
		
	}
	
	
	
	//*****************method for reading the XML input file by passing the xpath value******************************
	 
	public String read_xml(String xpath) throws UnsupportedEncodingException, IOException,DocumentException
	{
		//Node element = null;
		
			
		File inputFile = new File(file_location);
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputFile);
        //XPath xpath_w = document.createXPath(xpath);
        Node element = document.selectSingleNode(xpath);
        
        System.out.println(element.getText());
    	
	   
		
		return element.getText();
        
		
	}
	

	
	//*********************method to convert a string into XML************************************************************
	
	public void String_to_xml(String xml_data) throws IOException,DocumentException
	{
		Writer writer = new FileWriter(file_location);
        
		
		Document document = DocumentHelper.parseText(xml_data);
        OutputFormat format = OutputFormat.createPrettyPrint();
        XMLWriter xmlwriter;
        xmlwriter = new XMLWriter( writer, format );
        xmlwriter.write( document );
        xmlwriter.close();
	}
	
	
	 //******************************method to modify values in existing XML by Passing XPath************************************************
	
	public void modify(String xpath,String value) throws UnsupportedEncodingException, IOException, DocumentException
	{
		File inputFile = new File(file_location);
		
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputFile);
       
        Node node = document.selectSingleNode(xpath);
        Element element = (Element)node;
        element.setText(value);
        
        OutputFormat format = OutputFormat.createPrettyPrint();
        Writer writer = new FileWriter(file_location);
        XMLWriter xmlwriter;
        xmlwriter = new XMLWriter( writer, format );
        xmlwriter.write( document );
        writer.close();
		
		
	}
		
   //*********************************method to Convert xml to string**************************************************************************		
		
	public String XML_tostring() throws UnsupportedEncodingException, IOException, DocumentException
	{
		File inputFile = new File(file_location);
		
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputFile);
		return document.asXML();
		
	}
	
//************************************************************************************************************************************************	
	
	 
	 
	 
public static void main(String args[]) throws DocumentException, UnsupportedEncodingException, IOException
{
	xml_manipulation obj1=new xml_manipulation("E:\\samplexml.xml");
	obj1.read_xml("/class/student[@rollno='593']/nickname");
	//File inputfile = new File("E:\\sample_text.txt");
	//BufferedReader br = new BufferedReader(new FileReader("E:\\sample_text.txt"));
	//obj1.modify("//to", "modified");
	//obj1.modify("/class/student[@rollno='593']/nickname","Sasi");
	
	System.out.println("read sucessfully");
	
}
}




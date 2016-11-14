package Additional_Classes;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.net.URL;

import javax.swing.text.Element;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.dom4j.XPath;
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
	 
	public String read_xml(String xpath) throws UnsupportedEncodingException, IOException
	{
		//Node element = null;
		try{
			
		File inputFile = new File(file_location);
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputFile);
        //XPath xpath_w = document.createXPath(xpath);
       Node element = document.selectSingleNode(xpath);
        
        System.out.println("sucessfully readed");
    	
	     }
		catch (DocumentException e)
		{
	         e.printStackTrace();
	    }
		Node element = null;
		return element.getText();
		
	}
	

	
	//*********************method to convert a string into XML************************************************************
	
	public void String_to_xml(String xml_data) throws IOException
	{
		Writer writer = new FileWriter(file_location);
        Document document = null;
		//SAXReader reader = new SAXReader();
       // Document document = null;
		try {
			document = DocumentHelper.parseText(xml_data);
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println(xml_data);
        
        
        String XML = document.asXML();
        System.out.println(XML);
        OutputFormat format = OutputFormat.createPrettyPrint();
        XMLWriter writer2;
        writer2 = new XMLWriter( writer, format );
        writer2.write( document );
        writer2.close();
	}
	
	
	 //******************************method to modify values in existing XML by Passing XPath************************************************
	
	public void modify(String xpath,String value) throws UnsupportedEncodingException, IOException, DocumentException
	{
		//read_xml(xpath);
		
		
		//Document document = DocumentHelper.parseText(value);
		//Node element = null;

		File inputFile = new File(file_location);
		
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputFile);
       
        Node node = document.selectSingleNode(xpath);
        Element element = (Element)node;
		
		((Node) element).setText(value);
		OutputFormat format = OutputFormat.createPrettyPrint();
        XMLWriter writer;
        writer = new XMLWriter( System.out, format );
        writer.write( document );
		System.out.println("written successfully");
		
	}
		
   //*********************************		
		
	
	
	
	
	
	 
	 
	 
public static void main(String args[]) throws DocumentException, UnsupportedEncodingException, IOException
{
	xml_manipulation obj1=new xml_manipulation("E:\\samplexml.xml");
	//File inputfile = new File("E:\\sample_text.txt");
	//BufferedReader br = new BufferedReader(new FileReader("E:\\sample_text.txt"));
	//obj1.modify("//cars/supercars[@company", "80");
	
	
obj1.modify("/class/student[@rollno='393']/firstname","Sharath");
	
	System.out.println("read sucessfully");
	
	
	
	//BufferedReader br = new BufferedReader(inputfile);
//	String output = null,sCurrentLine=null;
	//while ((sCurrentLine = br.readLine()) != null) 
	//{
		//if (output == null)
		//{
		//	output = sCurrentLine;
	//	}
	//	else
	//	{
	//		output += sCurrentLine;
	//	}
		//System.out.println(sCurrentLine);
	//}
	//System.out.println(output);
	//obj1.String_to_xml(output);
	//System.out.println("read sucessfully");
}
}




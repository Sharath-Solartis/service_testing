package Supporting_Classes;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.dom4j.DocumentException;




public class request_response {

	//json_handle js;
	
  
	private json_handle json_object;
	private xml_manipulation xml_object;
    private String filepath;
    private String type;
   
    public request_response(String filepath,String type)
    {
    	
    	this.type = type;
    	this.filepath = filepath;
    	
    	if(type.equals("json"))
    	{
    		
    		json_object = new json_handle();
    		json_object.get_filepath(this.filepath+".json");
    	}
    	else if(type.equals("xml"))
    	{
    		xml_object = new xml_manipulation();
    		xml_object.get_filepath(this.filepath+".xml");
    	}
    }
    
    public request_response()
    {
    	
    }
    
    public request_response(String type)
    {
    	this.type = type;
    }
    
    public void get_filepath(String filepath)
    {
    	if(type.equals("json"))
    	{
    		json_object.get_filepath(this.filepath);
    	}
    	else if(type.equals("xml"))
    	{
    		xml_object.get_filepath(this.filepath);
    	}
    }
    
   
    public void String_to_object(String data) throws IOException, DocumentException
    {
    	if(type.equals("json"))
    	{
    		json_object.create_json(data);
    	}
    	else if(type.equals("xml"))
    	{
    		xml_object.String_to_xml(data);
    	}
    }
    
    
    public String Object_to_String() throws UnsupportedEncodingException, IOException, DocumentException
    {
    	String output = null;
    	if(type.equals("json"))
    	{
    		output = json_object.jsontostring();
    	}
    	else if(type.equals("xml"))
    	{
    		output = xml_object.XML_tostring();
    	}
    	return output;
    }
   
    public String read(String path) throws UnsupportedEncodingException, IOException, DocumentException
     {
    	String output = null;
    	
	   if(type.equals("json"))
	   {
		   System.out.println("read");
		   output = json_object.read(path);
	   }
	   else if(type.equals("xml"))
	   {
		   output =  xml_object.read_xml(path);
	   } 
	   return output;
     }
    
   
    public void write(String path,String value) throws UnsupportedEncodingException, IOException, DocumentException
     {
	   if(type.equals("json"))
	   {
		   json_object.write(path,value);
	   }
	   else if(type.equals("xml"))
	   {
		   xml_object.modify(path,value);
	   }
     }
    
   /* public static void main(String args[]) throws UnsupportedEncodingException, IOException, DocumentException
    {
    	request_response request = new request_response
    	("Q:/Automation Team/1 Projects/06 Star Search_rescue/Search_n_Rescue/program_doc/Release_1/Rating/sample_request/Request_JSON_Rating_V1.json","json");
    	
    	System.out.println(request.read("..CoverageType"));
    }
    */
}

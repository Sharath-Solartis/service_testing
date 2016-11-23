package Supporting_Classes;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.internal.JsonReader;

public class json_handle
{
	private JsonPath path;
	private JSONObject obj = new JSONObject();
	private JSONParser parser = new JSONParser();
	private JsonReader  doc = new JsonReader();
	private String file_location;
	private FileReader read_file = null;
	private FileWriter write_file = null;
	public json_handle(String file_location)
	{
		this.file_location = file_location;
		
	}
	
	
	public json_handle() {
		// TODO Auto-generated constructor stub
	}

	public void get_filepath(String filepath) 
	{
		// TODO Auto-generated method stub
		this.file_location = filepath;
	}

	private String enable_read()
	{
			try 
			{
				read_file = new FileReader(file_location);
				obj = (JSONObject) parser.parse(read_file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("Cant read file");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			read_file = null;
			return obj.toJSONString();
		
	}
	
	private void enable_write(String json_string)
	{
		try {
			
			write_file = new FileWriter(file_location);
			write_file.write(json_string);
			write_file.flush();
			write_file.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void create_json(String json_string)
	{
		//System.out.println("Going to create json");
		enable_write(json_string);
	}
	
	public String read(String json_path)
	{
		doc.parse(enable_read());
		
		path = JsonPath.compile(json_path);
		
		return doc.read(path).toString();
		
	}
	
	public String jsontostring()
	{
		doc.parse(enable_read());
		//System.out.println(doc.jsonString());
		return doc.jsonString();
	}
	
	public void write(String json_path,String new_value)
	{
		doc.parse(enable_read());
		path = JsonPath.compile(json_path);
		doc.set(path, new_value);
		enable_write(doc.jsonString());
		
	}


	
	
}

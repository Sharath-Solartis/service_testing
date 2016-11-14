package Supporting_Classes;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Properties;

public class properties_handle extends Properties 
{
	
	
		

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String path = null;
	
		public properties_handle()
		{
			
		}
		public properties_handle(String path)
		{
			this.path = path;
			FileInputStream configuration = null;
			try 
			{
				
				configuration = new FileInputStream
						(path);
			} catch (FileNotFoundException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try 
			{
				this.load(configuration);
			} catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void store(String newpath)
		{
			Writer writer = null;
			try {
				 writer = new FileWriter(newpath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				this.store(writer, "File saved");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
		}
		
		public void store()
		{
			Writer writer = null;
			try 
			{
				 writer = new FileWriter(this.path);
			} catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try 
			{
				this.store(writer, "File saved");
			} catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			};
		}
}

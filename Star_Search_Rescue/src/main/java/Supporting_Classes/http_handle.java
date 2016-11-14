package Supporting_Classes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class http_handle 
{
	private URL url;
	private HttpURLConnection conn;
	
	public http_handle(String url,String type)
	{
		try {
			this.url = new URL(url);
			conn = (HttpURLConnection) this.url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestMethod(type);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void add_header(String attr,String value)
	{
		conn.setRequestProperty(attr, value);
		
	}
	
	public void send_data(String data)
	{
		OutputStream os;
		try {
			os = conn.getOutputStream();
			os.write(data.getBytes());
			os.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		
	}
	
	public String Receive_data() throws Exception
	{
		String output,output2 = null;
		BufferedReader br;
		try {
			br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));
			while ((output = br.readLine()) != null) 
			{
				if(output2 == null)
				{
					output2 = output;
				}
				else
				{
					output2 += output;
				}
			}
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (output2 == null)
		{
			throw new Exception("Send request once again");
		}
		
		//System.out.println("Output from Server .... \n");
		
		return output2;
	}
	
}

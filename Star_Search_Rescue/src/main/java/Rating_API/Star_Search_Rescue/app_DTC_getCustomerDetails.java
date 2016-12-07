package Rating_API.Star_Search_Rescue;

import Supporting_Classes.http_handle;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import org.dom4j.DocumentException;

import Supporting_Classes.database_operation;
import Supporting_Classes.properties_handle;
import Supporting_Classes.request_response;

/**
 * Hello world!
 *
 */
public class app_DTC_getCustomerDetails 
{
    //private static FileInputStream configuration1;
	//public static properties_handle config = null;
	public static request_response sample_input = null;
	public static request_response request = null;
	public static request_response response = null;
	public static void main( String[] args ) throws SQLException, ClassNotFoundException, UnsupportedEncodingException, IOException, DocumentException
	
    {
        //System.out.println( "Hello World!" );
		database_operation.config = new properties_handle
				("Q:/Automation Team/1 Projects/08 DTC/Release1/GetcustomerDetails/configuration_file/config_json.properties");
		
		
		database_operation.conn_setup();
    	System.setProperty("jsse.enableSNIExtension", "false");
		
		sample_input = new request_response(database_operation.config.getProperty("sample_request"),database_operation.config.getProperty("type"));//added
		
		database_operation input = new database_operation();
		database_operation output = new database_operation();
		database_operation json_elements = new database_operation();
		json_elements.get_dataobjects(database_operation.config.getProperty("json_query"));
		input.get_dataobjects(database_operation.config.getProperty("input_query"));
		output.get_dataobjects(database_operation.config.getProperty("output_query"));
		//String[] expected_column_col = config.getProperty("expected_column").split(";");
		String[] actual_column_col = database_operation.config.getProperty("actual_column").split(";");
		String[] input_column_col = database_operation.config.getProperty("input_column").split(";");
		String[] status_column_col = database_operation.config.getProperty("status_column").split(";");
		int status_column_size = status_column_col.length;
		//int expected_column_size = expected_column_col.length;
		int actual_column_size = actual_column_col.length;
		int input_column_size = input_column_col.length;
		
		do
		{
			if(input.read_data("flag_for_execution").equals("Y"))
			{
				request = new request_response(database_operation.config.getProperty("request_location")+input.read_data("testdata")+"_request",database_operation.config.getProperty("type"));
				request.String_to_object(sample_input.Object_to_String());
				for(int i=0;i<input_column_size;i++)
				{
					request.write(json_elements.read_data(input_column_col[i]), input.read_data(input_column_col[i]));
				}
				
				http_handle http = new http_handle(database_operation.config.getProperty("test_url"),"POST");
				http.add_header("Content-Type", database_operation.config.getProperty("content_type"));
				http.add_header("Token", database_operation.config.getProperty("token"));
				http.add_header("EventName", database_operation.config.getProperty("EventName"));
				
				String input_data = request.Object_to_String();
				http.send_data(input_data);
				
				String response_string = null;
				try {
					response_string = http.Receive_data();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				response = new request_response(database_operation.config.getProperty("response_location")+input.read_data("testdata")+"_response",database_operation.config.getProperty("type"));  // response location
				response.String_to_object(response_string);
				for(int i=0;i<actual_column_size;i++)
				{
					try
					{
						String actual=(response.read(json_elements.read_data(actual_column_col[i])).replaceAll("\\[\"", "")).replaceAll("\"\\]", "");
						output.write_data(actual_column_col[i],actual);
					//output.write_data(actual_column_col[i], response.read(json_elements.read_data(actual_column_col[i])));
				    }
					catch(Exception e1)
					{
						output.write_data(actual_column_col[i],"Null value");
					}
			   }
	for(int i=0;i<status_column_size;i++)
				{
					String[] status_ind_col = status_column_col[i].split("-");
					String expected_column = status_ind_col[0];
					String actual_column = status_ind_col[1];
					System.out.println(status_column_col[i]);
					String status_column = status_ind_col[2];
					if(premium_comp(output.read_data(expected_column),output.read_data(actual_column)))
					{
						output.write_data(status_column, "Pass");
					}
					else
					{
						output.write_data(status_column, "Fail");
					}
					
				}
			}
			input.write_data("flag_for_execution", "Completed");
			output.write_data("flag_for_execution", "Completed");
			input.update_row();
			output.update_row();
			
		}while(input.move_forward()&& output.move_forward());
		
		database_operation.close_conn();
					
    }
    
	
	private static boolean premium_comp(String expected,String actual)
	{
		
		boolean status = false;
		if(expected == null || actual == null ||expected.equals("") || actual.equals(""))
		{
			status = false;
		}
		else
		{
			expected = expected.replaceAll("\\[\"", "");
			actual = actual.replaceAll("\\[\"", "");
			expected = expected.replaceAll("\"\\]", "");
			actual = actual.replaceAll("\"\\]", "");
			expected = expected.replaceAll("\\.[0-9]*", "");
			actual = actual.replaceAll("\\.[0-9]*", "");
			
			System.out.println(actual);
			System.out.println(expected);
			if(expected.equals(actual))
			{
				status = true;
			}
			else 
			{
				status = false;
			}
		}
		return status;
	}
}

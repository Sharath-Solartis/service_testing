package Rating_API.Star_Search_Rescue;

import Supporting_Classes.http_handle;














import java.sql.SQLException;


import Supporting_Classes.database_operation;
import Supporting_Classes.json_handle;
import Supporting_Classes.properties_handle;

/**
 * Hello world!
 *
 */
public class app_json_db 
{
    //private static FileInputStream configuration1;
	public static properties_handle config = null;
	public static json_handle sample_input = null;
	public static json_handle request = null;
	public static json_handle response = null;
	public static void main( String[] args ) throws SQLException, ClassNotFoundException
	
    {
        //System.out.println( "Hello World!" );
		config = new properties_handle
				("Q:/Automation Team/1 Projects/06 Star Search_rescue/Search_n_Rescue/program_doc/Release_1/Rating/configuration_file/config_json.properties");
		
		database_operation.conn_setup();
    	System.setProperty("jsse.enableSNIExtension", "false");
		
		sample_input = new json_handle(config.getProperty("sample_request"));
		
		database_operation input = new database_operation();
		database_operation output = new database_operation();
		database_operation json_elements = new database_operation();
		json_elements.get_dataobjects(config.getProperty("json_query"));
		input.get_dataobjects(config.getProperty("input_query"));
		output.get_dataobjects(config.getProperty("output_query"));
		//String[] expected_column_col = config.getProperty("expected_column").split(";");
		String[] actual_column_col = config.getProperty("actual_column").split(";");
		String[] input_column_col = config.getProperty("input_column").split(";");
		String[] status_column_col = config.getProperty("status_column").split(";");
		int status_column_size = status_column_col.length;
		//int expected_column_size = expected_column_col.length;
		int actual_column_size = actual_column_col.length;
		int input_column_size = input_column_col.length;
		
		do
		{
			if(input.read_data("flag_for_execution").equals("Y"))
			{
				request = new json_handle(config.getProperty("request_location")+input.read_data("testdata")+"_request"+".json");
				request.create_json(sample_input.jsontostring());
				for(int i=0;i<input_column_size;i++)
				{
					request.write(json_elements.read_data(input_column_col[i]), input.read_data(input_column_col[i]));
				}
				
				http_handle http = new http_handle(config.getProperty("test_url"),"POST");
				http.add_header("Content-Type", config.getProperty("content_type"));
				http.add_header("Token", config.getProperty("token"));
				String input_data = request.jsontostring();
				http.send_data(input_data);
				
				String response_string = null;
				try {
					response_string = http.Receive_data();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				response = new json_handle(config.getProperty("response_location")+input.read_data("testdata")+"_response"+".json");  // response location
				response.create_json(response_string);
				for(int i=0;i<actual_column_size;i++)
				{
					output.write_data(actual_column_col[i], response.read(json_elements.read_data(actual_column_col[i])));
				}
				for(int i=0;i<status_column_size;i++)
				{
					String[] status_ind_col = status_column_col[i].split("-");
					String expected_column = status_ind_col[0];
					String actual_column = status_ind_col[1];
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
			output.update_row();
			input.update_row();
		}while(input.move_forward() && output.move_forward());
		
		database_operation.close_conn();
					
    }
    
	
	private static boolean premium_comp(String expected,String actual)
	{
		
		boolean status = false;
		if(expected == null || actual == null)
		{
			status = false;
		}
		else
		{
			expected = expected.replaceAll("\\[\"", "");
			actual = actual.replaceAll("\\[\"", "");
			expected = expected.replaceAll("\"\\]", "");
			actual = actual.replaceAll("\"\\]", "");
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

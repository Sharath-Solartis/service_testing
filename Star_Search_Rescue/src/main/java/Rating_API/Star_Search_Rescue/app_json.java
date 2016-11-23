package Rating_API.Star_Search_Rescue;

import Supporting_Classes.http_handle;

import java.io.BufferedWriter;
import java.io.File;

import java.io.FileWriter;
import java.io.IOException;







import Supporting_Classes.json_handle;
import Supporting_Classes.excel_operation;
import Supporting_Classes.properties_handle;

/**
 * Hello world!
 *
 */
public class app_json 
{
    //private static FileInputStream configuration1;

	public static void main( String[] args )
    {
        //System.out.println( "Hello World!" );
    	System.setProperty("jsse.enableSNIExtension", "false");
		properties_handle config = new properties_handle
				("Q:/Automation Team/1 Projects/06 Star Search_rescue/Search_n_Rescue/program_doc/Release_1/Issue_Certificate/configuration_file/config.properties");
		
		File output =new File(config.getProperty("report_file"));
		if (!output.exists()) 
		{
			try 
			{
				output.createNewFile();
			} catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			output.delete();
			try 
			{
				output.createNewFile();
			} catch (IOException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		excel_operation input_excel = new excel_operation(config.getProperty("input_data"));
		json_handle input_json;
		json_handle output_json = null;
		json_handle response_json;
		input_excel.getsheets("Sheet1");
		input_excel.set_rownumber(2);
		
		while(input_excel.has_next_row())
		{
			if(input_excel.read_data(input_excel.get_rownumber(), 2).equals("Y"))
			{
				input_excel.set_columnnumber(3);
				input_json = new json_handle(config.getProperty("sample_request"));   // request location
				System.out.println("Sample_String - "+input_json.jsontostring());
				output_json = new json_handle(config.getProperty("request_location")+input_excel.read_data(input_excel.get_rownumber(), 1)+"request"+input_excel.read_data(input_excel.get_rownumber(), 0)+".json"); // saved request location
				
				output_json.create_json(input_json.jsontostring());
				while(input_excel.has_next_column())
				{
					if(!(input_excel.read_data(0, input_excel.get_columnnumber()).isEmpty()))
					{	
						if(input_excel.read_data(input_excel.get_rownumber(), input_excel.get_columnnumber()).isEmpty())
						{
							output_json.write(input_excel.read_data(0, input_excel.get_columnnumber()), "");
						}
						else
						{
							
							//System.out.println(j+" - "+sheet.getCell(j,1).getContents().toString()+" - "+getjsonpath(json,sheet.getCell(j,1).getContents().toString()));
							//System.out.println(output_json.);
							output_json.write(input_excel.read_data(0, input_excel.get_columnnumber()), input_excel.read_data(input_excel.get_rownumber(), input_excel.get_columnnumber()));
						}
					}
					input_excel.next_col();
				}
				
				System.out.println(config.getProperty("token"));
				http_handle http = new http_handle(config.getProperty("test_url"),"POST");
				http.add_header("Content-Type", "application/json");
				http.add_header("Token", config.getProperty("token"));
				String input = output_json.jsontostring();
				http.send_data(input);
			
				String response_string = null;
				try {
					response_string = http.Receive_data();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				response_json = new json_handle(config.getProperty("response_location")+input_excel.read_data(input_excel.get_rownumber(),1)+"response"+input_excel.read_data(input_excel.get_rownumber(),0)+".json");  // response location
				System.out.println(response_string);
				response_json.create_json(response_string);
				
				
				
				FileWriter fw = null;
				try 
				{
					fw = new FileWriter(output.getAbsoluteFile(),true);
				} catch (IOException e) {
					// TODO Auto-generated catch block
						e.printStackTrace();
					}
				BufferedWriter bw = new BufferedWriter(fw);
				try 
					{
						bw.append(System.getProperty("line.separator"));
					} catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try 
				{
					switch(config.getProperty("API"))
					{
					case "Rating":	if(response_json.read("..ResponseStatus").equals("Failed"))
									{
										bw.write(input_excel.read_data(input_excel.get_rownumber(), 1)+input_excel.read_data(input_excel.get_rownumber(), 0)
												+"-"+response_json.read("..UserMessage")
												+"-"+response_json.read("..MessageType")+"-"+response_json.read("..messageCode"));
										
									}
									else 
										bw.write(input_excel.read_data(input_excel.get_rownumber(), 1)+input_excel.read_data(input_excel.get_rownumber(), 0)
												+"-"
												+response_json.read("..PlanName")+response_json.read("..CoverageType")
												+response_json.read("..EffectiveDate")+response_json.read("..BasePremium")
												+response_json.read("..MemberNumber")+response_json.read("..AD&D")
												+response_json.read("..EmergencyEvacuation")+response_json.read("..Search&Rescue")
												+response_json.read("..RepatofRemains")+response_json.read("..AccidentMedicalExpense")
												+response_json.read("..EmergencyHelicopterEvacuation"));
										System.out.println(input_excel.read_data(input_excel.get_rownumber(), 1)+input_excel.read_data(input_excel.get_rownumber(), 0)
												+"-"
												+response_json.read("..PlanName")+response_json.read("..CoverageType")
												+response_json.read("..EffectiveDate")+response_json.read("..BasePremium")
												+response_json.read("..MemberNumber")+response_json.read("..AD&D")
												+response_json.read("..EmergencyEvacuation")+response_json.read("..Search&Rescue")
												+response_json.read("..RepatofRemains")+response_json.read("..AccidentMedicalExpense")
												+response_json.read("..EmergencyHelicopterEvacuation"));
									break;
					case "Issue_certificate":
												if(response_json.read("..ResponseStatus").equals("Failed"))
												{
													bw.write(input_excel.read_data(input_excel.get_rownumber(), 1)+input_excel.read_data(input_excel.get_rownumber(), 0)
															+"-"+response_json.read("..UserMessage")
															+"-"+response_json.read("..MessageType")+"-"+response_json.read("..messageCode"));
							
												}
												else 
													bw.write(input_excel.read_data(input_excel.get_rownumber(), 1)+input_excel.read_data(input_excel.get_rownumber(), 0)
															+"-"
															+response_json.read("..PlanName")+response_json.read("..CoverageType")
															+response_json.read("..EffectiveDate")+response_json.read("..BasePremium")
															+response_json.read("..MemberNumber")+response_json.read("..AD&D")
															+response_json.read("..EmergencyEvacuation")+response_json.read("..Search&Rescue")
															+response_json.read("..RepatofRemains")+response_json.read("..AccidentMedicalExpense")
															+response_json.read("..EmergencyHelicopterEvacuation"));
												System.out.println(input_excel.read_data(input_excel.get_rownumber(), 1)+input_excel.read_data(input_excel.get_rownumber(), 0)
														+"-"
														+response_json.read("..PlanName")+response_json.read("..CoverageType")
														+response_json.read("..EffectiveDate")+response_json.read("..BasePremium")
														+response_json.read("..MemberNumber")+response_json.read("..AD&D")
														+response_json.read("..EmergencyEvacuation")+response_json.read("..Search&Rescue")
														+response_json.read("..RepatofRemains")+response_json.read("..AccidentMedicalExpense")
														+response_json.read("..EmergencyHelicopterEvacuation"));
												break;  
						             
					case "cancel":
									bw.write(input_excel.read_data(input_excel.get_rownumber(), 1)+"-"+response_json.read("..CancelType"));
									System.out.println(input_excel.read_data(input_excel.get_rownumber(), 1)+"-"+response_json.read("..CancelType"));
									break;
						 			
					}
					bw.close();
				} catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				input_excel.next_row();
			}
		}
		
    }
}

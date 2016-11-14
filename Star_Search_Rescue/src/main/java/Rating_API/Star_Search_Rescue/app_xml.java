package Rating_API.Star_Search_Rescue;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.dom4j.DocumentException;

import Supporting_Classes.excel_operation;
import Supporting_Classes.http_handle;
//import Supporting_Classes.json_handle;
import Supporting_Classes.properties_handle;
import Supporting_Classes.xml_manipulation;

public class app_xml {

	public static void main(String[] args) throws DocumentException, IOException 
	{
		// TODO Auto-generated method stub
		System.setProperty("jsse.enableSNIExtension", "false");
		properties_handle config = new properties_handle
				("Q:/Automation Team/1 Projects/06 Star Search_rescue/Search_n_Rescue/program_doc/Release_1/Rating/configuration_file/config.properties");
		
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
		xml_manipulation sample_xml;
		xml_manipulation input_xml;
		xml_manipulation response_xml;
		input_excel.getsheets("Sheet1");
		input_excel.set_rownumber(2);
		
		while(input_excel.has_next_row())
		{
			if(input_excel.read_data(input_excel.get_rownumber(), 2).equals("Y"))
			{
				input_excel.set_columnnumber(3);
				sample_xml = new xml_manipulation(config.getProperty("sample_request"));   // request location
				
				input_xml = new xml_manipulation(config.getProperty("request_location")+input_excel.read_data(input_excel.get_rownumber(), 1)+"request"+input_excel.read_data(input_excel.get_rownumber(), 0)+".xml"); // saved request location
				input_xml.String_to_xml(sample_xml.XML_tostring());
				while(input_excel.has_next_column())
				{
					if(!(input_excel.read_data(0, input_excel.get_columnnumber()).isEmpty()))
					{	
						if(input_excel.read_data(input_excel.get_rownumber(), input_excel.get_columnnumber()).isEmpty())
						{
							input_xml.modify(input_excel.read_data(0, input_excel.get_columnnumber()), "");
						}
						else
						{
							
							//System.out.println(j+" - "+sheet.getCell(j,1).getContents().toString()+" - "+getjsonpath(json,sheet.getCell(j,1).getContents().toString()));
							//System.out.println(output_json.);
							input_xml.modify(input_excel.read_data(0, input_excel.get_columnnumber()), input_excel.read_data(input_excel.get_rownumber(), input_excel.get_columnnumber()));
						}
					}
					input_excel.next_col();
				}
				
			
				http_handle http = new http_handle(config.getProperty("test_url"),"POST");
				http.add_header("Content-Type", "application/xml");
				http.add_header("Token", config.getProperty("token"));
				String input = input_xml.XML_tostring();
				http.send_data(input);
			
				String response_string = null;
				try {
					response_string = http.Receive_data();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				response_xml = new xml_manipulation(config.getProperty("response_location")+input_excel.read_data(input_excel.get_rownumber(),1)+"response"+input_excel.read_data(input_excel.get_rownumber(),0)+".xml");  // response location
				//System.out.println(response_string);
				response_xml.String_to_xml(response_string);
				
				
				
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
					case "Rating":	if(response_xml.read_xml("//ResponseStatus").equals("Failed"))
									{
										//bw.write(input_excel.read_data(input_excel.get_rownumber(), 1)+input_excel.read_data(input_excel.get_rownumber(), 0)
												//+"-"+response_xml.read_xml()
												//+"-"+response_xml.read_xml()+"-"+response_xml.read_xml());
										
									}
									else 
										bw.write(input_excel.read_data(input_excel.get_rownumber(), 1)+input_excel.read_data(input_excel.get_rownumber(), 0)
												+" - "+response_xml.read_xml("//ATTRIBUTE_DETAIL[@KEY='Object::Policy::Certificate::PlanName']")
												+" - "+response_xml.read_xml("//ATTRIBUTE_DETAIL[@KEY='Object::BaseRate']")
												+" - "+response_xml.read_xml("//ATTRIBUTE_DETAIL[@KEY='Object::Coverage::Limit']")
												+" - "+response_xml.read_xml("//ATTRIBUTE_DETAIL[@KEY='Object::Coverage::CoverageName']")
												+" - "+response_xml.read_xml("//REPEATED_ATTRIBUTE_DETAIL/ATTRIBUTE_DETAILS[1]/ATTRIBUTE_DETAIL[@KEY='Object::Coverage::Limit']")
												+" - "+response_xml.read_xml("//REPEATED_ATTRIBUTE_DETAIL/ATTRIBUTE_DETAILS[1]/ATTRIBUTE_DETAIL[@KEY='Object::Coverage::CoverageName']")
												+" - "+response_xml.read_xml("//REPEATED_ATTRIBUTE_DETAIL/ATTRIBUTE_DETAILS[2]/ATTRIBUTE_DETAIL[@KEY='Object::Coverage::Limit']")
												+" - "+response_xml.read_xml("//REPEATED_ATTRIBUTE_DETAIL/ATTRIBUTE_DETAILS[2]/ATTRIBUTE_DETAIL[@KEY='Object::Coverage::CoverageName']")
												+" - "+response_xml.read_xml("//REPEATED_ATTRIBUTE_DETAIL/ATTRIBUTE_DETAILS[3]/ATTRIBUTE_DETAIL[@KEY='Object::Coverage::Limit']")
												+" - "+response_xml.read_xml("//REPEATED_ATTRIBUTE_DETAIL/ATTRIBUTE_DETAILS[3]/ATTRIBUTE_DETAIL[@KEY='Object::Coverage::CoverageName']")
												+" - "+response_xml.read_xml("//REPEATED_ATTRIBUTE_DETAIL/ATTRIBUTE_DETAILS[4]/ATTRIBUTE_DETAIL[@KEY='Object::Coverage::Limit']")
												+" - "+response_xml.read_xml("//REPEATED_ATTRIBUTE_DETAIL/ATTRIBUTE_DETAILS[4]/ATTRIBUTE_DETAIL[@KEY='Object::Coverage::CoverageName']")
												+" - "+response_xml.read_xml("//REPEATED_ATTRIBUTE_DETAIL/ATTRIBUTE_DETAILS[5]/ATTRIBUTE_DETAIL[@KEY='Object::Coverage::Limit']")
												+" - "+response_xml.read_xml("//REPEATED_ATTRIBUTE_DETAIL/ATTRIBUTE_DETAILS[5]/ATTRIBUTE_DETAIL[@KEY='Object::Coverage::CoverageName']")
												);
										System.out.println(input_excel.read_data(input_excel.get_rownumber(), 1)+input_excel.read_data(input_excel.get_rownumber(), 0)
												+" - "+response_xml.read_xml("//ATTRIBUTE_DETAIL[@KEY='Object::Policy::Certificate::PlanName']")
												+" - "+response_xml.read_xml("//ATTRIBUTE_DETAIL[@KEY='Object::BaseRate']")
												+" - "+response_xml.read_xml("//ATTRIBUTE_DETAIL[@KEY='Object::Coverage::Limit']")
												+" - "+response_xml.read_xml("//ATTRIBUTE_DETAIL[@KEY='Object::Coverage::CoverageName']")
												+" - "+response_xml.read_xml("//REPEATED_ATTRIBUTE_DETAIL/ATTRIBUTE_DETAILS[1]/ATTRIBUTE_DETAIL[@KEY='Object::Coverage::Limit']")
												+" - "+response_xml.read_xml("//REPEATED_ATTRIBUTE_DETAIL/ATTRIBUTE_DETAILS[1]/ATTRIBUTE_DETAIL[@KEY='Object::Coverage::CoverageName']")
												+" - "+response_xml.read_xml("//REPEATED_ATTRIBUTE_DETAIL/ATTRIBUTE_DETAILS[2]/ATTRIBUTE_DETAIL[@KEY='Object::Coverage::Limit']")
												+" - "+response_xml.read_xml("//REPEATED_ATTRIBUTE_DETAIL/ATTRIBUTE_DETAILS[2]/ATTRIBUTE_DETAIL[@KEY='Object::Coverage::CoverageName']")
												+" - "+response_xml.read_xml("//REPEATED_ATTRIBUTE_DETAIL/ATTRIBUTE_DETAILS[3]/ATTRIBUTE_DETAIL[@KEY='Object::Coverage::Limit']")
												+" - "+response_xml.read_xml("//REPEATED_ATTRIBUTE_DETAIL/ATTRIBUTE_DETAILS[3]/ATTRIBUTE_DETAIL[@KEY='Object::Coverage::CoverageName']")
												+" - "+response_xml.read_xml("//REPEATED_ATTRIBUTE_DETAIL/ATTRIBUTE_DETAILS[4]/ATTRIBUTE_DETAIL[@KEY='Object::Coverage::Limit']")
												+" - "+response_xml.read_xml("//REPEATED_ATTRIBUTE_DETAIL/ATTRIBUTE_DETAILS[4]/ATTRIBUTE_DETAIL[@KEY='Object::Coverage::CoverageName']")
												+" - "+response_xml.read_xml("//REPEATED_ATTRIBUTE_DETAIL/ATTRIBUTE_DETAILS[5]/ATTRIBUTE_DETAIL[@KEY='Object::Coverage::Limit']")
												+" - "+response_xml.read_xml("//REPEATED_ATTRIBUTE_DETAIL/ATTRIBUTE_DETAILS[5]/ATTRIBUTE_DETAIL[@KEY='Object::Coverage::CoverageName']")
												);
									break;
					case "Issue_certificate":
						             bw.write(input_excel.read_data(input_excel.get_rownumber(), 1)+"-"+response_xml.read_xml("..CertificateNumber"));
						             System.out.println(input_excel.read_data(input_excel.get_rownumber(), 1)+"-"+response_xml.read_xml("..CertificateNumber"));
						             break;  
						             
					case "cancel":
									bw.write(input_excel.read_data(input_excel.get_rownumber(), 1)+"-"+response_xml.read_xml("..CancelType"));
									System.out.println(input_excel.read_data(input_excel.get_rownumber(), 1)+"-"+response_xml.read_xml("..CancelType"));
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

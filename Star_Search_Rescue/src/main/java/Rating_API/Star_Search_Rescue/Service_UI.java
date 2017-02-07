package Rating_API.Star_Search_Rescue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
//import java.awt.BorderLayout;
//import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;

import Supporting_Classes.CustomOutputStream;
import Supporting_Classes.database_operation;
import Supporting_Classes.properties_handle;
//import sun.security.krb5.Config;

import javax.swing.JButton;
import javax.swing.JProgressBar;
//import javax.swing.JScrollBar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.CompoundBorder;

import org.dom4j.DocumentException;

//import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

//import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;

import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class Service_UI {

	private JFrame frame;
	private JTextField txt_url;
	private JTextField txt_dbname;
	private JTextField txt_input_table;
	private JTextField txt_outputtable;
	private JTextField txt_request_location;
	private JTextField txt_response_location;
	private JTextField txt_json_table;
	private JTextField txt_sample_request;
	private String API_Selected = null;
	private JComboBox<String> cmb_API;
	private JTextField txt_content_type;
	//private JFileChooser request_file_location;
	private JTextArea print_console;
	private PrintStream printStream;
	
	private JScrollPane console_scroll;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Service_UI window = new Service_UI();
					window.frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Service_UI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(200, 200, 726, 649);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		enable_all_labels();
		
		enable_text_url();
		
		enable_text_dbname();
		
		enable_text_input_table();
		
		enable_text_output_table();
		
		enable_text_request_location();
		
		enable_text_response_location();
		
		enable_text_json_table();
		
		enable_text_sample_request();
		
		enable_print_console();
		
		enable_button_runapi();
		
		enable_progress_check();
		
		enable_scrollbar();
		
		enable_button_testconnection();
		
		enable_content_type();
		
		enable_combo_API();
				
		//enable_file_chooser();		
				
				
			
			
		
	}

	private void enable_print_console() 
	{
		print_console = new JTextArea();
		//print_console.setBounds(44, 384, 636, 124);
		//frame.getContentPane().add(print_console);
		
		print_console.setVisible(true);
		printStream = new PrintStream(new CustomOutputStream(print_console));
		System.setOut(printStream);
		System.setErr(printStream);
		//frame.getContentPane().add(print_console);
		console_scroll = new JScrollPane(print_console,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		console_scroll.setBounds(44, 384, 636, 134);
		frame.getContentPane().add(console_scroll);
	
		
	}

	/*private void enable_file_chooser() 
	{
		// TODO Auto-generated method stub
		request_file_location = new JFileChooser("Browse File");
		request_file_location.setBackground(Color.GREEN);
		request_file_location.setBorder(new CompoundBorder());
		request_file_location.setBounds(351, 58, 322, 196);
		//request_file_location.setFileFilter(filter);
		frame.getContentPane().add(request_file_location);
	}*/

	private void enable_all_labels() 
	{
		// TODO Auto-generated method stub
		JLabel lbl_API_message = new JLabel("Which API to test?");
		lbl_API_message.setBounds(44, 27, 99, 20);
		frame.getContentPane().add(lbl_API_message);
		
		JLabel lblNewLabel = new JLabel("URL");
		lblNewLabel.setBounds(44, 58, 101, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("DB_name");
		lblNewLabel_1.setBounds(44, 89, 99, 20);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Input_table");
		lblNewLabel_2.setBounds(44, 123, 99, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Output_table");
		lblNewLabel_3.setBounds(44, 160, 99, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblRequestloocation = new JLabel("Request_location");
		lblRequestloocation.setBounds(44, 200, 99, 14);
		frame.getContentPane().add(lblRequestloocation);
		
		JLabel lblNewLabel_4 = new JLabel("Response_Location");
		lblNewLabel_4.setBounds(44, 237, 99, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Json_table");
		lblNewLabel_5.setBounds(44, 276, 99, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Sample_request");
		lblNewLabel_6.setBounds(44, 312, 99, 14);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Content_type");
		lblNewLabel_7.setBounds(44, 346, 99, 14);
		frame.getContentPane().add(lblNewLabel_7);
		
		
	}

	private void enable_scrollbar() 
	{
		
	}

	private void enable_progress_check() 
	{
		// TODO Auto-generated method stub
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(181, 529, 165, 14);
		frame.getContentPane().add(progressBar);
		
	}

	private void enable_text_sample_request() 
	{
		// TODO Auto-generated method stub
		txt_sample_request = new JTextField();
		txt_sample_request.setBounds(181, 309, 126, 20);
		frame.getContentPane().add(txt_sample_request);
		txt_sample_request.setColumns(10);
		
	}

	private void enable_button_runapi() 
	{
		// TODO Auto-generated method stub
		JButton btnRunApi = new JButton("Run API");
		btnRunApi.addMouseListener(new MouseAdapter() {
			@Override
		public void mouseClicked(MouseEvent e) 
		{
				//if(validation_correct())
				//{
					String API_to_test = cmb_API.getSelectedItem().toString();
					switch(API_to_test)
					{
						case "Preview PDF":	System.out.println("Mouse Clicked");
										database_operation.config.setProperty("test_url", txt_url.getText());
										database_operation.config.setProperty("db_url", txt_dbname.getText());
										database_operation.config.setProperty("input_table", txt_input_table.getText());
										database_operation.config.setProperty("input_query","Select * from " + txt_input_table.getText());
										database_operation.config.setProperty("output_table", txt_outputtable.getText());
										database_operation.config.setProperty("output_query", "Select * from " + txt_outputtable.getText());
										database_operation.config.setProperty("json_table", txt_json_table.getText());
										database_operation.config.setProperty("json_query", "Select * from " + txt_json_table.getText());
										
										database_operation.config.setProperty("request_location", txt_request_location.getText());
										database_operation.config.setProperty("response_location", txt_response_location.getText());
										database_operation.config.store();
										
											
												try {
												app_DTC_PreviewPDF.main(new String[5]);
												} catch (ClassNotFoundException | SQLException | IOException | DocumentException e1) {
													// TODO Auto-generated catch block
													System.out.print(e1.getMessage());
												}
											
										
										
										
										break;
					
					}
				//}
				//else
				//{
					//System.out.println("values cant be empty");
				//}
					
		}

			
		});
		btnRunApi.setBounds(591, 548, 89, 23);
		frame.getContentPane().add(btnRunApi);
		
	}

	protected boolean validation_correct() 
	{
		// TODO Auto-generated method stub
		boolean flag = true;
		if(txt_sample_request.getText().equals(""))
		{
			flag = false;
		}
		
		if(txt_sample_request.getText().equals(""))
		{
			flag = false;
		}
		
		if(txt_sample_request.getText().equals(""))
		{
			flag = false;
		}
		
		return flag;
	}

	private void enable_button_testconnection() 
	{
		// TODO Auto-generated method stub
		JButton btn_Testconnection = new JButton("Test Connection");
		btn_Testconnection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_Testconnection.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				System.out.println("Click Enabled");
			}
		});
		btn_Testconnection.setBounds(428, 272, 126, 23);
		frame.getContentPane().add(btn_Testconnection);
		
	}

	private void enable_content_type() 
	{
		// TODO Auto-generated method stub
		txt_content_type = new JTextField();
		txt_content_type.setBounds(181, 343, 126, 20);
		frame.getContentPane().add(txt_content_type);
		txt_content_type.setColumns(10);
		
		
	}

	private void enable_text_json_table() 
	{
		// TODO Auto-generated method stub
		txt_json_table = new JTextField();
		txt_json_table.setBounds(181, 273, 126, 20);
		frame.getContentPane().add(txt_json_table);
		txt_json_table.setColumns(10);
		
	}

	private void enable_text_response_location() 
	{
		// TODO Auto-generated method stub
		txt_response_location = new JTextField();
		txt_response_location.setBounds(181, 234, 126, 20);
		frame.getContentPane().add(txt_response_location);
		txt_response_location.setColumns(10);
		
	}

	private void enable_text_request_location() 
	{
		// TODO Auto-generated method stub
		txt_request_location = new JTextField();
		txt_request_location.setBounds(181, 197, 126, 20);
		frame.getContentPane().add(txt_request_location);
		txt_request_location.setColumns(10);
		
	}

	private void enable_text_output_table() 
	{
		// TODO Auto-generated method stub
		txt_outputtable = new JTextField();
		txt_outputtable.setBounds(181, 157, 126, 20);
		frame.getContentPane().add(txt_outputtable);
		txt_outputtable.setColumns(10);
		
	}

	private void enable_text_input_table() 
	{
		// TODO Auto-generated method stub
		txt_input_table = new JTextField();
		txt_input_table.setBounds(181, 120, 126, 20);
		frame.getContentPane().add(txt_input_table);
		txt_input_table.setColumns(10);
		
	}

	private void enable_combo_API() 
	{
		// TODO Auto-generated method stub
		cmb_API = new JComboBox<String>();
		cmb_API.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				API_Selected = cmb_API.getSelectedItem().toString();
				switch(API_Selected)
				{
				case "Preview PDF" : database_operation.config = new properties_handle
						("Q:/Automation Team/1 Projects/08 DTC/Release3/PreviewPDF/configuration_file/config_json_serviceUI.properties");
						System.out.println(database_operation.config.getProperty("input_query"));
						txt_url.setText(database_operation.config.getProperty("db_url"));
						
						
						break;
				case "Select_Item" : txt_url.setText("API not Selected");
								break;
								
				default: break;
				}
				
			}
		});
		cmb_API.setBounds(181, 27, 126, 20);
		frame.getContentPane().add(cmb_API);
		{
			
		}
		
	
		
		cmb_API.addItem("Select");
		cmb_API.addItem("Preview PDF");
		cmb_API.addItem("Rate API");
		//cmb_API.setSelectedItem("Preview PDF");
		cmb_API.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) 
			{
				API_Selected = cmb_API.getSelectedItem().toString();
				switch(API_Selected)
				{
				case "Select" : txt_url.setText("  ");
								txt_dbname.setText("  ");
								txt_input_table.setText(" ");
								txt_outputtable.setText(" ");
								txt_json_table.setText(" ");
								txt_request_location.setText(" ");
								txt_response_location.setText(" ");
								
							break;
				case "Preview PDF" : database_operation.config = new properties_handle
						("Q:/Automation Team/1 Projects/08 DTC/Release3/PreviewPDF/configuration_file/config_json_serviceUI.properties");
						System.out.println(database_operation.config.getProperty("input_query"));
						txt_url.setText(database_operation.config.getProperty("test_url"));
						txt_dbname.setText(database_operation.config.getProperty("db_url"));
						txt_input_table.setText(database_operation.config.getProperty("input_table"));
						txt_outputtable.setText(database_operation.config.getProperty("output_table"));
						txt_json_table.setText(database_operation.config.getProperty("json_table"));
						txt_request_location.setText(database_operation.config.getProperty("request_location"));
						txt_response_location.setText(database_operation.config.getProperty("response_location"));
						
						break;
						
				case "Rate API" : database_operation.config = new properties_handle
						("Q:/Automation Team/1 Projects/08 DTC/Release3/PreviewPDF/configuration_file/config_json.properties");
						System.out.println(database_operation.config.getProperty("input_query"));
						txt_url.setText("API not selected");
						txt_dbname.setText("API not selected");
						break;
				
								
				default: break;
				}
				
			}
		});
		
	}

	private void enable_text_dbname() 
	{
		// TODO Auto-generated method stub
		txt_dbname = new JTextField();
		txt_dbname.setBounds(181, 89, 126, 20);
		frame.getContentPane().add(txt_dbname);
		txt_dbname.setColumns(10);
		
	}

	private void enable_text_url() 
	{
		// TODO Auto-generated method stub
		txt_url = new JTextField();
		txt_url.setBounds(181, 58, 126, 20);
		frame.getContentPane().add(txt_url);
		txt_url.setColumns(10);
		
	}
}

package Rating_API.Star_Search_Rescue;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JScrollBar;

public class Service_UI {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;

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
		
		JLabel lbl_API_message = new JLabel("Which API to test?");
		lbl_API_message.setBounds(44, 27, 99, 20);
		frame.getContentPane().add(lbl_API_message);
		
		JComboBox cmb_API = new JComboBox();
		cmb_API.setBounds(181, 27, 126, 20);
		frame.getContentPane().add(cmb_API);
		cmb_API.addItem("Select");
		cmb_API.addItem("Preview PDF");
		
		textField = new JTextField();
		textField.setBounds(181, 58, 126, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("URL");
		lblNewLabel.setBounds(42, 58, 101, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("DB_name");
		lblNewLabel_1.setBounds(44, 89, 99, 20);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(181, 89, 126, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Input_table");
		lblNewLabel_2.setBounds(44, 123, 99, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setBounds(181, 120, 126, 20);
		frame.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Output_table");
		lblNewLabel_3.setBounds(44, 160, 99, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		JLabel lblRequestloocation = new JLabel("Request_location");
		lblRequestloocation.setBounds(44, 200, 99, 14);
		frame.getContentPane().add(lblRequestloocation);
		
		textField_3 = new JTextField();
		textField_3.setBounds(181, 157, 126, 20);
		frame.getContentPane().add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(181, 197, 126, 20);
		frame.getContentPane().add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Response_Location");
		lblNewLabel_4.setBounds(44, 237, 99, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		textField_5 = new JTextField();
		textField_5.setBounds(181, 234, 126, 20);
		frame.getContentPane().add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Json_table");
		lblNewLabel_5.setBounds(44, 276, 99, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
		textField_6 = new JTextField();
		textField_6.setBounds(181, 273, 126, 20);
		frame.getContentPane().add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Sample_request");
		lblNewLabel_6.setBounds(44, 312, 99, 14);
		frame.getContentPane().add(lblNewLabel_6);
		
		textField_7 = new JTextField();
		textField_7.setBounds(181, 309, 126, 20);
		frame.getContentPane().add(textField_7);
		textField_7.setColumns(10);
		
		JButton btnRunApi = new JButton("Run API");
		btnRunApi.setBounds(591, 548, 89, 23);
		frame.getContentPane().add(btnRunApi);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(181, 529, 165, 14);
		frame.getContentPane().add(progressBar);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(683, 11, 17, 589);
		frame.getContentPane().add(scrollBar);
		
	}
}

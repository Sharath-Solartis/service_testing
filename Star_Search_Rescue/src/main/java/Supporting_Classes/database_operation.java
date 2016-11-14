package Supporting_Classes;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Rating_API.Star_Search_Rescue.app_json_db;




public class database_operation
{
	private static Connection conn = null;
	private static final String JDBC_DRIVER =app_json_db.config.getProperty("jdbc_driver");
	//"com.mysql.jdbc.Driver"
	private static final String DB_URL = app_json_db.config.getProperty("db_url");
	//"jdbc:mysql://192.168.35.2:3391/Search_rescue";
	private static final String USER = app_json_db.config.getProperty("db_username");
	//"root";
	private static final String PASS = app_json_db.config.getProperty("db_password");
	//"password";
	
	private String query = null;
	
	private Statement stmt = null;
	private ResultSet rs = null;
	
	public static void conn_setup() throws SQLException,ClassNotFoundException
	{
		
		if(conn == null)
		{
				
				Class.forName(JDBC_DRIVER);
			
			
				conn = DriverManager.getConnection(DB_URL,USER,PASS);
			
		}
			
	}
	
	public static void close_conn()
	{
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		conn = null;
	}
	
	public void get_dataobjects(String query)throws SQLException
	{
		this.query = query;
		
			stmt = conn.createStatement(ResultSet.TYPE_FORWARD_ONLY,ResultSet.CONCUR_UPDATABLE);
			 rs =    stmt.executeQuery(this.query);
		
			rs.first();
		
		
	}
	
	public boolean move_forward() throws SQLException
	{
		
			return rs.next();
		
	}
	
	public String read_data(String column_name) throws SQLException
	{
		return rs.getString(column_name);
	}
	
	public void write_data(String column_name,String value) throws SQLException
	{
		rs.updateString(column_name, value);
	}
	
	public void update_row() throws SQLException
	{
		rs.updateRow();
	}
	
	
	public static void main(String args[]) throws ClassNotFoundException, SQLException
	{
		database_operation.conn_setup();
		database_operation input = new database_operation();
		database_operation output = new database_operation();
		input.get_dataobjects("select * from input_data;");
		output.get_dataobjects("Select * from output_table");
			System.out.println(input.read_data("test_id")+"  -  "+output.read_data("testcase"));
			input.write_data("Flag_for_execution", "N");
			output.write_data("status", "fail");
			input.update_row();
			output.update_row();
		database_operation.close_conn();
	}
	
}

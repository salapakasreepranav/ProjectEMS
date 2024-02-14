package sp.sree.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtil
{
	private JdbcUtil()
	{
		
	}
	
	static
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static Connection connection = null;
	
	public static Connection getJdbcConnection() throws IOException, SQLException
	{
		FileInputStream fis = new FileInputStream("E:\\iNeuron\\Servlets\\Projects\\ProjectEMS\\src\\sp\\sree\\properties\\application.properties");
		Properties properties = new Properties();
		properties.load(fis);
		
		if(connection == null)
			connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("username"), properties.getProperty("password"));
		
		return connection;
	}
}

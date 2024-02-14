package sp.sree.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

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
		//return getPhysicalConnection(); //for normal connection (uncomment for normal connection and also change application.properties file)
		return getLogicalConnection(); //for connection pooling
	}
	
	private static Connection getLogicalConnection() throws FileNotFoundException, IOException, SQLException 
	{
		HikariConfig config = new HikariConfig("E:\\iNeuron\\Servlets\\Projects\\ProjectEMS\\src\\sp\\sree\\properties\\application.properties");
		HikariDataSource dataSource = new HikariDataSource(config);
		
		
		return dataSource.getConnection();
	}

	private static Connection getPhysicalConnection() throws FileNotFoundException, IOException, SQLException {
		FileInputStream fis = new FileInputStream("E:\\iNeuron\\Servlets\\Projects\\ProjectEMS\\src\\sp\\sree\\properties\\application.properties");
		Properties properties = new Properties();
		properties.load(fis);
		
		if(connection == null)
			connection = DriverManager.getConnection(properties.getProperty("url"), properties.getProperty("username"), properties.getProperty("password"));
		
		return connection;
	}
}

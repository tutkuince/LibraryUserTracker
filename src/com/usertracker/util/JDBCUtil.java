package com.usertracker.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class JDBCUtil {
	private static Properties settings = new Properties();

	private static String url;
	private static String username;
	private static String password;
	
	static {	// static initializer
		InputStream in = JDBCUtil.class.getClassLoader().getResourceAsStream("com/usertracker/properties/jdbc.properties"); 

		try {
			
			settings.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		url = settings.getProperty("url");
		username = settings.getProperty("username");
		password = settings.getProperty("password");
	}
	
	public static synchronized Connection getConnection() {
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setURL(url);
		dataSource.setUser(username);
		dataSource.setPassword(password);
		
		Connection conn = null;
		try {
			 conn = dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

}

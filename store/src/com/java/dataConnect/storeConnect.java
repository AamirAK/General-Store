package com.java.dataConnect;

import java.sql.Connection;
import java.sql.DriverManager;

public class storeConnect {
    private static Connection con;
	
	private storeConnect()
	{
		try
		{
		Class.forName("com.mysql.cj.jdbc.Driver");
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/market","root","EX4IWPI6_sql");
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
	}
	public  static Connection  getConnection()
	 {
		storeConnect d= new storeConnect();
		
		 return(con);
	 }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lhf;


import java.sql.*;

public class jdbcHelper {

	private Connection conn;
	boolean isConnect;
    String url = "jdbc:mysql://localhost:3306/user?serverTimezone=GMT&useSSL=false";
    String username = "root";
    String password = "root";
    
	jdbcHelper() throws ClassNotFoundException, SQLException{
	    Class.forName("com.mysql.cj.jdbc.Driver");
	    conn = DriverManager.getConnection(url,username,password);
	    if(conn!=null)
	    	isConnect=true;
	    else isConnect=false;
	}

	public Connection getConnection() {
		return conn;
	}


	public void setConnection(Connection connection) {
		this.conn = connection;
	}
	
	public boolean IsConnect()
	{
		return isConnect;
	}
	
	public ResultSet Querry(String QuerryString) throws SQLException
	{
		if(!IsConnect())
		{
			ResultSet rs = null;
			return null;
		}
	    
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(QuerryString);
		return rs;
	}
	
	public int Update(String sql) throws SQLException
	{
		return Excute(sql);
	}
	
	public int Delete(String sql) throws SQLException
	{
		return Excute(sql);
	}
	
	public int Insert(String sql) throws SQLException
	{
		return Excute(sql);
	}
	
	public int Excute(String msg) throws SQLException
	{
		return conn.createStatement().executeUpdate(msg);
//		return conn.prepareStatement(msg).executeUpdate();
	}
	
	public String printInfo()
	{
		return "Successfully use JavaBean";
	}
	
}

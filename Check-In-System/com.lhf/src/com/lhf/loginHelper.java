/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lhf;

import java.sql.*;
import java.text.MessageFormat;


public class loginHelper {
        
	String table = "userinfo";
	
    jdbcHelper jd ;
    
    public loginHelper()
    {
    	try {
			jd = new jdbcHelper();
		} catch (Exception e) {
			e.printStackTrace();
		} 
    }
//    *  private Long id;
//    private String name;
//    private String password;
//    private String email;//注册账号
//    private int status;//激活状态
//    private String validateCode;//激活码
//    private Date  registerTime;//注册时间
    public boolean ApplyRegister(String userName,String passWord,String Email,String validateCode,String registerTime,String status) throws SQLException
    {
    	String sql = "insert into {0}({1},{2},{3},{4},{5},{6}) values(''{7}'',''{8}'',''{9}'',''{10}'',''{11}'',''{12}'')";
    	String s =  MessageFormat.format(sql, table,"username","password","Email","validateCode","registerTime","status",userName,passWord,Email,validateCode,registerTime,status);
    	System.out.println(s);
    	int index = jd.Insert(s);
        if(index==1)
        	return true;
        else return false;
    }
    
    public ResultSet FindUserByEmail(String Email) throws SQLException
    {
    	String sql = "select * from {0} where Email=''{1}''";
    	return jd.Querry(MessageFormat.format(sql, table,Email));
    }
    
    
    public ResultSet FindUserByName(String userName) throws SQLException
    {
    	String sql = "select * from {0} where username=''{1}''";
    	return jd.Querry(MessageFormat.format(sql, table,userName));
    }
    
    public boolean ChangePassword(String userName,String passWord) throws SQLException
    {
    	String sql = "update {0} set {1}=''{2}'' where {3}=''{4}''";
    	int index = jd.Update(MessageFormat.format(sql, table,"password",passWord,"username",userName));
    	if(index>0)
    		return true;
    	else return false;
    }
    
    public boolean ApplyActivateUpdate(String validateCode,String Email,String status,String RegisterTime) throws SQLException
    {
    	ResultSet rs = FindUserByEmail(Email);
    	rs.last();
    	if(rs.getRow()<=0)
    		return false;
    	
    	String sql = "update {0} set validateCode=''{1}'',status=''{2}'',registerTime=''{3}'' where Email=''{4}''";
    	if(jd.Update(MessageFormat.format(sql, table,validateCode,status,RegisterTime,Email))>0)
    	return true;
    	else return false;
    } 
    
    public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public jdbcHelper getJd() {
		return jd;
	}

	public void setJd(jdbcHelper jd) {
		this.jd = jd;
	}

	public int check(String userName,String passWord) throws SQLException
    {
    	if(IsExistUsername(userName))
    	{
    		if(IsUsernameAndPasswordCorrected(userName,passWord))
    		{
    			if(IsActivateStatus(userName))
    				return 3;
    			else return 2;
    		}
    		else return 1;
    	}
    	else return 0;
    }
    
	public boolean SetResetInfo(String userName,String Email,String resetTime,String resetCode) throws SQLException
	{
		String sql = "update {0} set resetTime=''{1}'',resetCode=''{2}'' where username=''{3}'' and Email=''{4}''";
		String s = MessageFormat.format(sql, table,resetTime,resetCode,userName,Email);
		System.out.println("Reset sql="+ s);
		if(jd.Update(s) > 0)
			return true;
		else return false;
	}
	
    public String toString(String userName,String passWord) throws SQLException
    {
		String sql = "select * from {0}";
		ResultSet st = jd.Querry(MessageFormat.format(sql, table));
		return Integer.toString(st.getRow());
    }
    
	public boolean IsExistUsername(String UserName) throws SQLException
	{
		String sql = "select * from {0} where username=''{1}''";
		ResultSet st = jd.Querry(MessageFormat.format(sql, table,UserName));
		st.last(); 
		if(st.getRow() > 0)
			return true;
		else return false;
	}
	public boolean IsExistUsernameAndEmail(String UserName,String Email)throws SQLException
	{
		String sql = "select * from {0} where username=''{1}'' and Email=''{2}''";
		ResultSet st = jd.Querry(MessageFormat.format(sql, table,UserName,Email));
		st.last(); 
		if(st.getRow() > 0)
			return true;
		else return false;
	}
	public boolean IsActivateStatus(String UserName) throws SQLException
	{
		String sql = "select * from {0} where username=''{1}'' and status=''1''";
		ResultSet st = jd.Querry(MessageFormat.format(sql, table,UserName));
		st.last(); 
		if(st.getRow() > 0)
			return true;
		else return false;
	}
	
	public boolean IsUsernameAndPasswordCorrected(String UserName,String Password) throws SQLException
	{
		String sql = "select * from {0} where username=''{1}'' and password=''{2}''";
		ResultSet st = jd.Querry(MessageFormat.format(sql, table,UserName,Password));
		st.last(); 
		if(st.getRow() > 0)
			return true;
		else return false;
	}
}
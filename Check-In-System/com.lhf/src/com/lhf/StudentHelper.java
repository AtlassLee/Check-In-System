/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lhf;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;

public class StudentHelper {
	String table = "student";
	
    jdbcHelper jd ;
    
    public StudentHelper()
    {
    	try {
			jd = new jdbcHelper();
		} catch (Exception e) {
			e.printStackTrace();
		} 
    }
    
    public ResultSet GetResultByClass(String classid) throws SQLException
    {
    	String sql = "select * from {0} where classname=''{1}''";
    	return jd.Querry(MessageFormat.format(sql, table,classid));
    }
    
    
    public ResultSet GetResult() throws SQLException
    {
    	String sql = "select * from {0}";
    	return jd.Querry(MessageFormat.format(sql, table));
    }
}
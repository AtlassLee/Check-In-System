/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lhf;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;

public class AttenceHelper {

	String table = "attence";
	
    jdbcHelper jd ;
    
    public AttenceHelper()
    {
    	try {
			jd = new jdbcHelper();
		} catch (Exception e) {
			e.printStackTrace();
		} 
    }
    
    public boolean Add(String student,String status,String courseid,String coursename) throws SQLException
    {
    	System.out.println("courseid=" + courseid + "coursename=" + coursename);
    	String sql = "insert into {0}(studentid,status,courseid,coursename) values(''{1}'',''{2}'',''{3}'',''{4}'')";
    	int index = jd.Insert(MessageFormat.format(sql, table,student,status,courseid,coursename));
    	if(index==1)
    		return true;
    	else return false;
    }

    public ResultSet GetResultByName(String studentid) throws SQLException
    {
    	String sql = "select * from {0} where studentid=''{1}''";
		return jd.Querry(MessageFormat.format(sql, table,studentid));
    }
    public ResultSet GetResultByCourse(String courseName) throws SQLException
    {
    	String sql = "select * from {0} where coursename=''{1}''";
    	return jd.Querry(MessageFormat.format(sql, table,courseName));
    }
    public ResultSet GetResult() throws SQLException
    {
    	String sql = "select * from {0}";
    	return jd.Querry(MessageFormat.format(sql, table));
    }
    
    public boolean Sign(String studentId,String courseId) throws SQLException
    {
    	String sql = "update {0} set status=''1'' where studentid=''{1}'' and courseid=''{2}''";
    	int index = jd.Update(MessageFormat.format(sql, table,studentId,courseId));
    	if(index==1)
    		return true;
    	else return false;
    }
}
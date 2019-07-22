/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lhf;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;

public class CourseHelper {

	String table = "course";
	
    jdbcHelper jd ;
    
    public CourseHelper()
    {
    	try {
			jd = new jdbcHelper();
		} catch (Exception e) {
			e.printStackTrace();
		} 
    }
    
    public boolean Add(int id,String Teacher,String startTime,String endTime,String classId,String courseName) throws SQLException
    {
    
    	String sql = "insert into {0}(courseid,teacher,startTime,endTime,classname,coursename) values(''{1}'',''{2}'',''{3}'',''{4}'',''{5}'',''{6}'')";
    	int index = jd.Insert(MessageFormat.format(sql, table,id,Teacher,startTime,endTime,classId,courseName));
    	if(index==1)
    		return true;
    	else return false;
    }
    
	public int GetTotalCounts() throws SQLException
	{
		String sql = "select * from {0}";
		ResultSet rs =jd.Querry(MessageFormat.format(sql, table));
		if(!rs.next())
			return 0;
		rs.last();
		int index = rs.getInt(1);
    	return index>0?index:0;
	}
	
    public ResultSet GetCourseNameResult() throws SQLException
    {
    	String sql = "select coursename from {0} group by coursename";
		return jd.Querry(MessageFormat.format(sql, table));
    }
    
    public ResultSet GetResult() throws SQLException
    {
    	String sql = "select * from {0}";
    	return jd.Querry(MessageFormat.format(sql, table));
    }
    public boolean DeleteCourseById(String id) throws SQLException
    {
    	String sql = "delete from {0} where courseid=''{1}''";
    	int index =jd.Delete(MessageFormat.format(sql, table,id));
    	if(index==1)return true;
    	else return false;
    }
	
}
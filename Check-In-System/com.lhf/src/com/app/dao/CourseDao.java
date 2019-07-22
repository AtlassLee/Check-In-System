/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;
import com.lhf.CourseHelper;
import com.lhf.StudentHelper;
import com.lhf.AttenceHelper;
import com.code.model.CourseModel;
import com.code.model.StudentAttenceModel;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import com.code.model.CourseAttenceModel;
@Repository
public class CourseDao {

	CourseHelper ch = new CourseHelper();
	StudentHelper sh = new StudentHelper();
	AttenceHelper ah = new AttenceHelper();
	
	public void AddCourse(CourseModel cm) throws SQLException
	{
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        
		int counts = ch.GetTotalCounts() + 1;
		System.out.println("===========counts==========" + counts);
		if(ch.Add(counts, cm.getTeacher(), cm.getStartTime(),cm.getEndTime(), cm.getClassName(),cm.getCourseName()))
		{
			System.out.println("EN---------");
			ResultSet rs = sh.GetResultByClass(cm.getClassName());
			String StudentName[] = new String[100];
			int cnt = 0;
		while(rs.next())
			StudentName[cnt++] = rs.getString(1);
			
		System.out.println("EN1---------");
		for(int i=0;i<cnt;i++)
		{
			ah.Add(StudentName[i], "0", String.valueOf(counts),cm.getCourseName());
		}
		
		
		}
		
	}
	
 	public JSONArray GetStudentAttenceInfo() throws SQLException
 	{
 	       ResultSet rs = sh.GetResult();
 	       List<StudentAttenceModel> st = new ArrayList<StudentAttenceModel>();
 	       while(rs.next())
 	       {
 	    	   StudentAttenceModel sam = new StudentAttenceModel();
 	    	   sam.setStudentId(rs.getString(1));
 	    	   sam.setStudentName(rs.getString(2));
 	    	   sam.setClassName(rs.getString(6));
 	    	   st.add(sam);
 	       }
 	         
 	       for(int i=0;i<st.size();i++)
 	       {
 	    	   rs = ah.GetResultByName(st.get(i).getStudentId());
 	    	  
 	    	   if(rs==null)
 	    		   continue;
 	    	   
 	    	   float totalcourses = 0;
 	    	   float truant = 0 ;
 	    	   while(rs.next())
 	    	   {
 	    		   totalcourses++;
 	    		   if(rs.getInt(2)==0)
 	    			   truant++;
 	    	   }
 	    	   float rate;
 	    	   if(truant==0)
 	    		   rate = 1;
 	    	   else
 	    	   rate = 1 - (truant/totalcourses);
 		 	     BigDecimal bg = new BigDecimal(rate);
 		 	     
 	    	   st.get(i).setRate(bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()*100);
 	    	   st.get(i).setTruant((int)truant);
 	    	   
 	       }
 	       return JSONArray.fromObject(st);
 	}
 	
  	public JSONArray GetAttenceInfoByCourse() throws SQLException
  	{
  		ResultSet rs = ch.GetCourseNameResult();
	       List<String> st = new ArrayList<String>();
	       while(rs.next())
	       {
	    	   st.add(rs.getString(1));
	       }
	       
		    List<CourseAttenceModel> st1 = new ArrayList<CourseAttenceModel>();
	       for(int i=0;i<st.size();i++)
	       {
	    	   rs = ah.GetResultByCourse(st.get(i));
	    	   float totalcourses = 0;
	    	   float truant = 0 ;
	    	   while(rs.next())
	    	   {
	    		   totalcourses++;
	    		   if(rs.getInt(2)==0)
	    			   truant++;
	    	   }
	    	   
	    	   double rate;
	    	   if(truant==0)
	    		   rate = 1;
	    	   else
	    	   rate = 1 - (truant/totalcourses);
	    	   System.out.println("totalcourses=" + totalcourses + " truant="+ truant );
	 	     BigDecimal bg = new BigDecimal(rate);
	 	     CourseAttenceModel cam = new CourseAttenceModel();
	 	     cam.setCourseName(st.get(i));
	 	     cam.setRate(bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()*100);
	 	      st1.add(cam);
	       }
	       JSONArray jsonArray = JSONArray.fromObject(st1);   
	       return jsonArray;
 	}
	
	public JSONArray GetAllAttenceInfo() throws SQLException
	{
		ResultSet rs = ah.GetResult();
 	   float totalcounts = 0;
 	   float truant = 0 ;
		while(rs.next())
		{
			totalcounts++;
			if(rs.getInt(2)==0)
				truant++;
		}	    	   double rate;
 	   if(truant==0)
		   rate = 1;
	   else
	   rate = 1 - (truant/totalcounts);
	     BigDecimal bg = new BigDecimal(rate);
 	     
		JSONArray jsonArray = new JSONArray();
		jsonArray.add(bg.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue()*100);
		return jsonArray;
	}
	public JSONArray GetAllCourseInfo() throws SQLException, ParseException
	{
		List<CourseModel> list = new ArrayList<CourseModel>();
		ResultSet rs = ch.GetResult();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		while(rs.next())
		{
			CourseModel cm = new CourseModel();
			cm.setId(rs.getString(1));
			cm.setTeacher(rs.getString(2));
			Date d1 = sdf.parse(rs.getString(3));
			Date d2 = sdf.parse(rs.getString(4));
			cm.setStartTime(sdf1.format(d1));
			cm.setEndTime(sdf1.format(d2));
			cm.setClassName(rs.getString(5));
			cm.setCourseName(rs.getString(6));
			list.add(cm);
		}
		return JSONArray.fromObject(list);
	}
	
	public void DeleteCourse(String id) throws SQLException
	{
		ch.DeleteCourseById(id);
	}
	public boolean Sign(String studentId,String courseId) throws SQLException
	{
		if(ah.Sign(studentId, courseId))
		return true;
		else return false;
	}
	
}
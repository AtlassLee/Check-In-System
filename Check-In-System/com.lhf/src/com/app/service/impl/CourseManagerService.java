/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.service.impl;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.CourseDao;
import java.text.SimpleDateFormat;
import com.code.model.CourseModel;

@Service
public class CourseManagerService {


	
	public void ProcessAddCourse(String teacher,String startTime,String EndTime,String classId,String courseName) throws SQLException, ParseException
	{
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
        
        CourseModel cm = new CourseModel();
        cm.setTeacher(teacher);
        cm.setStartTime(startTime);
        cm.setEndTime(EndTime);
        cm.setClassName(classId);
        cm.setCourseName(courseName);
        CourseDao courseDao = new CourseDao();
		courseDao.AddCourse(cm);
		
	}
}
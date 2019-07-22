/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.web.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.text.ParseException;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.app.service.impl.CourseManagerService;
import com.app.service.impl.RegisterValidateService;
import com.app.service.impl.ResetValidService;
import com.app.tools.ServiceException;

@Controller
public class CourseManagerController {

	@Resource
	private CourseManagerService cs;
	
    @RequestMapping(value="/user/Course",method={RequestMethod.GET,RequestMethod.POST})
    public ModelAndView  load(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException, SQLException, ParseException
    {
    
    	ModelAndView mav = new ModelAndView();
    	request.setCharacterEncoding("GBK");    
    	
    	String action = request.getParameter("action");
    	
    	if("add".equals(action))
    	{
    		
    	String userName = (String) request.getSession().getAttribute("username");
    	String startTime = request.getParameter("starttime");
    	String endTime = request.getParameter("endtime");
    	String classId = request.getParameter("classname");
    	String courseName = request.getParameter("coursename");
    	
    	cs.ProcessAddCourse(userName, startTime, endTime, classId, courseName);
    	mav.setViewName("/WEB-INF/jsp/Course/AddCourse_success.jsp");
    	}
    	
    	return mav;
    }
    
}
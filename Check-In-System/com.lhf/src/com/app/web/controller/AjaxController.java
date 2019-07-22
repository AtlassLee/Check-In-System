/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.web.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.app.dao.CourseDao;
import com.app.service.impl.CourseManagerService;
import com.lhf.*;

@Controller
public class AjaxController {

	@Resource
	private CourseManagerService cs;
	
	@RequestMapping(value="user/ajax",method={RequestMethod.GET,RequestMethod.POST})
    public ModelAndView  load(HttpServletRequest request,HttpServletResponse response) throws SQLException, IOException, ParseException
    {
		//ModelAndView mav = new ModelAndView();
		String action = request.getParameter("action");
        response.setContentType("text/html;charset=GBK");
        response.setCharacterEncoding("GBK");
		PrintWriter out = response.getWriter();
		loginHelper lg = new loginHelper();
		
//		if("register".equals(action))
//		{
//		String UserName = request.getParameter("username");
//		String passWord = request.getParameter("password");
//		String Email = request.getParameter("email");
//		
//		if(lg.IsExistUsername(UserName))
//		{
//			out.println("用户名存在");
//		}
//		else out.println("用户名不存在");
//		return null;
//		}
		if("register".equals(action))
		{
			String UserName = request.getParameter("username");
			if(lg.IsExistUsername(UserName))
			{
				out.println("用户名存在");
			}
			else out.println("用户名不存在");
			return null;
		}
		else if("login".equals(action))
		{
			String userName = request.getParameter("username");
			String passWord = request.getParameter("password");
			int index = lg.check(userName, passWord);
			if(index == 0)
				out.println("用户名不存在");
			else if(index == 1)
				out.println("密码错误");
			else if(index == 2)
				out.println("状态未激活");
			else if(index == 3)
			{
	        	request.getSession().setAttribute("username",request.getParameter("username"));
	        	request.getSession().setAttribute("password", request.getParameter("password"));
	        	out.println("登录成功");
			}
				
			return null;
		}
		else if("reset".equals(action))
		{
			String UserName = request.getParameter("username");
			String Email = request.getParameter("email");
			boolean index = lg.IsExistUsernameAndEmail(UserName, Email);
			if(index)
				out.print("用户名和邮箱存在");
			else out.println("用户名和邮箱不存在");
			return null;
		}
		else if("reset1".equals(action))
		{
	    	String userName=request.getParameter("username");
	    	String passWord1=request.getParameter("psw1");
	    	String passWord2=request.getParameter("psw2");
	    	
	    	if(passWord1==null || passWord1.length()==0 || passWord2==null || passWord2.length()==0)
	    	{
	    		out.println("输入的密码不能为空");
	    		return null;
	    	}

	    	if(!passWord1.equals(passWord2)) 
	    	{
	    		out.println("输入的两次密码不相同");
	    		return null;
	    	}
				boolean flag = lg.IsExistUsername(userName);
				if(flag==false)
					out.println("修改失败");
				else out.println("修改成功");
			return null;
	
        }
		else if("QuerryStudent".equals(action))
		{
			CourseDao courseDao = new CourseDao();
			out.println(courseDao.GetStudentAttenceInfo().toString());
		}
		else if("QuerryCourse".equals(action))
		{
			CourseDao courseDao = new CourseDao();
			out.println(courseDao.GetAttenceInfoByCourse().toString());
		}
		else if("QuerryAll".equals(action))
		{
			CourseDao courseDao = new CourseDao();
			out.println(courseDao.GetAllAttenceInfo().toString());
		}
		else if("QuerryAllCourse".equals(action))
		{
			CourseDao courseDao = new CourseDao();
			out.println(courseDao.GetAllCourseInfo());
		}
		else if("AddCourse".equals(action))
    	{
    	String userName = (String) request.getSession().getAttribute("username"); 
    	System.out.println("~~~~~~~~~~~getsession username=" + userName);
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddHH:mm");
    	SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
    	String startTime = request.getParameter("starttime"); //starttime=2018-05-31T23:10
    	String endTime = request.getParameter("endtime");
    	Date d1 = sdf.parse(startTime);
    	Date d2 = sdf.parse(endTime);
    	String classId = request.getParameter("classname");
    	String courseName = request.getParameter("coursename");
         
    	cs.ProcessAddCourse(userName, sdf1.format(d1), sdf1.format(d2), classId, courseName);
        out.println("success");
    	}
		else if("DeleteCourse".equals(action))
		{
			String courseId = request.getParameter("courseid");
			CourseDao courseDao = new CourseDao();
			courseDao.DeleteCourse(courseId);
		}
		else if("sign".equals(action))
		{
			String courseId = request.getParameter("courseid");
			String studentId = request.getParameter("studentid");
			CourseDao courseDao = new CourseDao();
			if(courseDao.Sign(studentId, courseId))
				out.println("成功");
			else out.println("失败");
		}
		return null;
    }
}
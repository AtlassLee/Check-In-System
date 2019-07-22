/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.service.impl;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.*;

import com.code.model.UserModel;
import com.app.tools.RandomString;
import com.app.tools.SendEmail;
import com.app.tools.ServiceException;
import com.app.dao.UserDao;
import com.app.tools.MD5Util;

@Service
public class ResetValidService {

@Autowired
private UserDao userDao;
@Autowired
private RandomString rds;

        public void ProcessResetApply(String userName,String Email) throws SQLException
        {
        	UserModel user = new UserModel();
        	
        	user.setName(userName);
        	user.setEmail(Email);
        	String ResetCode = rds.getRandomString(10);
        	user.setResetCode(MD5Util.encode2hex(ResetCode));
        	Date date = new Date();
        	user.setResetTime(date);
        	userDao.saveResetInfo(user);
        	
            StringBuffer sb=new StringBuffer("点击下面链接进行密码重置，请在15分钟内重置密码，否则该链接失效！</br>");
            sb.append("<a href=\"http://localhost:8080/SpringMVCTest/user/register?action=reset2&username=");
            sb.append(userName); 
            sb.append("&resetCode="); 
            sb.append(user.getResetCode());
            sb.append("\">http://localhost:8080/SpringMVCTest/user/register?action=reset2&username="); 
            sb.append(userName);
            sb.append("&resetCode=");
            sb.append(user.getResetCode());
            sb.append("</a>");
        	
            SendEmail.send(Email, sb.toString());
            
        	System.out.println("ProcessResetApply Successfully!");
        }
        
        public void ProcessResetActivate(String userName,String resetCode) throws ParseException, SQLException, ServiceException
        {
        	UserModel user = new UserModel();
        	user = userDao.findUserByName(userName);
        	if(user!=null)
        	{
        		Date currentTime = new Date();
        		System.out.println("curTime="+ currentTime + "LastResetTime=" + user.getLastResetTime());
        		if(currentTime.before(user.getLastResetTime()))
        		{
        			if(resetCode.equals(user.getResetCode()))
        			{
        				return ;
        			}
        			else throw new ServiceException("请求重置密码失败");
        		}
        		else throw new ServiceException("该链接已经过期！");
        	}
        	else throw new ServiceException("发生了错误！该用户名不存在！");

        }
        
        public void ProcessResetPassword(String userName,String passWord) throws SQLException
        {
        	userDao.updatePassword(userName, passWord);
        }
}
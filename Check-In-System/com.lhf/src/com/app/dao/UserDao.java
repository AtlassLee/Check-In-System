/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.dao;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;



import org.springframework.stereotype.Repository;

import com.code.model.UserModel;
import com.lhf.*;
import java.sql.*;
/**
 * 
 * @author Qixuan.Chen
 */

public class UserDao {
    
//   public HashMap<String, String> map=new HashMap<String, String>();
   loginHelper lg = new loginHelper();
    /**
     * @throws SQLException 
     * @保存注册信息
     *  private Long id;
        private String name;
        private String password;
        private String email;//注册账号
        private int status;//激活状态
        private String validateCode;//激活码
        private Date  registerTime;//注册时间
     */
    public void save(UserModel user) throws SQLException{
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
			if(lg.ApplyRegister(user.getName(), user.getPassword(), user.getEmail(), user.getValidateCode(), sdf.format(user.getRegisterTime()), String.valueOf(user.getStatus())))
			        System.out.println("ApplyRegister Successfully");
			else 
				System.out.println("ApplyRegister Failed");
    }
    
    
    public void saveResetInfo(UserModel user) throws SQLException
    {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            String date = sdf.format(user.getResetTime());
            if(lg.SetResetInfo(user.getName(), user.getEmail(), date , user.getResetCode()))
            	 System.out.println("ApplyRetCode Successfully");
			else 
				System.out.println("ApplyResetCode Failed");
    }
    
    /**
     * @throws SQLException 
     * @更新 user
     */
    public void update(UserModel user) throws SQLException{

        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");  
        lg.ApplyActivateUpdate(user.getValidateCode(), user.getEmail(),String.valueOf(user.getStatus()), sdf.format(user.getRegisterTime()));

    }
    
    public void updatePassword(String userName,String passWord) throws SQLException
    {
      lg.ChangePassword(userName, passWord);
    }
    /**
     * @throws ParseException 
     * @throws SQLException 
     * @查找信息
     */
    public UserModel findUserByEmail(String email) throws ParseException, SQLException{  
        UserModel user=new UserModel();  
        ResultSet rs = lg.FindUserByEmail(email);
        
        if(rs==null)
        	return user;
        rs.last();
        user.setEmail(email);
        user.setName(rs.getString(1));
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");  
        user.setRegisterTime(sdf.parse(rs.getString(5)));
        user.setValidateCode(rs.getString(4));
        user.setStatus(Integer.parseInt(rs.getString(3)));

        return user;  
    }  
    
    public UserModel findUserByName(String userName) throws ParseException, SQLException{  
        UserModel user=new UserModel();  
        ResultSet rs = lg.FindUserByName(userName);
        
        if(rs==null)
        	return user;
        rs.last();
        user.setEmail(rs.getString(6));
        user.setName(userName);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");  
        user.setResetTime(sdf.parse(rs.getString(7)));
        user.setResetCode(rs.getString(8));

        return user;  
    }  
    

}
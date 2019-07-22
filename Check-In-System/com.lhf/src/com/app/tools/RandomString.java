/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.tools;
import org.springframework.stereotype.Repository;
import java.util.Random;

@Repository
public class RandomString {
	public String getRandomString(int len)
	{
		Random rd = new Random();
		String s = "QWERTYUIOPASDFGHJKLZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567890";
		String str="";
		for(int i=0;i<len;i++)
		{
			int index = rd.nextInt(s.length());
			str+=s.charAt(index);
		}
		return str;
	}
}
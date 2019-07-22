/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.tools;

public class ServiceException extends Exception {
	private static final long serialVersionUID = -1708015121235851228L;
	
	public ServiceException(String message) {
		super(message);
	}
}
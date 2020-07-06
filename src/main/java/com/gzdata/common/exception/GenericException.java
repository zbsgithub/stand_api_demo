package com.gzdata.common.exception;


/**
 * 【简单异常】 抛出此异常，给用户友好的提示信息
 * 
 */
public class GenericException extends RuntimeException {

	private static final long serialVersionUID = -8233749574972852877L;

	/*
	 * 提示信息
	 */
	public GenericException(String message) {
		super(message);
	}

	/*
	 * 指定异常及该异常的（提示信息）
	 */
	public GenericException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
}

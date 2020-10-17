package com.ironrookcomputing.utils;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class LogUtil {
	// modularization of exception handling in my app
	public static void logException(Exception ex,
			@SuppressWarnings("rawtypes") Class c) {
		Logger log = Logger.getLogger(c);
		log.error(ex.getClass() + ": " + ex.getMessage());
		for(StackTraceElement s : ex.getStackTrace()) {
			log.warn(s.getLineNumber() + ": " + s.getClassName());
		}
	}
	
	public static void rollback(Exception ex, Connection connection,
			@SuppressWarnings("rawtypes") Class c) {
		LogUtil.logException(ex, c);
		try {
			connection.rollback();
		} catch (SQLException e1) {
			LogUtil.logException(e1, c);
		}
	}
}
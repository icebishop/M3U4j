package org.ice.media.m3u.exception;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class M3UReaderException extends Exception {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass().getSimpleName());

	public M3UReaderException(String message) {
		super(message);
	}
	
	public M3UReaderException(Exception e) {
		super(e);
		logger.error(e.getMessage());
	}

}

package com.opolishchuk.zibert.logging;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class Logger {
	
	public enum LogLevel{
		INFO(0), WARN(1), DEBUG(2);
		
		private int level;
		
		LogLevel(int level){
			this.level = level;
		}
		
		public int getLevel(){
			return level;
		}
	}

	private static Logger instance = null;
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private final LogLevel logLevel;
	private final Set<PrintStream> destinations = new HashSet<>();
	private final Calendar cal = Calendar.getInstance();
	
	private Logger(OutputStream dest, LogLevel logLevel){
		addLogger(dest);
		instance = this;
		this.logLevel = logLevel;
	}
	
	public void addLogger(OutputStream dest){
		destinations.add(new PrintStream(dest));
	}
	
	public static Logger getLogger(){
		OutputStream defaultDestination = System.out;
		
		return instance == null ? new Logger(defaultDestination, LogLevel.INFO) : instance;
	}
	
	public void info(String message){
		log(LogLevel.INFO, message);
	}

	private void log(LogLevel level, String message) {
		if(level.compareTo(this.logLevel) < 0){
			destinations.forEach(PrintStream::println);
		}
	}
	
}

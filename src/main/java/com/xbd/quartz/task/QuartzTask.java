package com.xbd.quartz.task;

public interface QuartzTask {
	String getName();
	
	String getGroup();
	
	String getDescription();
	
	void execute() throws Exception;

	String getMethod();

}

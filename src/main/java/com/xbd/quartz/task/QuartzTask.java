package com.xbd.quartz.task;

public interface QuartzTask {

	public final static String TASK_GROUP_DEFAULT = "DEFAULT";

	String getName();
	
	String getGroup();
	
	String getDescription();
	
	void execute() throws Exception;

	String getMethod();

}

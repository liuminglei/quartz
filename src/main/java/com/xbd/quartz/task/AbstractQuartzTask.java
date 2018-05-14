package com.xbd.quartz.task;

import com.xbd.quartz.DefaultQuartzJobBean;

public abstract class AbstractQuartzTask implements QuartzTask {
	
	public final static String TASK_GROUP_DEFAULT = "DEFAULT";
	
	public abstract String getName();
	
	public String getGroup() {
		return TASK_GROUP_DEFAULT;
	}
	
	public abstract String getDescription();
	
	public abstract void execute() throws Exception;

	public String getMethod() {
		return DefaultQuartzJobBean.JOBDETAIL_VALUE_TARGETMETHOD;
	}

}

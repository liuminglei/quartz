package com.xbd.quartz.task;

/**
 * <p>
 * 定时任务目标类接口
 * </p>
 *
 * @author 小不点
 */
public interface QuartzTask {

	/**
	 * 默认定时任务分组
	 */
	public final static String TASK_GROUP_DEFAULT = "DEFAULT";

	String getName();
	
	String getGroup();
	
	String getDescription();
	
	void execute() throws Exception;

	String getMethod();

}

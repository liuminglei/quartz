package com.xbd.quartz.task;

import com.xbd.quartz.DefaultQuartzJobBean;

/**
 * <p>
 * 定时任务目标类基类
 * </p>
 *
 * @author 小不点
 */
public abstract class AbstractQuartzTask implements QuartzTask {
	
	public abstract String getName();

	/**
	 * <p>
	 * 如不实现此方法，则返回默认分组
	 * </p>
     *
     * @see QuartzTask#TASK_GROUP_DEFAULT
     *
	 */
	public String getGroup() {
		return TASK_GROUP_DEFAULT;
	}
	
	public abstract String getDescription();
	
	public abstract void execute() throws Exception;

    /**
     * <p>
     * 如不实现此方法，则返回默认执行方法
     * </p>
     *
     * @see DefaultQuartzJobBean#JOBDETAIL_VALUE_TARGETMETHOD
     *
     */
	public String getMethod() {
		return DefaultQuartzJobBean.JOBDETAIL_VALUE_TARGETMETHOD;
	}

}

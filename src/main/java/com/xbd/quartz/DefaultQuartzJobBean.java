package com.xbd.quartz;

import java.lang.reflect.Method;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class DefaultQuartzJobBean extends QuartzJobBean {
	private static final Logger logger = LoggerFactory.getLogger(DefaultQuartzJobBean.class.getName());

	public final static String JOBDETAIL_KEY_TARGETOBJECT = "targetObject";
	public final static String JOBDETAIL_KEY_TARGETMETHOD = "targetMethod";
	public final static String JOBDETAIL_VALUE_TARGETMETHOD = "execute";
	
	private String targetObject;
	private String targetMethod;

	@Override
	protected void executeInternal(JobExecutionContext context)
			throws JobExecutionException {

		try {
			ApplicationContext ctx = (ApplicationContext) context.getScheduler().getContext().get("applicationContext");
			
			Object bean = ctx.getBean(targetObject);
			Method m = bean.getClass().getMethod(targetMethod);
			m.invoke(bean, new Object[] {});
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void setTargetObject(String targetObject) {
		this.targetObject = targetObject;
	}

	public void setTargetMethod(String targetMethod) {
		this.targetMethod = targetMethod;
	}
	
}

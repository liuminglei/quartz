package com.xbd.quartz;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

public class AutowiredSpringBeanJobFactory extends SpringBeanJobFactory {
	
	@Autowired
	private AutowireCapableBeanFactory autowireCapableBeanFactory;

	@Override
	protected Object createJobInstance(TriggerFiredBundle bundle)
			throws Exception {
		Object job = super.createJobInstance(bundle);
		autowireCapableBeanFactory.autowireBean(job);
		return job;
	}
}

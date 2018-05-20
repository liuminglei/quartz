package com.xbd.quartz;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

/**
 * <p>
 * 自动装载Bean到Spring，可在JobBean中直接注入定义的Bean
 * </p>
 *
 * @author 小不点
 */
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

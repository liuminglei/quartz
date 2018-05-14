package com.xbd.quartz.listener;

import org.quartz.JobListener;
import org.quartz.Matcher;
import org.quartz.TriggerKey;
import org.quartz.impl.matchers.EverythingMatcher;

public abstract class AbstractJobListener implements JobListener {
	
	public Matcher<TriggerKey> matcher() {
		return EverythingMatcher.allTriggers();
	}
	
}

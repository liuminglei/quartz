package com.xbd.quartz.listener;

import org.quartz.Matcher;
import org.quartz.TriggerKey;
import org.quartz.TriggerListener;
import org.quartz.impl.matchers.EverythingMatcher;

public abstract class AbstractTriggerListener implements TriggerListener {

	public Matcher<TriggerKey> matcher() {
		return EverythingMatcher.allTriggers();
	}
	
}

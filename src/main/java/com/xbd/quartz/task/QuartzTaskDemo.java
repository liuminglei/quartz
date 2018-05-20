package com.xbd.quartz.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>
 * 定时任务demo
 * </p>
 *
 * @author 小不点
 */
public class QuartzTaskDemo extends AbstractQuartzTask {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getGroup() {
        return super.getGroup();
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getMethod() {
        return super.getMethod();
    }

    @Override
    public void execute() throws Exception {
        log.info("我是测试定时任务！");
    }
}

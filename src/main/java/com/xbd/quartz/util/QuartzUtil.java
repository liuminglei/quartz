package com.xbd.quartz.util;

import com.xbd.quartz.task.QuartzTask;
import com.xbd.quartz.vo.QuartzTaskVO;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class QuartzUtil implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public List<QuartzTaskVO> tasks() {
        Map<String, QuartzTask> quartzTaskMap = applicationContext.getBeansOfType(QuartzTask.class);

        List<QuartzTaskVO> quartzTasks = new ArrayList<>();

        if (MapUtils.isEmpty(quartzTaskMap)) {
            return null;
        }

        for (String beanName : quartzTaskMap.keySet()) {
            QuartzTask quartzTask = quartzTaskMap.get(beanName);

            QuartzTaskVO quartzTaskVO = new QuartzTaskVO();
            quartzTaskVO.setName(quartzTask.getName());
            quartzTaskVO.setGroup(quartzTask.getGroup());
            quartzTaskVO.setDescription(quartzTask.getDescription());
            quartzTaskVO.setTargetClass(quartzTask.getClass().getName());
            quartzTaskVO.setTargetObject(beanName);

            quartzTasks.add(quartzTaskVO);
        }

        return quartzTasks;
    }

}

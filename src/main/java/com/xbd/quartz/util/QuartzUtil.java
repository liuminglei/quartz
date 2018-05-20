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

/**
 * <p>
 * 定时任务工具类
 * </p>
 *
 * @author 小不点
 */
public class QuartzUtil implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 将定义的定时任务SpringBean加载并转换为定时任务信息实体
     *
     * @return 定时任务信息列表
     */
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

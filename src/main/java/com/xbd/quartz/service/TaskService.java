package com.xbd.quartz.service;

import com.xbd.quartz.vo.QuartzTaskVO;
import org.quartz.SchedulerException;

public interface TaskService {
    void addTask(QuartzTaskVO quartzTaskVO) throws SchedulerException;

    void addTask(QuartzTaskVO... quartzTaskVOs) throws SchedulerException;

    void updateTask(QuartzTaskVO quartzTaskVO) throws SchedulerException;

    void updateTask(QuartzTaskVO... quartzTaskVOs) throws SchedulerException;

    void pauseTask(QuartzTaskVO quartzTaskVO) throws SchedulerException;

    void pauseTask(QuartzTaskVO... quartzTaskVOs) throws SchedulerException;

    void pauseTask() throws SchedulerException;

    void resumeTask(QuartzTaskVO quartzTaskVO) throws SchedulerException;

    void resumeTask(QuartzTaskVO... quartzTaskVOs) throws SchedulerException;

    void deleteTask(QuartzTaskVO quartzTaskVO) throws SchedulerException;

    void deleteTask(QuartzTaskVO... quartzTaskVOs) throws SchedulerException;

}

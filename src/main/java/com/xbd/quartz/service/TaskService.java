package com.xbd.quartz.service;

import com.xbd.quartz.vo.QuartzTaskVO;
import org.quartz.SchedulerException;

/**
 * <p>
 * 任务动态注册服务，提供新增任务、修改任务、暂停任务、继续任务、删除任务等动态注册服务。
 * </p>
 *
 * @see QuartzTaskVO
 *
 * @author 小不点
 */
public interface TaskService {

    /**
     * <p>
     * <b>动态添加任务</b><br>
     * <code>QuartzTaskVO</code>中包含定时任务的一些必要和扩展信息，但一些重要信息，如任务名称、cron表达式、
     * 任务目标类、任务目标类SpringBean对象、任务目标类执行方法等不允许为空。
     * </p>
     *
     * @see QuartzTaskVO
     *
     * @param quartzTaskVO 定时任务信息
     * @throws SchedulerException
     */
    void addTask(QuartzTaskVO quartzTaskVO) throws SchedulerException;

    /**
     * <p>
     * <b>批量动态添加任务</b><br>
     * <code>QuartzTaskVO</code>中包含定时任务的一些必要和扩展信息，但一些重要信息，如任务名称、cron表达式、
     * 任务目标类、任务目标类SpringBean对象、任务目标类执行方法等不允许为空。
     * </p>
     *
     * @see QuartzTaskVO
     *
     * @param quartzTaskVOs 定时任务信息集合
     * @throws SchedulerException
     */
    void addTask(QuartzTaskVO... quartzTaskVOs) throws SchedulerException;

    /**
     * <p>
     * <b>动态更新任务</b><br>
     * <code>QuartzTaskVO</code>中包含定时任务的一些必要和扩展信息，但一些重要信息，如任务名称、cron表达式、
     * 任务目标类、任务目标类SpringBean对象、任务目标类执行方法等不允许为空。
     * </p>
     *
     * @see QuartzTaskVO
     *
     * @param quartzTaskVO 定时任务信息
     * @throws SchedulerException
     */
    void updateTask(QuartzTaskVO quartzTaskVO) throws SchedulerException;

    /**
     * <p>
     * <b>批量动态更新任务</b><br>
     * <code>QuartzTaskVO</code>中包含定时任务的一些必要和扩展信息，但一些重要信息，如任务名称、cron表达式、
     * 任务目标类、任务目标类SpringBean对象、任务目标类执行方法等不允许为空。
     * </p>
     *
     * @see QuartzTaskVO
     *
     * @param quartzTaskVOs 定时任务信息集合
     * @throws SchedulerException
     */
    void updateTask(QuartzTaskVO... quartzTaskVOs) throws SchedulerException;

    /**
     * <p>
     * <b>暂停任务</b><br>
     * <code>QuartzTaskVO</code>中包含定时任务的一些必要和扩展信息，但一些重要信息，如任务名称、cron表达式、
     * 任务目标类、任务目标类SpringBean对象、任务目标类执行方法等不允许为空。
     * </p>
     *
     * @see QuartzTaskVO
     *
     * @param quartzTaskVO 定时任务信息
     * @throws SchedulerException
     */
    void pauseTask(QuartzTaskVO quartzTaskVO) throws SchedulerException;

    /**
     * <p>
     * <b>批量暂停任务</b><br>
     * <code>QuartzTaskVO</code>中包含定时任务的一些必要和扩展信息，但一些重要信息，如任务名称、cron表达式、
     * 任务目标类、任务目标类SpringBean对象、任务目标类执行方法等不允许为空。
     * </p>
     *
     * @see QuartzTaskVO
     *
     * @param quartzTaskVOs 定时任务信息集合
     * @throws SchedulerException
     */
    void pauseTask(QuartzTaskVO... quartzTaskVOs) throws SchedulerException;

    /**
     * <p>
     * 暂停所有任务
     * </p>
     *
     * @throws SchedulerException
     */
    void pauseTask() throws SchedulerException;

    /**
     * <p>
     * <b>继续任务</b><br>
     * <code>QuartzTaskVO</code>中包含定时任务的一些必要和扩展信息，但一些重要信息，如任务名称、cron表达式、
     * 任务目标类、任务目标类SpringBean对象、任务目标类执行方法等不允许为空。
     * </p>
     *
     * @see QuartzTaskVO
     *
     * @param quartzTaskVO 定时任务信息
     * @throws SchedulerException
     */
    void resumeTask(QuartzTaskVO quartzTaskVO) throws SchedulerException;

    /**
     * <p>
     * <b>批量继续任务</b><br>
     * <code>QuartzTaskVO</code>中包含定时任务的一些必要和扩展信息，但一些重要信息，如任务名称、cron表达式、
     * 任务目标类、任务目标类SpringBean对象、任务目标类执行方法等不允许为空。
     * </p>
     *
     * @see QuartzTaskVO
     *
     * @param quartzTaskVOs 定时任务信息集合
     * @throws SchedulerException
     */
    void resumeTask(QuartzTaskVO... quartzTaskVOs) throws SchedulerException;

    /**
     * <p>
     * <b>删除任务</b><br>
     * <code>QuartzTaskVO</code>中包含定时任务的一些必要和扩展信息，但一些重要信息，如任务名称、cron表达式、
     * 任务目标类、任务目标类SpringBean对象、任务目标类执行方法等不允许为空。
     * </p>
     *
     * @see QuartzTaskVO
     *
     * @param quartzTaskVO 定时任务信息
     * @throws SchedulerException
     */
    void deleteTask(QuartzTaskVO quartzTaskVO) throws SchedulerException;

    /**
     * <p>
     * <b>批量删除任务</b><br>
     * <code>QuartzTaskVO</code>中包含定时任务的一些必要和扩展信息，但一些重要信息，如任务名称、cron表达式、
     * 任务目标类、任务目标类SpringBean对象、任务目标类执行方法等不允许为空。
     * </p>
     *
     * @see QuartzTaskVO
     *
     * @param quartzTaskVOs 定时任务信息集合
     * @throws SchedulerException
     */
    void deleteTask(QuartzTaskVO... quartzTaskVOs) throws SchedulerException;

}

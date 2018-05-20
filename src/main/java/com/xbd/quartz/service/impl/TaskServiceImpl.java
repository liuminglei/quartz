package com.xbd.quartz.service.impl;

import com.xbd.quartz.DefaultQuartzJobBean;
import com.xbd.quartz.service.TaskService;
import com.xbd.quartz.vo.QuartzTaskVO;
import org.apache.commons.lang3.StringUtils;
import org.quartz.*;

/**
 * <p>
 * 任务动态注册服务，提供新增任务、修改任务、暂停任务、继续任务、删除任务等动态注册服务。
 * </p>
 *
 * @see QuartzTaskVO
 * @see Scheduler
 *
 * @author 小不点
 */
public class TaskServiceImpl implements TaskService {

    private Scheduler scheduler;

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
    public void addTask(QuartzTaskVO quartzTaskVO) throws SchedulerException {
        JobDetail jobDetail = JobBuilder
                .newJob(DefaultQuartzJobBean.class)
                .withIdentity(quartzTaskVO.getName(), quartzTaskVO.getGroup())
                .withDescription(quartzTaskVO.getDescription())
                .storeDurably()
                .build();

        CronScheduleBuilder cronScheduleBuilder = initCronScheduleBuilder(quartzTaskVO);

        TriggerBuilder<CronTrigger> triggerBuilder = TriggerBuilder.newTrigger()
                .withIdentity(TriggerKey.triggerKey(quartzTaskVO.getName(), quartzTaskVO.getGroup()))
                .withSchedule(cronScheduleBuilder)
                .usingJobData(DefaultQuartzJobBean.JOBDETAIL_KEY_TARGETOBJECT, quartzTaskVO.getTargetObject())
                .usingJobData(DefaultQuartzJobBean.JOBDETAIL_KEY_TARGETMETHOD, StringUtils.isBlank(quartzTaskVO.getTargetMethod()) ? DefaultQuartzJobBean.JOBDETAIL_VALUE_TARGETMETHOD : quartzTaskVO.getTargetMethod());

        if (quartzTaskVO.getStartAt() != null) {
            triggerBuilder.startAt(quartzTaskVO.getStartAt());
        }

        if (quartzTaskVO.isStartNow()) {
            triggerBuilder.startNow();
        }

        CronTrigger cronTrigger = triggerBuilder.build();

        scheduler.scheduleJob(jobDetail, cronTrigger);
    }

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
    public void addTask(QuartzTaskVO... quartzTaskVOs) throws SchedulerException {
        if (quartzTaskVOs != null) {
            for (QuartzTaskVO quartzTaskVO : quartzTaskVOs) {
                addTask(quartzTaskVO);
            }
        }
    }

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
    public void updateTask(QuartzTaskVO quartzTaskVO) throws SchedulerException {
        CronTrigger cronTrigger = getTrigger(quartzTaskVO.getName(), quartzTaskVO.getGroup());

        CronScheduleBuilder cronScheduleBuilder = initCronScheduleBuilder(quartzTaskVO);

        TriggerBuilder<CronTrigger> triggerBuilder = cronTrigger
                .getTriggerBuilder()
                .withIdentity(TriggerKey.triggerKey(quartzTaskVO.getName(), quartzTaskVO.getGroup()))
                .withSchedule(cronScheduleBuilder)
                .usingJobData(DefaultQuartzJobBean.JOBDETAIL_KEY_TARGETOBJECT, quartzTaskVO.getTargetObject())
                .usingJobData(DefaultQuartzJobBean.JOBDETAIL_KEY_TARGETMETHOD, StringUtils.isBlank(quartzTaskVO.getTargetMethod()) ? DefaultQuartzJobBean.JOBDETAIL_VALUE_TARGETMETHOD : quartzTaskVO.getTargetMethod());

        if (quartzTaskVO.getStartAt() != null) {
            triggerBuilder.startAt(quartzTaskVO.getStartAt());
        }

        if (quartzTaskVO.isStartNow()) {
            triggerBuilder.startNow();
        }

        cronTrigger = triggerBuilder.build();

        scheduler.rescheduleJob(TriggerKey.triggerKey(quartzTaskVO.getName(), quartzTaskVO.getGroup()),
                cronTrigger);
    }

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
    public void updateTask(QuartzTaskVO... quartzTaskVOs) throws SchedulerException {
        if (quartzTaskVOs != null) {
            for (QuartzTaskVO quartzTaskVO : quartzTaskVOs) {
                updateTask(quartzTaskVO);
            }
        }
    }

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
    public void pauseTask(QuartzTaskVO quartzTaskVO) throws SchedulerException {
        JobKey jobKey = new JobKey(quartzTaskVO.getName(), quartzTaskVO.getGroup());
        scheduler.pauseJob(jobKey);
    }

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
    public void pauseTask(QuartzTaskVO... quartzTaskVOs) throws SchedulerException {
        if (quartzTaskVOs != null) {
            for (QuartzTaskVO quartzTaskVO : quartzTaskVOs) {
                pauseTask(quartzTaskVO);
            }
        }
    }

    /**
     * <p>
     * 暂停所有任务
     * </p>
     *
     * @throws SchedulerException
     */
    public void pauseTask() throws SchedulerException {
        scheduler.pauseAll();
    }

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
    public void resumeTask(QuartzTaskVO quartzTaskVO) throws SchedulerException {
        JobKey jobKey = new JobKey(quartzTaskVO.getName(), quartzTaskVO.getGroup());
        scheduler.resumeJob(jobKey);
    }

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
    public void resumeTask(QuartzTaskVO... quartzTaskVOs) throws SchedulerException {
        if (quartzTaskVOs != null) {
            for (QuartzTaskVO quartzTaskVO : quartzTaskVOs) {
                resumeTask(quartzTaskVO);
            }
        }
    }

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
    public void deleteTask(QuartzTaskVO quartzTaskVO) throws SchedulerException {
        JobKey jobKey = new JobKey(quartzTaskVO.getName(), quartzTaskVO.getGroup());
        scheduler.deleteJob(jobKey);
    }

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
    public void deleteTask(QuartzTaskVO... quartzTaskVOs) throws SchedulerException {
        if (quartzTaskVOs != null) {
            for (QuartzTaskVO quartzTaskVO : quartzTaskVOs) {
                deleteTask(quartzTaskVO);
            }
        }
    }

    /**
     * <p>
     * 根据定时任务信息组织CronScheduleBuilder
     * </p>
     *
     * @see CronScheduleBuilder
     * @see QuartzTaskVO
     *
     * @param quartzTaskVO 定时任务信息
     * @return
     */
    private CronScheduleBuilder initCronScheduleBuilder(QuartzTaskVO quartzTaskVO) {
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(quartzTaskVO.getCronExpression());

        // 以当前时间为触发频率立刻触发一次执行，然后按照Cron频率依次执行
        if (quartzTaskVO.getMisfireInstruction() == CronTrigger.MISFIRE_INSTRUCTION_FIRE_ONCE_NOW) {
            cronScheduleBuilder.withMisfireHandlingInstructionFireAndProceed();
        } else if (quartzTaskVO.getMisfireInstruction() == CronTrigger.MISFIRE_INSTRUCTION_IGNORE_MISFIRE_POLICY) {
            // 以错过的第一个频率时间立刻开始执行，重做错过的所有频率周期后，当下一次触发频率发生时间大于当前时间后，再按照正常的Cron频率依次执行
            cronScheduleBuilder.withMisfireHandlingInstructionIgnoreMisfires();
        } else if (quartzTaskVO.getMisfireInstruction() == CronTrigger.MISFIRE_INSTRUCTION_DO_NOTHING) {
            // 不触发立即执行，等待下次Cron触发频率到达时刻开始按照Cron频率依次执行
            cronScheduleBuilder.withMisfireHandlingInstructionDoNothing();
        }

        return cronScheduleBuilder;
    }

    /**
     * <p>
     * 根据任务名称、任务分组获取定时任务调度器中的该任务
     * </p>
     *
     * @param name
     * @param group
     * @throws SchedulerException
     */
    private CronTrigger getTrigger(String name, String group) throws SchedulerException {
        CronTrigger cronTrigger = (CronTrigger) scheduler.getTrigger(new TriggerKey(name, group));

        return cronTrigger;
    }

    public Scheduler getScheduler() {
        return scheduler;
    }

    public void setScheduler(Scheduler scheduler) {
        this.scheduler = scheduler;
    }
}

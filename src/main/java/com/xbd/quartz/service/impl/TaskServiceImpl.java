package com.xbd.quartz.service.impl;

import com.xbd.quartz.DefaultQuartzJobBean;
import com.xbd.quartz.service.TaskService;
import com.xbd.quartz.vo.QuartzTaskVO;
import org.apache.commons.lang3.StringUtils;
import org.quartz.*;

public class TaskServiceImpl implements TaskService {

    private Scheduler scheduler;

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

    public void addTask(QuartzTaskVO... quartzTaskVOs) throws SchedulerException {
        if (quartzTaskVOs != null) {
            for (QuartzTaskVO quartzTaskVO : quartzTaskVOs) {
                addTask(quartzTaskVO);
            }
        }
    }

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

    public void updateTask(QuartzTaskVO... quartzTaskVOs) throws SchedulerException {
        if (quartzTaskVOs != null) {
            for (QuartzTaskVO quartzTaskVO : quartzTaskVOs) {
                updateTask(quartzTaskVO);
            }
        }
    }

    public void pauseTask(QuartzTaskVO quartzTaskVO) throws SchedulerException {
        JobKey jobKey = new JobKey(quartzTaskVO.getName(), quartzTaskVO.getGroup());
        scheduler.pauseJob(jobKey);
    }

    public void pauseTask(QuartzTaskVO... quartzTaskVOs) throws SchedulerException {
        if (quartzTaskVOs != null) {
            for (QuartzTaskVO quartzTaskVO : quartzTaskVOs) {
                pauseTask(quartzTaskVO);
            }
        }
    }

    public void pauseTask() throws SchedulerException {
        scheduler.pauseAll();
    }

    public void resumeTask(QuartzTaskVO quartzTaskVO) throws SchedulerException {
        JobKey jobKey = new JobKey(quartzTaskVO.getName(), quartzTaskVO.getGroup());
        scheduler.resumeJob(jobKey);
    }

    public void resumeTask(QuartzTaskVO... quartzTaskVOs) throws SchedulerException {
        if (quartzTaskVOs != null) {
            for (QuartzTaskVO quartzTaskVO : quartzTaskVOs) {
                resumeTask(quartzTaskVO);
            }
        }
    }

    public void deleteTask(QuartzTaskVO quartzTaskVO) throws SchedulerException {
        JobKey jobKey = new JobKey(quartzTaskVO.getName(), quartzTaskVO.getGroup());
        scheduler.deleteJob(jobKey);
    }

    public void deleteTask(QuartzTaskVO... quartzTaskVOs) throws SchedulerException {
        if (quartzTaskVOs != null) {
            for (QuartzTaskVO quartzTaskVO : quartzTaskVOs) {
                deleteTask(quartzTaskVO);
            }
        }
    }

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

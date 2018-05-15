package com.xbd.quartz.vo;

import com.xbd.quartz.task.AbstractQuartzTask;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;

public class QuartzTaskVO implements Serializable {

    private static final long serialVersionUID = -2116518270025568628L;

    private String name;

    private String group;

    private String description;

    private Date startAt;

    private boolean startNow;

    private String targetClass;

    private String targetObject;

    private String targetMethod;

    private String cronExpression;

    /**
     * @see org.quartz.CronTrigger#MISFIRE_INSTRUCTION_SMART_POLICY
     * @see org.quartz.CronTrigger#MISFIRE_INSTRUCTION_DO_NOTHING
     * @see org.quartz.CronTrigger#MISFIRE_INSTRUCTION_FIRE_ONCE_NOW
     * @see org.quartz.CronTrigger#MISFIRE_INSTRUCTION_IGNORE_MISFIRE_POLICY
     */
    private int misfireInstruction;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return StringUtils.isBlank(group) ? AbstractQuartzTask.TASK_GROUP_DEFAULT : group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    public boolean isStartNow() {
        return startNow;
    }

    public void setStartNow(boolean startNow) {
        this.startNow = startNow;
    }

    public String getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(String targetClass) {
        this.targetClass = targetClass;
    }

    public String getTargetObject() {
        return targetObject;
    }

    public void setTargetObject(String targetObject) {
        this.targetObject = targetObject;
    }

    public String getTargetMethod() {
        return targetMethod;
    }

    public void setTargetMethod(String targetMethod) {
        this.targetMethod = targetMethod;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public int getMisfireInstruction() {
        return misfireInstruction;
    }

    public void setMisfireInstruction(int misfireInstruction) {
        this.misfireInstruction = misfireInstruction;
    }
}

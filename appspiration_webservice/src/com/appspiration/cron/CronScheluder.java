package com.appspiration.cron;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class CronScheluder {

    public CronScheluder() throws Exception {

        JobDetail job = JobBuilder.newJob(CronJob.class).withIdentity("dummyJobName", "group1").build();
         
        Trigger trigger = TriggerBuilder.newTrigger().withIdentity("dummyTriggerName", "group1")
        		.withSchedule(CronScheduleBuilder.cronSchedule("1 * * * * ?")).build();
        
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
    	scheduler.start();
    	scheduler.scheduleJob(job, trigger);
    }
}
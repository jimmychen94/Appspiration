package com.appspiration.cron;

import java.sql.SQLException;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.appspiration.service.DBConnection;

public class CronJob implements Job {

	public void execute(JobExecutionContext context) throws JobExecutionException {
		try {
			DBConnection.updateThreadVotes();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
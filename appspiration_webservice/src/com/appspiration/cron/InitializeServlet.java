package com.appspiration.cron;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class InitializeServlet extends HttpServlet {

 public void init() throws ServletException {

    try {
        System.out.println("Initializing cron scheduler PlugIn");
        CronScheluder objPlugin = new CronScheluder();
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }

  }

}
package org.gec.smart.listener;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.gec.smart.task.RefreshTask;

public class GetRefreshDataListener implements ServletContextListener {

	@Override
    public void contextInitialized(ServletContextEvent arg0)  {
        System.out.println("容器启动了...");
        //启动定时任务(每个30秒钟自动执行定时任务一次)
        new Timer().schedule(new RefreshTask(), 0, 1000 * 30);
   }

   @Override
   public void contextDestroyed(ServletContextEvent arg0)  {
	    System.out.println("容器关闭了...");
   }
	
}

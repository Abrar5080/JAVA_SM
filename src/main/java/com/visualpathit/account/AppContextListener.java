package com.visualpathit.account;

import java.lang.reflect.Method;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;

@WebListener
public class AppContextListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // Initialization code if needed
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            // Attempt to shut down the abandoned connection cleanup thread using reflection
            Method shutdownMethod = AbandonedConnectionCleanupThread.class.getDeclaredMethod("shutdown", boolean.class);
            shutdownMethod.setAccessible(true);
            shutdownMethod.invoke(null, true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

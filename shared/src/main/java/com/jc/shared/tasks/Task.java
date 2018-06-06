package com.jc.shared.tasks;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by jc on 6/5/18.
 */
public class Task {

    public static void executeWithDelay(Runnable task, int delay, TimeUnit unit) {
        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.schedule(task, delay, unit);
        scheduler.shutdown();
    }

    public static void execute(Runnable task) {
        executeWithDelay(task, 0, TimeUnit.SECONDS);
    }

}

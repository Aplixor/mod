package com.aplixor.mod;

import com.aplixor.mod.core.Scheduler;
import com.aplixor.mod.core.SchedulerImpl;

public class mod {
    private static mod instance = null;
    Scheduler scheduler = new SchedulerImpl();

    private mod() {}

    public Scheduler getScheduler() {
        return this.scheduler;
    }

    public static mod getInstance() {
        if (instance == null) instance = new mod();
        return instance;
    }
}

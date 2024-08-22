package com.aplixor.mod;

import com.aplixor.mod.core.Scheduler;
import com.aplixor.mod.core.SchedulerImpl;

public class Mod {
    private static Mod instance = null;
    Scheduler scheduler = new SchedulerImpl();

    private Mod() {}

    public Scheduler getScheduler() {
        return this.scheduler;
    }

    public static Mod getInstance() {
        if (instance == null) instance = new Mod();
        return instance;
    }
}

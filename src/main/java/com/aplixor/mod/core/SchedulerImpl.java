package com.aplixor.mod.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class SchedulerImpl implements Scheduler {
    private Long clock = 0L;
    private final ArrayList<String> tasks = new ArrayList<>();
    private final HashMap<String, Runnable> storedEntries = new HashMap<>();
    private final HashMap<String, Long> taskLives = new HashMap<>();
    private final HashMap<String, Long> timetable = new HashMap<>();
    private final HashMap<String, Integer> intervals = new HashMap<>();

    public SchedulerImpl() {}

    @Override
    public void tick() {
        this.clock++;

        List<String> due = tasks.stream()
                .filter((s -> timetable.get(s) <= this.clock))
                .toList();

        Iterator<String> iter = due.iterator();
        while (iter.hasNext()) {
            String key = iter.next();

            storedEntries.get(key).run();
            taskLives.put(key, taskLives.get(key)-1);
            timetable.put(key, clock + intervals.get(key));
            if (taskLives.get(key) <= 0) this.remove(key);
        }
    }

    @Override
    public boolean addTask(String name, Runnable func, Integer ticksAfter) {
        return this.addTask(name, func, ticksAfter, 1, false);
    }

    @Override
    public boolean addTask(String name, Runnable func, Integer ticksAfter, Integer lives) {
        return this.addTask(name, func, ticksAfter, lives, false);
    }

    @Override
    public boolean addTask(String name, Runnable func, Integer ticksAfter, Integer lives, Boolean infinite) {
        Long value = Long.valueOf(lives);
        if (infinite) value = Long.MAX_VALUE;

        if (storedEntries.containsKey(name)) return false;

        tasks.add(name);
        storedEntries.put(name, func);
        taskLives.put(name, value);
        timetable.put(name, clock + ticksAfter);
        this.intervals.put(name, ticksAfter);
        return true;
    }

    @Override
    public void remove(String name) {
        this.tasks.removeIf((s -> s.equals(name)));
        this.storedEntries.remove(name);
        this.taskLives.remove(name);
        this.timetable.remove(name);
    }
}

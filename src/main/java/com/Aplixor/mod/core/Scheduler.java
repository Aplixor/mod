package com.Aplixor.mod.core;

public interface Scheduler {

    void tick();

    boolean addTask(String name, Runnable func, Integer ticksAfter);

    boolean addTask(String name, Runnable func, Integer ticksAfter, Integer lives);

    boolean addTask(String name, Runnable func, Integer ticksAfter, Integer lives, Boolean infinite);

    void remove(String name);
}

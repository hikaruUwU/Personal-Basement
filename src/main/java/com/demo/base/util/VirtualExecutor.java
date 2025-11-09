package com.demo.base.util;

import lombok.experimental.UtilityClass;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@UtilityClass
public class VirtualExecutor {
    private final Executor EXECUTOR = Executors.newVirtualThreadPerTaskExecutor();

    public void execute(Runnable command) {
        EXECUTOR.execute(command);
    }
}

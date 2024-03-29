package com.gg.anrwatch.anrwatch;

import android.os.HandlerThread;
import android.os.Looper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @description:
 * @author: GG
 * @createDate: 2023 2.20 0020 15:49
 * @updateUser:
 * @updateDate: 2023 2.20 0020 15:49
 */
public class ThreadManager {
    private static Looper ANR_WATCHDOG_LOOPER = null;
    private static volatile ExecutorService mExecutor;

    static {
        mExecutor = Executors.newCachedThreadPool();
    }

    public static Looper getAnrWatchDogLooper(){
        if(ANR_WATCHDOG_LOOPER == null){
            synchronized (ThreadManager.class){
                if(ANR_WATCHDOG_LOOPER == null){
                    HandlerThread handlerThread = new HandlerThread("ANR_WATCHDOG");
                    handlerThread.start();
                    ANR_WATCHDOG_LOOPER = handlerThread.getLooper();
                }
            }
        }
        return ANR_WATCHDOG_LOOPER;
    }

    public static void run(Runnable runnable) {
        mExecutor.execute(runnable);
    }
}


package com.dapan.rxjava;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by per4j
 * on 2020/5/10
 */
public abstract class Schedulers {

    private static Schedulers IO;

    static {
        IO = new IOSchedulers();
    }

    public static Schedulers io() {
        return IO;
    }

    public abstract void scheduleDirect(Runnable runnable);

    private static class IOSchedulers extends Schedulers {

        private ExecutorService service;

        IOSchedulers() {
            service = Executors.newScheduledThreadPool(1, new ThreadFactory() {
                @Override
                public Thread newThread(Runnable r) {
                    return new Thread(r, "IOSchedulers");
                }
            });
        }

        @Override
        public void scheduleDirect(Runnable runnable) {
            service.execute(runnable);
        }
    }
}

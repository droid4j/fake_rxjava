package com.dapan.rxjava;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * Created by per4j
 * on 2020/5/10
 */
public class AndroidSchedulers {

    private static Schedulers DEFAULT;

    static {
        DEFAULT = new HandlerScheduler(new Handler(Looper.getMainLooper()));
    }

    public static Schedulers mainThread() {
        return DEFAULT;
    }

    private static class HandlerScheduler extends Schedulers {

        private Handler handler;

        HandlerScheduler(Handler handler) {
            this.handler = handler;
        }

        @Override
        public void scheduleDirect(Runnable runnable) {
            Message message = Message.obtain(handler, runnable);
            handler.sendMessage(message);
        }
    }
}

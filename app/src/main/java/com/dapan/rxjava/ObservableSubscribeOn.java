package com.dapan.rxjava;

/**
 * Created by per4j
 * on 2020/5/10
 */
final class ObservableSubscribeOn<T> extends Observable<T> {

    private final Observable<T> source;
    private final Schedulers schedulers;

    ObservableSubscribeOn(Observable<T> source, Schedulers schedulers) {
        this.source = source;
        this.schedulers = schedulers;
    }

    @Override
    void subscribeActual(Observer<? super T> observer) {
        final SubscribeOnObserver<T> parent = new SubscribeOnObserver<T>(observer);
        // observer.onSubscribe(); // TODO: 这里与RxJava2不同，所以这里的实现，onSubscribe执行在子线程
        schedulers.scheduleDirect(new SubscribeTask(parent)); // 切换线程
    }

    private static class SubscribeOnObserver<T> implements Observer<T> {

        private Observer<? super T> downStream;

        SubscribeOnObserver(Observer<? super T> observer) {
            this.downStream = observer;
        }

        @Override
        public void onSubscribe() {
            downStream.onSubscribe();
        }

        @Override
        public void onError(Throwable e) {
            downStream.onError(e);
        }

        @Override
        public void onComplete() {
            downStream.onComplete();
        }

        @Override
        public void onNext(T next) {
            downStream.onNext(next);
        }
    }

    private class SubscribeTask implements Runnable {
        private SubscribeOnObserver<T> parent;
        SubscribeTask(SubscribeOnObserver<T> parent) {
            this.parent = parent;
        }

        @Override
        public void run() {
            source.subscribe(parent);
        }
    }
}

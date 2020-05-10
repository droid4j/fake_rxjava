package com.dapan.rxjava;

/**
 * Created by per4j
 * on 2020/5/10
 */
final class ObservableObserveOn<T> extends Observable<T> {

    private final Observable<T> source;
    private final Schedulers schedulers;

    ObservableObserveOn(Observable<T> source, Schedulers schedulers) {
        this.source = source;
        this.schedulers = schedulers;
    }

    @Override
    void subscribeActual(Observer<? super T> observer) {
        source.subscribe(new ObserveOnObserver<T>(observer, schedulers));
    }

    private static class ObserveOnObserver<T> implements Observer<T>, Runnable {

        private final Observer<? super T> downStream;
        private final Schedulers schedulers;
        private T t;

        public ObserveOnObserver(Observer<? super T> observer, Schedulers schedulers) {
            this.downStream = observer;
            this.schedulers = schedulers;
        }

        @Override
        public void onSubscribe() {
            downStream.onSubscribe();
        }

        @Override
        public void onNext(T next) {
            this.t = next;
            schedulers.scheduleDirect(this); // 这里，只实现了将 onNext 切到主线程！！
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
        public void run() {
            downStream.onNext(t);
        }
    }
}

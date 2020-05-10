package com.dapan.rxjava;

/**
 * Created by per4j
 * on 2020/5/10
 */
class ObservableJust<T> extends Observable<T> {

    private T item;
    ObservableJust(T t) {
        this.item = t;
    }

    @Override
    void subscribeActual(Observer<? super T> observer) {
        ScalarDisposable<T> sd = new ScalarDisposable<>(observer, item);
        observer.onSubscribe();
        sd.run();
    }

    private static class ScalarDisposable<T> implements Runnable {
        Observer<? super T> observer;
        T item;
        ScalarDisposable(Observer<? super T> observer, T item) {
            this.observer = observer;
            this.item = item;
        }

        @Override
        public void run() {
            try {
                observer.onNext(item);
                observer.onComplete();
            } catch (Exception e) {
                observer.onError(e);
            }
        }
    }
}

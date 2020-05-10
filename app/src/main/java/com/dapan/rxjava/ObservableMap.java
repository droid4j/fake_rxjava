package com.dapan.rxjava;

/**
 * Created by per4j
 * on 2020/5/10
 */
class ObservableMap<T, R> extends Observable<R> {

    private final Observable<T> source;
    private final Function<T, R> function;
    ObservableMap(Observable<T> source, Function<T, R> function) {
        this.source = source;
        this.function = function;
    }

    @Override
    void subscribeActual(Observer<? super R> observer) {
        source.subscribe(new MapObserver<T, R>(observer, function));
    }

    private static class MapObserver<T, R> implements Observer<T> {

        Observer<? super R> observer;
        Function<T, R> function;
        MapObserver(Observer<? super R> observer, Function<T, R> function) {
            this.observer = observer;
            this.function = function;
        }

        @Override
        public void onSubscribe() {
            observer.onSubscribe();
        }

        @Override
        public void onError(Throwable e) {
            observer.onError(e);
        }

        @Override
        public void onComplete() {
            observer.onComplete();
        }

        @Override
        public void onNext(T next) {
            R r = function.apply(next);
            observer.onNext(r);
        }
    }
}

package com.dapan.rxjava;

/**
 * Created by per4j
 * on 2020/5/10
 */
public abstract class Observable<T> implements ObservableSource<T> {

    public static <T> Observable<T> just(T t) {
        return onAssembly(new ObservableJust<>(t));
    }

    public <R> Observable<R> map(Function<T, R> mapper) {
        return onAssembly(new ObservableMap<T, R>(this, mapper));
    }

    public Observable<T> subscribeOn(Schedulers schedulers) {
        return onAssembly(new ObservableSubscribeOn<T>(this, schedulers));
    }

    public Observable<T> observeOn(Schedulers schedulers) {
        return onAssembly(new ObservableObserveOn<T>(this, schedulers));
    }

    private static <T> Observable<T> onAssembly(Observable<T> observable) {
        return observable;
    }

    @Override
    public void subscribe(Observer<? super T> observer) {
        subscribeActual(observer);
    }

    abstract void subscribeActual(Observer<? super T> observer);
}

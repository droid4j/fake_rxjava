package com.dapan.rxjava;

/**
 * Created by per4j
 * on 2020/5/10
 */
public interface Observer<T> {

    void onSubscribe();
    void onNext(T next);
    void onError(Throwable e);
    void onComplete();
}

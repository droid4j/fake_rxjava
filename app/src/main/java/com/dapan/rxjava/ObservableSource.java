package com.dapan.rxjava;

/**
 * Created by per4j
 * on 2020/5/10
 */
public interface ObservableSource<T> {

    void subscribe(Observer<? super T> observer);
}

package com.dapan.rxjava;

/**
 * Created by per4j
 * on 2020/5/10
 */
public interface Function<T, R> {
    R apply(T t);
}

package com.dapan.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.dapan.rxjava.Function;
import com.dapan.rxjava.Observable;
import com.dapan.rxjava.Observer;
import com.dapan.rxjava.Schedulers;

/**
 * Created by per4j
 * on 2020/5/10
 */
public class FakeRxJavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fake_rxjava);


        Observable.just("1")
                .map(new Function<String, Integer>() {
                    @Override
                    public Integer apply(String s) {
                        Log.e("FakeRxJavaActivity", "apply parseInt " + Thread.currentThread().getName());
                        return Integer.parseInt(s);
                    }
                })
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) {
                        Log.e("FakeRxJavaActivity", "apply toString " + Thread.currentThread().getName());
                        return integer.toString();
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe() {
                        Log.e("FakeRxJavaActivity", "onSubscribe " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onNext(String next) {
                        Log.e("FakeRxJavaActivity", "onNext: " + next + ", " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("FakeRxJavaActivity", "onError " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onComplete() {
                        Log.e("FakeRxJavaActivity", "onComplete " + Thread.currentThread().getName());
                    }
                });

    }
}

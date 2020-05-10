package com.dapan.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observable.just("1")
                .map(new Function<String, Integer>() {
                    @Override
                    public Integer apply(String s) {
                        Log.e(TAG, "apply parseInt " + Thread.currentThread().getName());
                        return Integer.parseInt(s);
                    }
                })
                .map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) {
                        Log.e(TAG, "apply toString " + Thread.currentThread().getName());
                        return integer.toString();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe: " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onNext(String s) {
                        Log.e(TAG, "onNext: " + s + ", " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete: " + Thread.currentThread().getName());
                    }
                });
    }

    public void jumpTo(View view) {

        Intent intent = new Intent(this, FakeRxJavaActivity.class);
        startActivity(intent);
    }
}

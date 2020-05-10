package com.dapan.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.dapan.rxjava.Observable;
import com.dapan.rxjava.Observer;

/**
 * Created by per4j
 * on 2020/5/10
 */
public class FakeRxJavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_fake_rxjava);


        Observable.just("item")
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe() {
                        Log.e("FakeRxJavaActivity", "onSubscribe");
                    }

                    @Override
                    public void onNext(String next) {
                        Log.e("FakeRxJavaActivity", "onNext: " + next);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("FakeRxJavaActivity", "onError");
                    }

                    @Override
                    public void onComplete() {
                        Log.e("FakeRxJavaActivity", "onComplete");
                    }
                });

    }
}

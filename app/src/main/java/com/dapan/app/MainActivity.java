package com.dapan.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Observable.just("item")
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void jumpTo(View view) {

        Intent intent = new Intent(this, FakeRxJavaActivity.class);
        startActivity(intent);
    }
}

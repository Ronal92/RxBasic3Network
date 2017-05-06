package com.jinwoo.android.rxbasic3network;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.reactivestreams.Subscriber;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    public final static String TAG = "PINGPONG";
    TextView textView4, textView1, textView2, textView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = (TextView)findViewById(R.id.textView1);
        textView2 = (TextView)findViewById(R.id.textView2);
        textView3 = (TextView)findViewById(R.id.textView3);
        textView4= (TextView)findViewById(R.id.textView4);


        // 실제 Task를 처리하는 객체(발행자)
        Observable<String> naverObservable =
                Observable.create(emitter -> {
                        emitter.onNext(Remote.getUrlByGet("google.com"));
                });

        Observable<String> cnetObservable =
                Observable.create(emitter -> {
                        emitter.onNext(Remote.getUrlByGet("www.cnet.co.kr"));
                });

        cnetObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                     (result) -> textView2.setText(result)
                );

        naverObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    (result) -> textView4.setText(result)
                );

    }
}

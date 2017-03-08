package com.example.rxjava.operationalCharacter.CreateOperation;

import com.example.rxjava.operationalCharacter.BaseOperational;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by zhaoli on 2017/2/18.
 */
public class Create extends BaseOperational {
    @Override
    protected void create() {
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                String[] data = new String[]{"a1", "a2", "a3"};
                for (String s : data) {
                    subscriber.onNext(s);
                }
                subscriber.onCompleted();
            }
        });

        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                String[] data = new String[]{"a1", "a2", ""};
                for (String s : data) {
                    if (s.isEmpty()) {
                        subscriber.onError(new Throwable("字符串为空"));
                    }
                    subscriber.onNext(s);
                }
                subscriber.onCompleted();
            }
        });
    }
}

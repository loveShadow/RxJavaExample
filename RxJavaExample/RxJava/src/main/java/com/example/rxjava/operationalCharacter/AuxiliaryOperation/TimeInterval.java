package com.example.rxjava.operationalCharacter.AuxiliaryOperation;

import com.example.rxjava.operationalCharacter.BaseOperational;

import java.util.Random;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by zhaoli on 2017/3/2.
 * TimeInterval 操作符
 * 将原始Observable发射的信号进行二次处理为新的Observable，新的Observable发出值的同时，会发出前一个和这个的时间间隔
 */
public class TimeInterval extends BaseOperational {
    @Override
    protected void create() {
        observableList.add(Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        if (subscriber.isUnsubscribed()) return;
                        Random random = new Random(System.currentTimeMillis());
                        for (int i = 0; i < 10; i ++) {
                            subscriber.onNext(String.valueOf(i));
                            try {
                                Thread.sleep(random.nextInt(1000));
                            } catch (Exception e) {
                                subscriber.onError(e);
                            }
                        }
                        subscriber.onCompleted();
                    }
                }
        ).timeInterval());
    }
}

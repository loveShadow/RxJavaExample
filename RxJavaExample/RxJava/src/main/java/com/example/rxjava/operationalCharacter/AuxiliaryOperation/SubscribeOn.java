package com.example.rxjava.operationalCharacter.AuxiliaryOperation;

import com.example.rxjava.log.LogUtils;
import com.example.rxjava.operationalCharacter.BaseOperational;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * Created by zhaoli on 2017/3/1.
 */
public class SubscribeOn extends BaseOperational {
    @Override
    protected void create() {
        observableList.add(Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (subscriber.isUnsubscribed()) return;
                int i = 10;
                while (i > 0) {
                    subscriber.onNext("[消息] " + i);
                    LogUtils.d(TAG, "发送消息 " + i);
                    i --;
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        subscriber.onError(e);
                    }
                }
                subscriber.onCompleted();
            }
        })
        /**
         * 改变订阅的线程，即call调用的线程
         */
        .subscribeOn(Schedulers.newThread())
        /**
         * 改变发送的线程，即onNext调用的线程
         */
        .observeOn(Schedulers.newThread())
        .doOnSubscribe(new Action0() {
            @Override
            public void call() {
                LogUtils.d(TAG, "已订阅");
            }
        }));
    }
}

package com.example.rxjava.operationalCharacter.FilterOperation;

import com.example.rxjava.operationalCharacter.BaseOperational;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by zhaoli on 2017/2/19.
 *
 * Deboundce 原意：“去抖动、防抖动” --- 过滤掉发射频率过快的数据
 *
 * Debounce 操作符示例
 * 仅在过了一段指定的时间还没发射数据时才发射"一个数据" 注意：是一个
 * Debounce操作符会过滤掉发射速率过快的数据项
 * 适用的场景可以是在线搜索功能，间隔指定时间发送当前输入框的搜索内容
 */
public class Debounce extends BaseOperational {
    @Override
    protected void create() {
        /**
         * @param timeout 两个信号间隔小于timeout时，信号会被忽略
         * Observable<T> debounce(long timeout, TimeUnit unit)
         *
         */
        observableList.add(Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (subscriber.isUnsubscribed()) return;
                int count = 0;
                Random random = new Random(System.currentTimeMillis());
                while (count < 100) {
                    subscriber.onNext("消息[" + count + "]");
                    long sleepTime = random.nextInt(500);
                    try {
                        Thread.sleep(sleepTime);
                    } catch (Exception e){
                        subscriber.onError(e);
                    }
                    count ++;
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.newThread())
                .debounce(250, TimeUnit.MILLISECONDS));
    }
}

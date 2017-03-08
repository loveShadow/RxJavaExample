package com.example.rxjava.operationalCharacter.FilterOperation;

import com.example.rxjava.operationalCharacter.BaseOperational;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by zhaoli on 2017/2/20.
 * Sample 操作符
 * 定期发射Observable最近发射的数据项【如果期间没有数据项，不会发射最近的数据】
 * ----0----1---2----3-4------------5------
 * -----a-----a-----a-----a-----a-----a----
 * -----0-----1-----2-----4-----------5----
 */
public class Sample extends BaseOperational {
    @Override
    protected void create() {
        /**
         * @param period 采取的频率
         * Observable<T> sample(long period, TimeUnit unit)
         */
        observableList.add(Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (subscriber.isUnsubscribed()) return;
                int count = 0;
                long[] sleepTimes = new long[]{400, 400, 300, 400, 100, 1200};
                while (count < 6) {
                    try {
                        Thread.sleep(sleepTimes[count]);
                    } catch (Exception e){
                        subscriber.onError(e);
                    }
                    subscriber.onNext("消息[" + count + "]");
                    count ++;
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.newThread())
                .sample(500, TimeUnit.MILLISECONDS));

        /**
         * @param sampler 用于发射采样率信号的Observable
         * <U> Observable<T> sample(Observable<U> sampler)
         */
        observableList.add(Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (subscriber.isUnsubscribed()) return;
                int count = 0;
                Random random = new Random(System.currentTimeMillis());
                while (count < 20) {
                    subscriber.onNext("消息2[" + count + "]");
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
                .sample(Observable.interval(300, TimeUnit.MILLISECONDS)));
    }
}

package com.example.rxjava.operationalCharacter.ConditionsAndBooleanOperation;

import com.example.rxjava.log.LogUtils;
import com.example.rxjava.operationalCharacter.BaseOperational;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by zhaoli on 2017/3/4.
 * Amb 操作符
 * 两个或多个Observable只处理发出最早的Observable
 */
public class Amb extends BaseOperational {
    @Override
    protected void create() {
        /**
         * 两个或多个Observable发出的信号，最终只会发出最早发出的Observable
         * <T> Observable<T> amb(Observable<? extends T> o1, Observable<? extends T> o2)
         * <T> Observable<T> amb(Iterable<? extends Observable<? extends T>> sources)
         *
         * 示例：Observable1和Observable2处理发出最早的Observable
         * 示例结果：
         *      D/LogUtils[Amb]: [延迟时间] 296
         *      D/LogUtils[Amb]: 5
         *      D/LogUtils[Amb]: 完成了
         * [第一个Observable发出的时间最早]
         */
        observableList.add(Observable.amb(
                Observable.create(new Observable.OnSubscribe<Long>() {
                    @Override
                    public void call(Subscriber<? super Long> subscriber) {
                        try {
                            int time = new Random(System.currentTimeMillis()).nextInt(1000);
                            LogUtils.d(TAG, "[延迟时间] " + time);
                            Thread.sleep(time);
                            subscriber.onNext(5l);
                        } catch (Exception e) {
                            subscriber.onError(e);
                        }
                        subscriber.onCompleted();
                    }
                }).subscribeOn(Schedulers.newThread()),
                Observable.timer(500, TimeUnit.MILLISECONDS)
        ));
    }
}

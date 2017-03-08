package com.example.rxjava.operationalCharacter.AuxiliaryOperation;

import com.example.rxjava.log.LogUtils;
import com.example.rxjava.operationalCharacter.BaseOperational;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zhaoli on 2017/3/2.
 */
public class Timeout extends BaseOperational {
    @Override
    protected void create() {
        /**
         * @param timeout 超时时间
         * Observable<T> timeout(long timeout, TimeUnit timeUnit)
         * Observable<T> timeout(long timeout, TimeUnit timeUnit, Scheduler scheduler)
         */
        observableList.add(Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        if (subscriber.isUnsubscribed()) return;
                        Random random = new Random(System.currentTimeMillis());
                        for (int i = 0; i < 10; i ++) {
                            int time = random.nextInt(800);
                            subscriber.onNext("[T-1][" + time + "] " + String.valueOf(i));
                            try {
                                Thread.sleep(time);
                            } catch (Exception e) {
                                subscriber.onError(e);
                            }
                        }
                        subscriber.onCompleted();
                    }
                }
        ).subscribeOn(Schedulers.newThread()).timeout(500, TimeUnit.MILLISECONDS));

        /**
         * @param timeout 超时时间
         * @param other 另外一个Observable，当原有的Observable超时后，继续这个Observable
         * Observable<T> timeout(long timeout, TimeUnit timeUnit, Observable<? extends T> other)
         * Observable<T> timeout(long timeout, TimeUnit timeUnit, Observable<? extends T> other, Scheduler scheduler)
         */
        observableList.add(Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        if (subscriber.isUnsubscribed()) return;
                        Random random = new Random(System.currentTimeMillis());
                        for (int i = 0; i < 10; i ++) {
                            int time = random.nextInt(800);
                            subscriber.onNext("[T-2][" + time + "] " + String.valueOf(i));
                            try {
                                Thread.sleep(time);
                            } catch (Exception e) {
                                subscriber.onError(e);
                            }
                        }
                        subscriber.onCompleted();
                    }
                }
        ).subscribeOn(Schedulers.newThread()).timeout(500, TimeUnit.MILLISECONDS, Observable.just("aaa")));

        /**
         * @param firstTimeoutSelector 第一次超时的选择器
         * @param timeoutSelector 超时的选择器
         * <U, V> Observable<T> timeout(
         *                  Func0<? extends Observable<U>> firstTimeoutSelector,
         *                  Func1<? super T, ? extends Observable<V>> timeoutSelector)
         *
         * <U, V> Observable<T> timeout(
         *                  Func0<? extends Observable<U>> firstTimeoutSelector,
         *                  Func1<? super T, ? extends Observable<V>> timeoutSelector,
         *                  Observable<? extends T> other)
         *
         * <V> Observable<T> timeout(Func1<? super T, ? extends Observable<V>> timeoutSelector)
         *
         * <V> Observable<T> timeout(Func1<? super T, ? extends Observable<V>> timeoutSelector,
         *                  Observable<? extends T> other)
         */
        observableList.add(Observable.create(
                new Observable.OnSubscribe<String>() {
                    @Override
                    public void call(Subscriber<? super String> subscriber) {
                        if (subscriber.isUnsubscribed()) return;
                        Random random = new Random(System.currentTimeMillis());
                        for (int i = 0; i < 10; i ++) {
                            int time = random.nextInt(800);
                            if (i == 0) {
                                // time = 650 在第一次超时选择器就会出异常
                                // time = 550 在第一次超时选择器不会出异常，但是在每一项的超时选择器出异常
                                // time = 400 每一项都不会出异常
                                time = 400;
                            }
                            try {
                                Thread.sleep(time);
                            } catch (Exception e) {
                                subscriber.onError(e);
                            }
                            subscriber.onNext("[T-3][" + time + "] " + String.valueOf(i));
                        }
                        subscriber.onCompleted();
                    }
                }
        ).subscribeOn(Schedulers.newThread()).timeout(
                /**
                 * 第一项数据的超时选择器：针对第一项数据创建的超时Observable，如果满足这个超时选择器，还需要判断每一项的超时选择器
                 */
                new Func0<Observable<Long>>() {
                    @Override
                    public Observable<Long> call() {
                        LogUtils.d(TAG, "[第一次超时选择器]");
                        return Observable.timer(600, TimeUnit.MILLISECONDS);
                    }
                },
                /**
                 * 超时选择器：对原始Observable发出的每一项数据都创建一个超时Observable
                 *            在超时Observable终止时，原始的Observable没有发出数据，就算超时
                 */
                new Func1<String, Observable<Long>>() {
                    @Override
                    public Observable<Long> call(String s) {
                        LogUtils.d(TAG, "[超时选择器] " + s);
                        return Observable.timer(500, TimeUnit.MILLISECONDS);
                    }
                }
        ));
    }
}

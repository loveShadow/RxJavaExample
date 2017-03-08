package com.example.rxjava.operationalCharacter.AuxiliaryOperation;

import com.example.rxjava.log.LogUtils;
import com.example.rxjava.operationalCharacter.BaseOperational;


import rx.Notification;
import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.observers.Observers;

/**
 * Created by zhaoli on 2017/3/1.
 * Do 操作符
 * [DoOnEach]           ----   发射每一个操作(包括数据项、onError、onCompleted)都会封装成Notification给订阅者
 * [DoOnNext]           ----   发射每一个数据项会进行通知
 * [DoOnError]          ----   出现错误时进行通知
 * [DoOnCompleted]      ----   完成时进行通知
 * [DoOnTerminate]      ----   将要完成时进行通知(无论是正常完成还是出错完成)
 * [DoOnSubscribe]      ----   当被订阅时进行通知
 * [DoOnUnSubscribe]    ----   当被取消订阅时进行通知
 */
public class Do extends BaseOperational {
    @Override
    protected void create() {
        observableList.add(Observable.just("abc", "def", "gty").doOnEach(
                /**
                 * 针对原始Observable发出的信号进行订阅并处理
                 */
                new Action1<Notification<? super String>>() {
                    @Override
                    public void call(Notification<? super String> notification) {
                        LogUtils.d(TAG, "[doOnEach] " + notification.toString());
                    }
                }
        ));
        /**
         * 原始Observable发射数据时会同步发给observer
         * Observable<T> doOnEach(Observer<? super T> observer)
         */
        observableList.add(Observable.just(1, 2, 3).doOnEach(Observers.create(
                new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        LogUtils.d(TAG, "[doOnEach] " + integer);
                    }
                },
                new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        LogUtils.d(TAG, "[doOnEach] " + throwable.getMessage());
                    }
                },
                new Action0() {
                    @Override
                    public void call() {
                        LogUtils.d(TAG, "[doOnEach] " + "onComplete");
                    }
                }
        )));

        /**
         * 类似DonOnEach，原始Observable发出数据项之前会同步接收处理一份，但是他接受的只是数据项
         */
        observableList.add(Observable.just("opi", "uyi").doOnNext(
                new Action1<String>() {
                    @Override
                    public void call(String s) {
                        LogUtils.d(TAG, "[doOnNext] " + s);
                    }
                }
        ));

        /**
         * 当原始Observable被订阅时会进行通知
         */
        observableList.add(Observable.just("qse", "edf").doOnSubscribe(
                new Action0() {
                    @Override
                    public void call() {
                        LogUtils.d(TAG, "[doOnSubscribe] " + "有观察者订阅");
                    }
                }
        ));

        /**
         * 当原始Observable被取消订阅时会进行通知
         */
        observableList.add(Observable.just("ttt", "yyy").doOnUnsubscribe(
                new Action0() {
                    @Override
                    public void call() {
                        LogUtils.d(TAG, "[doOnUnsubscribe] " + "有观察者取消订阅");
                    }
                }
        ));

        /**
         * 当原始Observable发射信号完成时会进行通知
         */
        observableList.add(Observable.just("uuu", "iii").doOnCompleted(
                new Action0() {
                    @Override
                    public void call() {
                        LogUtils.d(TAG, "[doOnCompleted] " + "该原始Observable完成");
                    }
                }
        ));

        /**
         * 当原始Observable出现错误onError时会进行通知
         */
        observableList.add(Observable.just("ooo", null).doOnError(
                new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        LogUtils.d(TAG, "[doOnError] " + "该原始Observable出现错误");
                    }
                }
        ));

        /**
         * 当原始Observable将被终止前会进行通知，无论是正常结束还是异常结束
         * [finallyDo 操作符]被终止后会进行通知，无论是正常结束还是异常结束[已经废弃]
         */
        observableList.add(Observable.just("aaa", "bbb").doOnTerminate(
                new Action0() {
                    @Override
                    public void call() {
                        LogUtils.d(TAG, "[doOnTerminate] " + "该原始Observable将被终止");
                    }
                }
        ));
    }
}

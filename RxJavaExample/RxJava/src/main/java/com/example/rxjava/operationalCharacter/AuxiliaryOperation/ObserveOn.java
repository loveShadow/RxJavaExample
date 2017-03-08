package com.example.rxjava.operationalCharacter.AuxiliaryOperation;

import com.example.rxjava.operationalCharacter.BaseOperational;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by zhaoli on 2017/3/1.
 * ObserveOn 操作符
 * 决定发射信号的Observable在哪个线程
 */
public class ObserveOn extends BaseOperational {
    @Override
    protected void create() {
        /**
         * 观察者在哪个调度器上观察这个Observable，即发射信号的Observable在哪个线程上发射
         * Observable<T> observeOn(Scheduler scheduler)
         * Observable<T> observeOn(Scheduler scheduler, int bufferSize)
         * Observable<T> observeOn(Scheduler scheduler, boolean delayError)
         * Observable<T> observeOn(Scheduler scheduler, boolean delayError, int bufferSize)
         */
        observableList.add(Observable.just("aaa", "bbb").observeOn(Schedulers.newThread()));
        observableList.add(Observable.just("ccc", "ddd").observeOn(Schedulers.newThread(), 0));
        observableList.add(Observable.just("eee", "fff").observeOn(Schedulers.newThread(), false));
        observableList.add(Observable.just("hhh", "iii").observeOn(Schedulers.newThread(), false, 0));
    }
}

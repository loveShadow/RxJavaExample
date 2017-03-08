package com.example.rxjava.operationalCharacter.CreateOperation;

import com.example.rxjava.operationalCharacter.BaseOperational;

import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * Created by zhaoli on 2017/2/18.
 * Interval 操作符示例
 * 创建一个按固定时间间隔发射整数序列的Observable
 * 执行线程：默认在computation调度器上执行
 */
public class Interval extends BaseOperational {
    @Override
    protected void create() {
        /**
         * @param interval 间隔时间(注：第一个信号会在时间过后才会发出)
         * @param unit 时间单位
         * Observable<Long> interval(long interval, TimeUnit unit)
         *
         * @param scheduler 调度器
         * Observable<Long> interval(long interval, TimeUnit unit, Scheduler scheduler)
         *
         * @param initialDelay 发出第一个信号时等待的时间
         * @param period 后续发射信号的时间段
         * Observable<Long> interval(long initialDelay, long period, TimeUnit unit)
         *
         * Observable<Long> interval(long initialDelay, long period, TimeUnit unit, Scheduler scheduler)
         */
        observableList.add(Observable.interval(1, TimeUnit.SECONDS));
        observableList.add(Observable.interval(5, 2, TimeUnit.SECONDS));
    }
}

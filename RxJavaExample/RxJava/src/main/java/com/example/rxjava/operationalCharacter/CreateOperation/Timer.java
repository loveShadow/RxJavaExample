package com.example.rxjava.operationalCharacter.CreateOperation;

import com.example.rxjava.operationalCharacter.BaseOperational;

import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * Created by zhaoli on 2017/2/18.
 * Timer 操作符
 * 创建一个Observable，它在一个给定的延迟后发射一个特殊的值(数字0)
 * 执行线程：默认在computation调度器上执行
 */
public class Timer extends BaseOperational {
    @Override
    protected void create() {
        /**
         * 延迟两秒后发出信号
         *
         * @param delay 延迟时间
         * Observable<Long> timer(long delay, TimeUnit unit)
         */
        observableList.add(Observable.timer(2, TimeUnit.SECONDS));
    }
}

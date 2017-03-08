package com.example.rxjava.operationalCharacter.FilterOperation;

import com.example.rxjava.operationalCharacter.BaseOperational;

import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * Created by zhaoli on 2017/2/20.
 * TakeLast 操作符示例
 * 与Skip类似，只发射后面的N项数据
 */
public class TakeLast extends BaseOperational {
    @Override
    protected void create() {
        /**
         *
         * @param count 只取最后count个数据
         * Observable<T> takeLast(int count)
         */
        observableList.add(Observable.range(1, 6).takeLast(2));

        /**
         * @param time 只取最后time段时间的数据
         * Observable<T> takeLast(long time, TimeUnit unit)
         */
        observableList.add(Observable.timer(1, TimeUnit.SECONDS).repeat(5).takeLast(2, TimeUnit.SECONDS));

        /**
         * 只取最后time时间段的最后count个数据
         * @param count
         * @param time
         * Observable<T> takeLast(int count, long time, TimeUnit unit)
         */
        observableList.add(Observable.timer(1, TimeUnit.SECONDS).repeat(5).takeLast(2, 3, TimeUnit.SECONDS));
    }
}
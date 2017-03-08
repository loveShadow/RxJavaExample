package com.example.rxjava.operationalCharacter.FilterOperation;

import com.example.rxjava.operationalCharacter.BaseOperational;

import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * Created by zhaoli on 2017/2/20.
 * Take 操作符示例
 * 与Skip类似，只发射前面的N项数据
 */
public class Take extends BaseOperational {
    @Override
    protected void create() {
        /**
         *
         * @param count 只取前count个数据
         * Observable<T> take(int count)
         */
        observableList.add(Observable.range(1, 6).take(2));

        /**
         * @param time 只取前time段时间的数据
         * Observable<T> take(long time, TimeUnit unit)
         */
        observableList.add(Observable.timer(1, TimeUnit.SECONDS).repeat(5).take(2, TimeUnit.SECONDS));
    }
}

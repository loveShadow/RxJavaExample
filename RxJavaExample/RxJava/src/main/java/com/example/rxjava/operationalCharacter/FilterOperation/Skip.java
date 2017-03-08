package com.example.rxjava.operationalCharacter.FilterOperation;

import com.example.rxjava.operationalCharacter.BaseOperational;

import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * Created by zhaoli on 2017/2/20.
 * Skip 操作符示例
 * 抑制(跳过)Observable发射的前N项数据
 */
public class Skip extends BaseOperational {
    @Override
    protected void create() {
        /**
         *
         * @param count 跳过前count个数据
         * Observable<T> skip(int count)
         */
        observableList.add(Observable.range(1, 6).skip(2));

        /**
         * @param time 跳过前time段时间的数据
         * Observable<T> skip(long time, TimeUnit unit)
         */
        observableList.add(Observable.timer(1, TimeUnit.SECONDS).repeat(5).skip(2, TimeUnit.SECONDS));
    }
}

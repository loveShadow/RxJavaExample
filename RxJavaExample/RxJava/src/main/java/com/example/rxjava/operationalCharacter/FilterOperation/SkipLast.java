package com.example.rxjava.operationalCharacter.FilterOperation;

import com.example.rxjava.operationalCharacter.BaseOperational;

import java.util.concurrent.TimeUnit;

import rx.Observable;

/**
 * Created by zhaoli on 2017/2/20.
 * SkipLast 操作符示例
 * 和Skip类似，抑制(跳过)Observable发射的后N项数据
 */
public class SkipLast extends BaseOperational {
    @Override
    protected void create() {
        /**
         *
         * @param count 跳过最后count个数据
         * Observable<T> skipLast(int count)
         */
        observableList.add(Observable.range(1, 6).skipLast(2));

        /**
         * @param time 跳过最后time段时间的数据
         * Observable<T> skipLast(long time, TimeUnit unit)
         */
        observableList.add(Observable.timer(1, TimeUnit.SECONDS).repeat(5).skipLast(2,TimeUnit.SECONDS));
    }
}

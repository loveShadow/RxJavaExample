package com.example.rxjava.operationalCharacter.ArithmeticAndAggregationOperation;

import com.example.rxjava.operationalCharacter.BaseOperational;

import rx.Observable;

/**
 * Created by zhaoli on 2017/3/4.
 * Concat 操作符
 * 不交替的发射两个或多个Observable的发射的信号
 */
public class Concat extends BaseOperational {
    @Override
    protected void create() {
        /**
         * <T> Observable<T> concat(Observable<? extends T> t1, Observable<? extends T> t2)
         */
        observableList.add(Observable.concat(
                Observable.range(1, 5),
                Observable.just(2.3, 4.5)
        ));
    }
}

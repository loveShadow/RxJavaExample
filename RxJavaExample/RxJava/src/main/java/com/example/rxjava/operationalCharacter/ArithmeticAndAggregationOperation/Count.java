package com.example.rxjava.operationalCharacter.ArithmeticAndAggregationOperation;

import com.example.rxjava.operationalCharacter.BaseOperational;

import rx.Observable;

/**
 * Created by zhaoli on 2017/3/4.
 * Count 操作符
 * 计算原始Observable发出信号的数量
 */
public class Count extends BaseOperational {
    @Override
    protected void create() {
        observableList.add(Observable.just("one", "two", "three").count());
        observableList.add(Observable.just(1, 2, 3, 2).countLong());
    }
}

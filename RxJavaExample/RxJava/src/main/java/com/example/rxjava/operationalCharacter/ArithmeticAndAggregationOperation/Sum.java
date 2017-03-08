package com.example.rxjava.operationalCharacter.ArithmeticAndAggregationOperation;

import com.example.rxjava.operationalCharacter.BaseOperational;

import rx.Observable;
import rx.observables.MathObservable;

/**
 * Created by zhaoli on 2017/3/4.
 * Sum 操作符
 * 计算原始Observable发出的信号的总和并发出【只适合发射数字的Observable】
 * 【注意】：不在RxJava的主模块中，在【RxJava-Math】模块中
 */
public class Sum extends BaseOperational {
    @Override
    protected void create() {
        observableList.add(MathObservable.sumInteger(Observable.just(3, 4, 5)));
        observableList.add(MathObservable.sumLong(Observable.just(9l, 4l)));
        observableList.add(MathObservable.sumFloat(Observable.just(9.8f, 3.4f)));
        observableList.add(MathObservable.sumDouble(Observable.just(23.4d, 54.5d)));
    }
}

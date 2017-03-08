package com.example.rxjava.operationalCharacter.ArithmeticAndAggregationOperation;

import com.example.rxjava.operationalCharacter.BaseOperational;

import rx.Observable;
import rx.observables.MathObservable;

/**
 * Created by zhaoli on 2017/3/4.
 * Average 操作符
 * 计算原始Observable发出信号的平均值然后发出【只适合发射数字的Observable】
 * 【注意】：不在RxJava的主模块中，在【RxJava-Math】模块中
 */
public class Average extends BaseOperational {
    @Override
    protected void create() {
        /**
         * 求出平均值并发出信号
         * Observable<Integer> averageInteger(Observable<Integer> source)
         * Observable<Long> averageLong(Observable<Long> source)
         * Observable<Float> averageFloat(Observable<Float> source)
         * Observable<Double> averageDouble(Observable<Double> source)
         */
        observableList.add(MathObservable.averageInteger(Observable.range(1, 10)));
        observableList.add(MathObservable.averageLong(Observable.just(23l, 24l, 25l)));
        observableList.add(MathObservable.averageFloat(Observable.just(3.4f, 3.5f, 3.6f)));
        observableList.add(MathObservable.averageDouble(Observable.just(5.67d, 5.77d, 5.87d)));
    }
}

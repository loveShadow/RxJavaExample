package com.example.rxjava.operationalCharacter.ArithmeticAndAggregationOperation;

import com.example.rxjava.operationalCharacter.BaseOperational;

import rx.Observable;
import rx.observables.MathObservable;

/**
 * Created by zhaoli on 2017/3/4.
 * Min 操作符
 * 计算原始Observable发出信号的最小的信号并发射
 * 【注意】：不在RxJava的主模块中，在【RxJava-Math】模块中
 */
public class Min extends BaseOperational {
    @Override
    protected void create() {
        observableList.add(MathObservable.min(Observable.just(2.3f, 4.5f, 0.1f)));
        /**
         * 示例结果：
         *      D/LogUtils[Min]: aaa    --- 首字母最小的
         *      D/LogUtils[Min]: 完成了
         */
        observableList.add(MathObservable.min(Observable.just("c", "az", "bbb", "eee", "fff", "hi")));
    }
}

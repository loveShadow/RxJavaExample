package com.example.rxjava.operationalCharacter.ArithmeticAndAggregationOperation;

import com.example.rxjava.operationalCharacter.BaseOperational;

import rx.Observable;
import rx.observables.MathObservable;

/**
 * Created by zhaoli on 2017/3/4.
 * Max 操作符
 * 计算原始Observable发出信号的最大的信号并发射
 * 【注意】：不在RxJava的主模块中，在【RxJava-Math】模块中
 */
public class Max extends BaseOperational {
    @Override
    protected void create() {
        observableList.add(MathObservable.max(Observable.just(2, 3, 5, 1)));
        /**
         * 示例结果：
         *      D/LogUtils[Max]: observable     --- 首字母最大的
         *      D/LogUtils[Max]: 完成了
         */
        observableList.add(MathObservable.max(Observable.just("d", "observable", "za", "c", "hello", "ff")));
    }
}

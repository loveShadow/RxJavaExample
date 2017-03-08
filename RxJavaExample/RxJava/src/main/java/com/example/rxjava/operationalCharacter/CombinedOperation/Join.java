package com.example.rxjava.operationalCharacter.CombinedOperation;

import com.example.rxjava.log.LogUtils;
import com.example.rxjava.operationalCharacter.BaseOperational;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * Created by zhaoli on 2017/2/21.
 * Join 操作符示例
 * 原始Observable按照自己的频率发射数据的过程中，另外一个Observable每发出一个信号，会和原始Observable已发出的信号进行合并。
 * 例如：
 *  原始Observable：---1---2---3---4---5---6---
 *  另外Observable：---a---b---c---d---e---f---
 *  产生的结果：    ---(1, a)---(1, b)(2, b)---(1, c)(2, c)(3, c)---...
 */
public class Join extends BaseOperational {
    @Override
    protected void create() {
        /**
         * @param right 第二个Observable
         * @param
         * @param
         * @param
         * <TRight, TLeftDuration, TRightDuration, R> Observable<R> join(
         *                          Observable<TRight> right,
         *                          Func1<T, Observable<TLeftDuration>> leftDurationSelector,
         *                          Func1<TRight, Observable<TRightDuration>> rightDurationSelector,
         *                          Func2<T, TRight, R> resultSelector)
         */
        observableList.add(Observable.range(1, 3).join(
                Observable.range(4, 5),
                /**
                 * 左边(第一个)的时间规则
                 */
                new Func1<Integer, Observable<Long>>() {
                    @Override
                    public Observable<Long> call(Integer integer) {
                        LogUtils.d(TAG, "[LEFT] = " + integer);
                        long time = new Random(System.currentTimeMillis()).nextInt(1000);
                        return Observable.timer(time, TimeUnit.MILLISECONDS);
                    }
                },
                /**
                 * 右边(第二个)的时间规则
                 */
                new Func1<Integer, Observable<Long>>() {
                    @Override
                    public Observable<Long> call(Integer integer) {
                        LogUtils.d(TAG, "[RIGHT] = " + integer);
                        return Observable.interval(500, TimeUnit.MILLISECONDS);
                    }
                },
                /**
                 * 合并方法（将两个Observable发出的信号合并后的新信号）
                 */
                new Func2<Integer, Integer, String>() {
                    @Override
                    public String call(Integer integer, Integer integer2) {
                        return "(" + integer + ", " + integer2 + ")";
                    }
                }
        ));
    }
}

package com.example.rxjava.operationalCharacter.ArithmeticAndAggregationOperation;

import com.example.rxjava.operationalCharacter.BaseOperational;

import rx.Observable;
import rx.functions.Func2;

/**
 * Created by zhaoli on 2017/3/4.
 * Reduce 操作符
 * 将原始Observable发射的元素应用一个函数计算出值，再发出
 * 类似【Scan】
 */
public class Reduce extends BaseOperational {
    @Override
    protected void create() {
        /**
         * Observable<T> reduce(Func2<T, T, T> accumulator)
         */
        observableList.add(Observable.just(1, 3, 5).reduce(
                new Func2<Integer, Integer, Integer>() {
                    /**
                     * 计算的方法
                     * @param lastResult 上一次计算的结果
                     * @param next 这一次的数据
                     * @return 返回计算的结果
                     */
                    @Override
                    public Integer call(Integer lastResult, Integer next) {
                        return lastResult + next;
                    }
                }
        ));

        /**
         * @param initialValue 初始值
         * <R> Observable<R> reduce(R initialValue, Func2<R, ? super T, R> accumulator)
         */
        observableList.add(Observable.just(2, 6, 8).reduce(
                3,
                new Func2<Integer, Integer, Integer>() {
                    @Override
                    public Integer call(Integer lastResult, Integer next) {
                        return lastResult + next;
                    }
                }
        ));
    }
}

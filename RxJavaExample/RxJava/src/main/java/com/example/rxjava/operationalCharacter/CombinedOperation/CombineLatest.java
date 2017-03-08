package com.example.rxjava.operationalCharacter.CombinedOperation;

import com.example.rxjava.log.LogUtils;
import com.example.rxjava.operationalCharacter.BaseOperational;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Func2;

/**
 * Created by zhaoli on 2017/2/20.
 * CombineLast 操作符示例
 * 当两个Observables中的任何一个发射了数据时，使用一个方法结合每个Observable发射的最近数据项，并且基于这个函数的结果发射数据
 */
public class CombineLatest extends BaseOperational {
    @Override
    protected void create() {
        /**
         * ---1--------2---------------------------3-------4--------5----
         * -----a-------------b------c-----d-----------------------------
         * -----(1a)---(2a)---(2b)---(2c)--(2d)----(3d)----(4d)-----(5d)-
         *
         * 总共支持[2-9]个Observable的合并
         * <T1, T2, R> Observable<R> combineLatest(Observable<? extends T1> o1,
         *                                         Observable<? extends T2> o2,
         *                                         Func2<? super T1, ? super T2, ? extends R> combineFunction)
         *
         * <T1, T2, T3, R> Observable<R> combineLatest(Observable<? extends T1> o1,
         *                                             Observable<? extends T2> o2,
         *                                             Observable<? extends T3> o3,
         *                                             Func3<? super T1, ? super T2, ? super T3, ? extends R> combineFunction)
         *
         * 还支持数组List及实现Iterable接口
         * <T, R> Observable<R> combineLatest(List<? extends Observable<? extends T>> sources,
         *                                    FuncN<? extends R> combineFunction)
         *
         * <T, R> Observable<R> combineLatest(Iterable<? extends Observable<? extends T>> sources,
         *                                    FuncN<? extends R> combineFunction)
         */
        observableList.add(Observable.combineLatest(Observable.interval(1, TimeUnit.SECONDS),
                Observable.interval(2, TimeUnit.SECONDS),
                new Func2<Long, Long, Long>() {
                    @Override
                    public Long call(Long aLong, Long aLong2) {
                        LogUtils.d(TAG, "[数据项] A = " + aLong + " B = " + aLong2);
                        return aLong + aLong2;
                    }
                }));
    }
}

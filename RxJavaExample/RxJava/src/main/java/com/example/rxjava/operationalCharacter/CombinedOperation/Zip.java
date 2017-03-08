package com.example.rxjava.operationalCharacter.CombinedOperation;

import com.example.rxjava.operationalCharacter.BaseOperational;

import rx.Observable;
import rx.functions.Func2;

/**
 * Created by zhaoli on 2017/2/21.
 * Zip 操作符示例
 * 将多个数据序列进行一个数据对应一个数据的打包后重新发出[最短的那方终止，整个数据序列会终止]
 */
public class Zip extends BaseOperational {
    @Override
    protected void create() {
        /**
         * 将[2-9]个数据序列进行一个数据对应一个数据的打包后重新发出
         * @param o1 数据序列1
         * @param o2 数据序列2
         * @param zipFunction 打包的方法
         * <T1, T2, R> Observable<R> zip(
         *                  Observable<? extends T1> o1,
         *                  Observable<? extends T2> o2,
         *                  final Func2<? super T1, ? super T2, ? extends R> zipFunction)
         */
        observableList.add(Observable.zip(
                Observable.range(1, 8),
                Observable.just("adb", "def", "sdf", "grgt", "rtgv"),
                new Func2() {
                    @Override
                    public Object call(Object o, Object o2) {
                        return "[" + o + " + " + o2 + "]";
                    }
                }));
    }
}

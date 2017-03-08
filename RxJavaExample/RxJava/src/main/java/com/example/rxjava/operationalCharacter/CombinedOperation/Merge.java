package com.example.rxjava.operationalCharacter.CombinedOperation;

import com.example.rxjava.operationalCharacter.BaseOperational;

import rx.Observable;

/**
 * Created by zhaoli on 2017/2/21.
 * Merge 操作符示例
 */
public class Merge extends BaseOperational {
    @Override
    protected void create() {
        /**
         * --1--2--3--4--5--6--7--8--9--10
         * --20--21--22--23--24--25
         * --1--...-10-20-...-25
         *
         * 注意：会交叉，使用Concat就不会交叉
         *
         * @param t1 t2 两个待合并的Observable [支持2-9]个参数
         * <T> Observable<T> merge(Observable<? extends T> t1, Observable<? extends T> t2)
         *
         * 或者列表、数组、甚至一个发射Observable序列的Observable
         *
         * @param maxConcurrent 同时订阅的Observables的最大数量
         * <T> Observable<T> merge(Observable<? extends T>[] sequences)
         * <T> Observable<T> merge(Observable<? extends Observable<? extends T>> source, int maxConcurrent)
         * <T> Observable<T> merge(Iterable<? extends Observable<? extends T>> sequences)
         * <T> Observable<T> merge(Iterable<? extends Observable<? extends T>> sequences, int maxConcurrent)
         * <T> Observable<T> merge(Observable<? extends Observable<? extends T>> source)
         * <T> Observable<T> merge(Observable<? extends Observable<? extends T>> source, int maxConcurrent)
         */
        observableList.add(Observable.merge(
                Observable.range(1, 10),
                Observable.range(20, 25)
        ));
    }
}

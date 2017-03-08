package com.example.rxjava.operationalCharacter.TransformationOperation;

import com.example.rxjava.log.LogUtils;
import com.example.rxjava.operationalCharacter.BaseOperational;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Func0;
import rx.functions.Func1;

/**
 * Created by zhaoli on 2017/2/19.
 * FlatMap 操作符示例
 * FlatMap将一个发射数据的Observable变换为多个Observables，然后将它们发射的数据合并后放进一个单独的Observable
 */
public class FlatMap extends BaseOperational {
    @Override
    protected void create() {
        /**
         *
         * 将原始Observable发出的信号数据进行转换然后生成新的Observable
         * 注意：任何一个发出异常，就会全部终止
         * <R> Observable<R> flatMap(Func1<? super T, ? extends Observable<? extends R>> func)
         *
         * @param maxConcurrent 最大订阅数
         * <R> Observable<R> flatMap(Func1<? super T, ? extends Observable<? extends R>> func, int maxConcurrent)
         */
        observableList.add(Observable.interval(1, TimeUnit.SECONDS).flatMap(new Func1<Long, Observable<String>>() {
            @Override
            public Observable<String> call(Long integer) {
                LogUtils.d(TAG, "[收到原始信号数据] integer = " + integer);
                return Observable.just("转换之后数据--(" + integer + ")", "转换之后数据--( -" + integer + ")");
            }
        }));

        /**
         * 这个不光为原始Observable的每一项数据创建一个新的，而且为每一个通知，包括onError onComplete
         * <R> Observable<R> flatMap(
                                Func1<? super T, ? extends Observable<? extends R>> onNext,
                                Func1<? super Throwable, ? extends Observable<? extends R>> onError,
                                Func0<? extends Observable<? extends R>> onCompleted)
         */
        Observable.interval(1, TimeUnit.SECONDS).flatMap(new Func1<Long, Observable<?>>() {
            @Override
            public Observable<?> call(Long aLong) {
                LogUtils.d(TAG, "收到一个数据");
                return null;
            }
        }, new Func1<Throwable, Observable<?>>() {
            @Override
            public Observable<?> call(Throwable throwable) {
                LogUtils.d(TAG, "收到一个异常");
                return null;
            }
        }, new Func0<Observable<?>>() {
            @Override
            public Observable<?> call() {
                LogUtils.d(TAG, "原始数据结束");
                return null;
            }
        });
    }
}

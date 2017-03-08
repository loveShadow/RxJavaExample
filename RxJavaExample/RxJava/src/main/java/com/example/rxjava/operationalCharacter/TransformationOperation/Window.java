package com.example.rxjava.operationalCharacter.TransformationOperation;

import com.example.rxjava.log.LogUtils;
import com.example.rxjava.operationalCharacter.BaseOperational;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by zhaoli on 2017/2/19.
 * Window 操作符示例
 * 与Buffer类似。但不是发射来自原始Observable的数据包，
 * 它发射的是Observables，这些Observables中的每一个都发射原始Observable数据的一个子集，最后发射一个onCompleted通知
 */
public class Window extends BaseOperational {

    @Override
    protected void create() {
        /**
         * [1,2,3,....16,17]
         * [1,2,3] 由一个Observable发出
         * [4,5,6] 由一个Observable发出
         * .....
         */
        Observable.range(1, 17).window(3)
        .subscribe(new Action1<Observable<Integer>>() {
            @Override
            public void call(final Observable<Integer> observable) {
                observable.subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        LogUtils.d(TAG + "[" + observable.toString() + "]", String.valueOf(integer));
                    }
                });
            }
        });
    }
}

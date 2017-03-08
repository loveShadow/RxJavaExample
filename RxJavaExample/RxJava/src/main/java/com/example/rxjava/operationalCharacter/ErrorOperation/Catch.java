package com.example.rxjava.operationalCharacter.ErrorOperation;

import com.example.rxjava.log.LogUtils;
import com.example.rxjava.operationalCharacter.BaseOperational;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by zhaoli on 2017/2/22.
 * 主要包含:
 *      onErrorReturn
 *      onErrorResumeNext
 *      onExceptionResumeNext
 * 当原始序列发射过程中出现异常，能够捕获到并做出后续的处理（例如：返回一个正常项、继续发射下一个Observable序列）
 */
public class Catch extends BaseOperational {
    @Override
    protected void create() {

        Observable errorOb = Observable.create(new Observable.OnSubscribe<Object>() {
            @Override
            public void call(Subscriber<? super Object> subscriber) {
                for (int i = 0; i < 4; i ++) {
                    if (i == 2) {
                        subscriber.onError(new Exception("异常"));
                    } else {
                        subscriber.onNext(i);
                    }
                    try {
                        Thread.sleep(100);
                    } catch (Exception e){}
                }
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.newThread());

        /**
         * onErrorReturn
         * 当原始的Observable出现异常时，发射一个特殊的项正常终止
         * ----------------------
         * 0
         * 1
         * [异常情况]
         * 完成了
         */
        observableList.add(errorOb.onErrorReturn(new Func1<Throwable, String>() {
            @Override
            public String call(Throwable throwable) {
                return "[异常情况]";
            }
        }));

        /**
         * onErrorResumeNext
         * 当原始的Observable出现异常时，开始发射第二个Observable
         * --------------------------
         * 0
         * 1
         * [这个是带异常的] + 异常
         * 5
         * 6
         * 7
         */
        observableList.add(errorOb.onErrorResumeNext(
                new Func1<Throwable, Observable<Integer>>() {
                    @Override
                    public Observable<Integer> call(Throwable throwable) {
                        LogUtils.d(TAG, "[这个是带异常的] + " + throwable.getMessage());
                        return Observable.range(5, 3);
                    }
                }));
        observableList.add(errorOb.onErrorResumeNext(
                Observable.range(5, 10)
        ));

        /**
         * onExceptionResumeNext
         * 当原始的Observable出现异常时，继续发射后面的数据项，如果收到的不是异常，还是会把onError抛出
         * 例如：new Throwable()等
         */
        observableList.add(errorOb.onExceptionResumeNext(
                Observable.range(15, 3)
        ));
    }
}

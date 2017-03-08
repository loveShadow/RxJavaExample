package com.example.rxjava.operationalCharacter.ErrorOperation;

import com.example.rxjava.log.LogUtils;
import com.example.rxjava.operationalCharacter.BaseOperational;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * Created by zhaoli on 2017/2/22.
 */
public class Retry extends BaseOperational {
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
         * 当原始Observable发生异常时，能够进行重试，即重新订阅，重新开始发射数据
         * Observable<T> retry()
         *
         * @param count 重试次数，如果重试过后，还是异常，将会把最新的异常抛出
         * Observable<T> retry(final long count)
         * -----------------------------------------
         * 0
         * 1
         * 0    ---第一次重试
         * 1
         * 0    ---第二次重试
         * 1
         * 出异常了
         */
        observableList.add(errorOb.retry(2));

        /**
         * 自定义方法来决定是否需要重试
         * 0
         * 1
         * 第 1 次重试
         * 0
         * 1
         * 第 2 次重试
         * 0
         * 1
         * 第 3 次重试
         * 出异常了
         */
        observableList.add(errorOb.retry(
                new Func2<Integer, Throwable, Boolean>() {
                    @Override
                    /**
                     * @param integer 重试次数
                     * @param throwable 异常
                     */
                    public Boolean call(Integer integer, Throwable throwable) {
                        LogUtils.d(TAG, "第 " + integer + " 次重试");
                        if (integer < 3) {
                            return true;
                        } else {
                            return false;
                        }
                    }
                }
        ));

        /**
         * 将错误和重试次数打包后
         */
        observableList.add(errorOb.retryWhen(
                new Func1<Observable<? extends Throwable>, Observable<?>>() {
                    @Override
                    public Observable<?> call(Observable<? extends Throwable> errors) {
                        return errors.zipWith(Observable.range(1, 3), new Func2<Throwable, Integer, Integer>() {
                                    @Override
                                    public Integer call(Throwable throwable, Integer integer) {
                                        return integer;
                                    }
                                })
                                .flatMap(new Func1<Integer, Observable<?>>() {
                                    @Override
                                    public Observable<?> call(Integer i) {
                                        LogUtils.d(TAG, "[重试] " + i);
                                        return Observable.timer(i, TimeUnit.SECONDS);
                                    }
                                });
                    }
                }
        ));
    }
}

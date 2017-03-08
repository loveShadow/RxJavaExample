package com.example.rxjava.operationalCharacter.AuxiliaryOperation;

import com.example.rxjava.log.LogUtils;
import com.example.rxjava.operationalCharacter.BaseOperational;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Func0;
import rx.functions.Func1;

/**
 * Created by zhaoli on 2017/2/23.
 * Delay 操作符
 * 延迟一段时间再发射原始的Observable
 */
public class Delay extends BaseOperational {
    @Override
    protected void create() {
        /**
         * @param delay 延迟一段时间再发射原始Observable
         * Observable<T> delay(long delay, TimeUnit unit)
         * Observable<T> delay(long delay, TimeUnit unit, Scheduler scheduler)
         */
        observableList.add(Observable.just("adb", 3.4).delay(2, TimeUnit.SECONDS));

        /**
         * delay 不会平移onError通知
         */
        observableList.add(Observable.just("def", null, 4.6).delay(2, TimeUnit.SECONDS));

        observableList.add(Observable.just("rgh", 7.8).delay(
                /**
                 * 发射数据项延迟
                 * 当Func1返回的Observable数据发射完成时，再进行原始Observable的发射
                 */
                new Func1<Serializable, Observable<Long>>() {
                    @Override
                    public Observable<Long> call(Serializable serializable) {
                        return Observable.timer(3, TimeUnit.SECONDS);
                    }
                }
        ));

        observableList.add(Observable.just("rty", 9.8).delay(
                /**
                 * 订阅延迟
                 * 延迟3秒订阅
                 */
                new Func0<Observable<Long>>() {
                    @Override
                    public Observable<Long> call() {
                        return Observable.timer(3, TimeUnit.SECONDS);
                    }
                },
                /**
                 * 发射数据项延迟
                 * 延迟2秒发射数据
                 */
                new Func1<Serializable, Observable<Long>>() {
                    @Override
                    public Observable<Long> call(Serializable serializable) {
                        return Observable.timer(1, TimeUnit.SECONDS);
                    }
                }
        ));

        LogUtils.d(TAG, "原始Observable开始发射");
    }
}

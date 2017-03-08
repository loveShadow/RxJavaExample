package com.example.rxjava.operationalCharacter.AuxiliaryOperation;

import com.example.rxjava.log.LogUtils;
import com.example.rxjava.operationalCharacter.BaseOperational;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action0;

/**
 * Created by zhaoli on 2017/3/1.
 * Serialize 操作符
 * 强制保证Observable正常发射数据
 * 例如：不会出现 onError 或 onCompleted 之后还会出现 onNext
 */
public class Serialize extends BaseOperational {
    @Override
    protected void create() {

        /**
         * 强制保证Observable正常发射数据
         * 没有使用 Serialize 确满足Observable约束的原因是通过 subscribe 订阅是安全订阅，收到 onCompleted 会自动终止订阅
         */
        observableList.add(Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("aaa");
                subscriber.onNext("bbb");
                subscriber.onCompleted();
                subscriber.onNext("ccc");
                subscriber.onCompleted();
            }
        }).doOnUnsubscribe(new Action0() {
            @Override
            public void call() {
                LogUtils.d(TAG, "取消订阅");
            }
        }));

        /**
         * 如果使用不安全的 subscribe 订阅，就会出现问题
         */
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (subscriber.isUnsubscribed()) return;
                subscriber.onNext("rrr");
                subscriber.onNext("ttt");
                subscriber.onCompleted();
                subscriber.onNext("sss");
                subscriber.onCompleted();
            }
        })
        .doOnUnsubscribe(new Action0() {
            @Override
            public void call() {
                LogUtils.d(TAG, "取消订阅");
            }
        })
        .unsafeSubscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                LogUtils.d(TAG, "完成了");
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.d(TAG, "出错了");
            }

            @Override
            public void onNext(String s) {
                LogUtils.d(TAG, "收到数据 " + s);
            }
        });

        /**
         * 加上serialize，保证正常发射
         */
        Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (subscriber.isUnsubscribed()) return;
                subscriber.onNext("ddd");
                subscriber.onNext("eee");
                subscriber.onCompleted();
                subscriber.onNext("fff");
                subscriber.onCompleted();
            }
        })
        .doOnUnsubscribe(new Action0() {
            @Override
            public void call() {
                LogUtils.d(TAG, "取消订阅");
            }
        })
        .serialize()
        .unsafeSubscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                LogUtils.d(TAG, "完成了");
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.d(TAG, "出错了");
            }

            @Override
            public void onNext(String s) {
                LogUtils.d(TAG, "收到数据 " + s);
            }
        });
    }
}

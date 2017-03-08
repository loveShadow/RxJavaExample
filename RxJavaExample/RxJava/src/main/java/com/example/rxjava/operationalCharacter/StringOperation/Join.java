package com.example.rxjava.operationalCharacter.StringOperation;

import com.example.rxjava.operationalCharacter.BaseOperational;

import rx.Observable;
import rx.observables.StringObservable;

/**
 * Created by zhaoli on 2017/3/5.
 *  Join 操作符
 *  将原始Observable发出的字符串信号用固定的字符串“拼接”成为一个进行发射
 * 【注意】：不在RxJava的主模块中，在【RxJava-String】模块中
 */
public class Join extends BaseOperational {
    @Override
    protected void create() {
        /**
         * 示例结果：
         *      D/LogUtils[Join]: hello_new_world
         *      D/LogUtils[Join]: 完成了
         */
        observableList.add(StringObservable.join(
                Observable.just("hello", "new", "world"),
                "_"
        ));
    }
}

package com.example.rxjava.operationalCharacter.StringOperation;

import com.example.rxjava.operationalCharacter.BaseOperational;

import rx.Observable;
import rx.observables.StringObservable;

/**
 * Created by zhaoli on 2017/3/5.
 *  StringConcat 操作符
 *  将原始Observable发射的字符串序列信号连接起来进行发射
 * 【注意】：不在RxJava的主模块中，在【RxJava-String】模块中
 */
public class StringConcat extends BaseOperational {
    @Override
    protected void create() {
        /**
         * 示例结果：
         *      D/LogUtils[StringConcat]: hellonewworld
         *      D/LogUtils[StringConcat]: 完成了
         */
        observableList.add(StringObservable.stringConcat(
                Observable.just("hello", "new", "world")
        ));
    }
}

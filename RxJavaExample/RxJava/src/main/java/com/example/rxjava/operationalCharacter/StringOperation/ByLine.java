package com.example.rxjava.operationalCharacter.StringOperation;

import com.example.rxjava.operationalCharacter.BaseOperational;

import rx.Observable;
import rx.observables.StringObservable;

/**
 * Created by zhaoli on 2017/3/5.
 * ByLine 操作符
 * 将原始的Observable转换成一个行序列的Observable，按换行符分割
 * 【注意】：不在RxJava的主模块中，在【RxJava-String】模块中
 */
public class ByLine extends BaseOperational {
    @Override
    protected void create() {
        /**
         * 示例结果：
         *      D/LogUtils[ByLine]: hellonewworld
         *      D/LogUtils[ByLine]: 完成了
         */
        observableList.add(StringObservable.byLine(
                Observable.just("hello", "new", "world")
        ));

        /**
         * 示例结果：
         *      D/LogUtils[ByLine]: hello
         *      D/LogUtils[ByLine]: new
         *      D/LogUtils[ByLine]: world
         *      D/LogUtils[ByLine]: 完成了
         */
        observableList.add(StringObservable.byLine(
                Observable.just("hello\n" +
                        "new\n" +
                        "world\n")
        ));
    }
}

package com.example.rxjava.operationalCharacter.StringOperation;

import com.example.rxjava.operationalCharacter.BaseOperational;

import rx.Observable;
import rx.observables.StringObservable;

/**
 * Created by zhaoli on 2017/3/5.
 *  Split 操作符
 *  将原始Observable发射的字符串信号进行“分割”
 * 【注意】：不在RxJava的主模块中，在【RxJava-String】模块中
 */
public class Split extends BaseOperational {
    @Override
    protected void create() {
        /**
         * 示例结果：
         *      D/LogUtils[Split]: hell
         *      D/LogUtils[Split]: neww
         *      D/LogUtils[Split]: rld
         *      D/LogUtils[Split]: 完成了
         */
        observableList.add(StringObservable.split(
                Observable.just("hello", "new", "world"),
                "o"
        ));
    }
}

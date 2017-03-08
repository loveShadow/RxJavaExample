package com.example.rxjava.operationalCharacter.ConditionsAndBooleanOperation;

import com.example.rxjava.operationalCharacter.BaseOperational;

import rx.Observable;

/**
 * Created by zhaoli on 2017/3/4.
 * IsEmpty 操作符
 * 原始Observable发射的信号是否为空
 *      是：true
 *      否：false
 */
public class IsEmpty extends BaseOperational {
    @Override
    protected void create() {
        /**
         * 示例结果：
         *      D/LogUtils[IsEmpty]: false
         *      D/LogUtils[IsEmpty]: 完成了
         */
        observableList.add(Observable.just("aaa", "bbb").isEmpty());
        /**
         * 示例结果：
         *      D/LogUtils[IsEmpty]: true
         *      D/LogUtils[IsEmpty]: 完成了
         */
        observableList.add(Observable.empty().isEmpty());
    }
}

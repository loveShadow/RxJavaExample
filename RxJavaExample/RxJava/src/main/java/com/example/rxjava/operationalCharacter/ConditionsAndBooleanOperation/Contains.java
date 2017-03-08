package com.example.rxjava.operationalCharacter.ConditionsAndBooleanOperation;

import com.example.rxjava.operationalCharacter.BaseOperational;

import rx.Observable;

/**
 * Created by zhaoli on 2017/3/4.
 * Contains 操作符
 * 原始Observable发出的信号是否包含该信号，
 *      包含：发射true
 *      不包含：发射false
 */
public class Contains extends BaseOperational {
    @Override
    protected void create() {
        /**
         * 示例结果：
         *      D/LogUtils[Contains]: false
         *      D/LogUtils[Contains]: 完成了
         */
        observableList.add(Observable.just("aaa", "bbb", "ccc").contains("ddd"));
        /**
         * 示例结果：
         *      D/LogUtils[Contains]: true
         *      D/LogUtils[Contains]: 完成了
         */
        observableList.add(Observable.just("aaa", "bbb", "ccc").contains("bbb"));
    }
}

package com.example.rxjava.operationalCharacter.ConditionsAndBooleanOperation;

import com.example.rxjava.operationalCharacter.BaseOperational;

import rx.Observable;

/**
 * Created by zhaoli on 2017/3/4.
 * DefaultIfEmpty 操作符
 * 如果原始Observable发射信号中【没有值】，则不用再发射默认信号
 *                            【有值】，则发射默认信号
 */
public class DefaultIfEmpty extends BaseOperational {
    @Override
    protected void create() {
        /**
         * 示例结果：
         *      D/LogUtils[DefaultIfEmpty]: ddd
         *      D/LogUtils[DefaultIfEmpty]: 完成了
         */
        observableList.add(Observable.empty().defaultIfEmpty("ddd"));
        /**
         * 示例结果：
         *      D/LogUtils[DefaultIfEmpty]: eee
         *      D/LogUtils[DefaultIfEmpty]: fff
         *      D/LogUtils[DefaultIfEmpty]: hhh
         *      D/LogUtils[DefaultIfEmpty]: 完成了
         */
        observableList.add(Observable.just("eee", "fff", "hhh").defaultIfEmpty("fff"));
    }
}

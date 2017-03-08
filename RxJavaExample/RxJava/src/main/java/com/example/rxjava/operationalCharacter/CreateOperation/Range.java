package com.example.rxjava.operationalCharacter.CreateOperation;

import com.example.rxjava.operationalCharacter.BaseOperational;

import rx.Observable;

/**
 * Created by zhaoli on 2017/2/18.
 * Range 操作符示例
 * 创建一个发射特定整数序列的Observable
 * 执行线程：默认不在任何调度器上执行
 */
public class Range extends BaseOperational {
    @Override
    protected void create() {
        /**
         * @param start 起始值
         * @param count 特定整数序列的长度(=0 不发射任何数据, <0 抛异常)
         * 例如：start = 1 count = 2
         *       那么发射信号为：1, 2
         */
        observableList.add(Observable.range(2, 3));
        observableList.add(Observable.range(2, 0));
        observableList.add(Observable.range(2, -1));
    }
}

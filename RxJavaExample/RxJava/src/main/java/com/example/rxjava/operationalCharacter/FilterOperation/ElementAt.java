package com.example.rxjava.operationalCharacter.FilterOperation;

import com.example.rxjava.operationalCharacter.BaseOperational;

import rx.Observable;

/**
 * Created by zhaoli on 2017/2/19.
 * ElementAt 操作符示例
 * 只发射第N项数据[基于索引0开始]
 */
public class ElementAt extends BaseOperational {
    @Override
    protected void create() {
        /**
         * [1, 2, 3, 4, 5]
         * 只会发射4
         */
        observableList.add(Observable.range(1, 5).elementAt(3));
    }
}

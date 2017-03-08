package com.example.rxjava.operationalCharacter.FilterOperation;

import com.example.rxjava.operationalCharacter.BaseOperational;

import rx.Observable;

/**
 * Created by zhaoli on 2017/2/20.
 * IgnoreElements 操作符示例
 * 不发射任何数据，只发射Observable的终止通知[即只发出onCompleted、onError]
 */
public class IgnoreElements extends BaseOperational {
    @Override
    protected void create() {
        observableList.add(Observable.range(1, 10).ignoreElements());
    }
}

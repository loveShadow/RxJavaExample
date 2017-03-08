package com.example.rxjava.operationalCharacter.AuxiliaryOperation;

import com.example.rxjava.operationalCharacter.BaseOperational;

import rx.Observable;

/**
 * Created by zhaoli on 2017/3/4.
 * Timestamp 操作符
 * 给每一个原始Observable加上时间戳并重新发出
 */
public class Timestamp extends BaseOperational {
    @Override
    protected void create() {
        observableList.add(Observable.just("aaa", "bbb", "ccc")
            .timestamp());
    }
}

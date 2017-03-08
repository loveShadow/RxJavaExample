package com.example.rxjava.operationalCharacter.FilterOperation;

import com.example.rxjava.operationalCharacter.BaseOperational;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by zhaoli on 2017/2/20.
 * First 操作符示例
 * 只发射第一项（或者满足某个条件的第一项）数据
 */
public class First extends BaseOperational {
    @Override
    protected void create() {
        /**
         * 只发射第一项
         */
        observableList.add(Observable.range(1, 10).first());

        /**
         * 只发射满足条件的第一项
         */
        observableList.add(Observable.range(1, 10).first(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return (integer % 3 == 0);
            }
        }));
    }
}

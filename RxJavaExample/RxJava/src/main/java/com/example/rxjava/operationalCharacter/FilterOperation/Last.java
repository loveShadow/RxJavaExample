package com.example.rxjava.operationalCharacter.FilterOperation;

import com.example.rxjava.operationalCharacter.BaseOperational;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by zhaoli on 2017/2/20.
 * Last 操作符示例
 * 只发射第一项（或者满足某个条件的第一项）数据
 */
public class Last extends BaseOperational {
    @Override
    protected void create() {
        /**
         * 只发射最后一个
         */
        observableList.add(Observable.range(1, 10).last());

        /**
         * 只发射最后一个满足条件的
         */
        observableList.add(Observable.range(1, 10).last(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return (integer % 3 == 0);
            }
        }));
    }
}

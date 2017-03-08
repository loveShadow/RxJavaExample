package com.example.rxjava.operationalCharacter.TransformationOperation;

import com.example.rxjava.operationalCharacter.BaseOperational;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by zhaoli on 2017/2/19.
 * Map 操作符示例
 * 对Observable发射的每一项数据应用一个函数，执行变换操作
 * 重要：Map 和 FlatMap 的区别？
 *  Map是将发射的数据转换成另外一个数据
 *  FlatMap是将发射的数据转换成另外一个Observable，再由这个Observable把数据发射出去
 */
public class Map extends BaseOperational {
    @Override
    protected void create() {
        /**
         * 对每一个发射的数据进行转换
         */
        observableList.add(Observable.range(1, 5).map(new Func1<Integer, String>() {
            @Override
            public String call(Integer integer) {
                return "[转换数据] " + integer;
            }
        }));
    }
}

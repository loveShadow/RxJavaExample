package com.example.rxjava.operationalCharacter.ConditionsAndBooleanOperation;

import com.example.rxjava.operationalCharacter.BaseOperational;

import rx.Observable;
import rx.functions.Func2;

/**
 * Created by zhaoli on 2017/3/4.
 * SequenceEqual 操作符
 * 判断两个Observable发出的信号是否一致，可以自定义判断条件
 *      一致：true
 *      不一致：false
 */
public class SequenceEqual extends BaseOperational {
    @Override
    protected void create() {
        /**
         * 示例结果：
         *      D/LogUtils[SequenceEqual]: false
         *      D/LogUtils[SequenceEqual]: 完成了
         */
        observableList.add(Observable.sequenceEqual(
                Observable.just("aaa", "bbb"),
                Observable.just("ccc", "bbb")
        ));

        /**
         * 自定义判断条件
         * 示例结果：
         *      D/LogUtils[SequenceEqual]: true
         *      D/LogUtils[SequenceEqual]: 完成了
         */
        observableList.add(Observable.sequenceEqual(
                Observable.just("ccc", "ddd"),
                Observable.just("eee", "fff"),
                new Func2<String, String, Boolean>() {
                    @Override
                    public Boolean call(String s, String s2) {
                        return s.length() == s2.length();
                    }
                }
        ));
    }
}

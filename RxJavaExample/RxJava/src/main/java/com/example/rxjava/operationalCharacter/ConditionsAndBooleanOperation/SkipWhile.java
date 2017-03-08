package com.example.rxjava.operationalCharacter.ConditionsAndBooleanOperation;

import com.example.rxjava.operationalCharacter.BaseOperational;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by zhaoli on 2017/3/4.
 * SkipWhile 操作符
 * 原始Observable发出的信号第一个不满足【自定义条件】的信号之前的信号进行抛弃
 */
public class SkipWhile extends BaseOperational {
    @Override
    protected void create() {
        /**
         * 示例结果：
         *      D/LogUtils[SkipWhile]: hello
         *      D/LogUtils[SkipWhile]: new
         *      D/LogUtils[SkipWhile]: world
         *      D/LogUtils[SkipWhile]: 完成了
         */
        observableList.add(Observable.just("next", "abc", "hello", "new", "world").skipWhile(
                new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        return s.length() < 5;
                    }
                }
        ));
    }
}

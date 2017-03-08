package com.example.rxjava.operationalCharacter.ConditionsAndBooleanOperation;

import com.example.rxjava.operationalCharacter.BaseOperational;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by zhaoli on 2017/3/4.
 * TakeWhile 操作符
 * 只获取【第一次满足自定义条件】的原始Observable发出信号的之前所有信号
 */
public class TakeWhile extends BaseOperational {
    @Override
    protected void create() {
        /**
         * 示例结果：
         *      D/LogUtils[TakeWhile]: 完成了
         */
        observableList.add(Observable.just("hello", "new", "world").takeWhile(
                new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        return s.length() == 3;
                    }
                }
        ));
    }
}

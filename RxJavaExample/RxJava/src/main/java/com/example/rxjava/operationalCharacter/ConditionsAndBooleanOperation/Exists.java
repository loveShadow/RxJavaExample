package com.example.rxjava.operationalCharacter.ConditionsAndBooleanOperation;

import com.example.rxjava.operationalCharacter.BaseOperational;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by zhaoli on 2017/3/4.
 * Exists 操作符
 * 自定义判断条件判断原始Observable中是否存在该条件的信号
 *      存在：发射true
 *      不存在：发射false
 */
public class Exists extends BaseOperational {
    @Override
    protected void create() {
        /**
         * 示例结果：
         *      D/LogUtils[Exists]: true
         *      D/LogUtils[Exists]: 完成了
         */
        observableList.add(Observable.just("aaa", "bb").exists(
                new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        return s.length() == 3;
                    }
                }
        ));
    }
}

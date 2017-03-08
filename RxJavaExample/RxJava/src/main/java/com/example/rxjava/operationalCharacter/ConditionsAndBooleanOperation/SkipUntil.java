package com.example.rxjava.operationalCharacter.ConditionsAndBooleanOperation;

import com.example.rxjava.log.LogUtils;
import com.example.rxjava.operationalCharacter.BaseOperational;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by zhaoli on 2017/3/4.
 * SkipUntil 操作符
 * 原始Observable发出信号的时候，当第二个Observable发出信号时，原始Observable之前发出的进行丢弃
 */
public class SkipUntil extends BaseOperational {
    @Override
    protected void create() {
        /**
         * 示例结果：
         *      D/LogUtils[SkipUntil]: [消息] 3
         *          ...
         *      D/LogUtils[SkipUntil]: [消息] 10
         *      D/LogUtils[SkipUntil]: 完成了
         */
        observableList.add(Observable.range(1, 10).flatMap(
                new Func1<Integer, Observable<?>>() {
                    @Override
                    public Observable<?> call(Integer integer) {
//                        LogUtils.d(TAG, "[转换] " + integer);
                        //500ms一个信号
                        return Observable.just("[消息] " + integer).delay(integer * 500, TimeUnit.MILLISECONDS);
                    }
                }
        ).skipUntil(
                Observable.timer(1500, TimeUnit.MILLISECONDS)
        ));
    }
}

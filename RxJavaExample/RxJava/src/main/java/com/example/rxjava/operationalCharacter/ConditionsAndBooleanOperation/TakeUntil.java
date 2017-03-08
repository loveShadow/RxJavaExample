package com.example.rxjava.operationalCharacter.ConditionsAndBooleanOperation;

import com.example.rxjava.operationalCharacter.BaseOperational;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by zhaoli on 2017/3/4.
 * TakeUntil 操作符
 * 只获取【第一次满足自定义条件】或【第二个Observable发出信号】时原始Observable发出的信号
 * 【第一次满足自定义条件】的TakeUntil相当于TakeWhile
 */
public class TakeUntil extends BaseOperational {
    @Override
    protected void create() {
        /**
         * 示例：原始Observable发出的信号，只获取第二个Observable第一次发出信号时原始Observable发出的信号
         * 示例结果：
         *      D/LogUtils[TakeUntil]: 1
         *      ...
         *      D/LogUtils[TakeUntil]: 6
         *      D/LogUtils[TakeUntil]: 完成了
         */
        observableList.add(Observable.range(1, 10).flatMap(
                new Func1<Integer, Observable<?>>() {
                    @Override
                    public Observable<?> call(Integer integer) {
                        return Observable.just(integer).delay(integer * 200, TimeUnit.MILLISECONDS);
                    }
                }
        ).takeUntil(Observable.timer(1300, TimeUnit.MILLISECONDS)));

        /**
         * 示例：原始Observable发出的信号，只获取第一个字符串长度为3的信号之前的信号【包含不满足的信号】
         * 示例结果：
         *      D/LogUtils[TakeUntil]: hello
         *      D/LogUtils[TakeUntil]: new
         *      D/LogUtils[TakeUntil]: 完成了
         */
        observableList.add(Observable.just("hello", "new", "world", "!").takeUntil(
                new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        return s.length() == 3;
                    }
                }
        ));
    }
}

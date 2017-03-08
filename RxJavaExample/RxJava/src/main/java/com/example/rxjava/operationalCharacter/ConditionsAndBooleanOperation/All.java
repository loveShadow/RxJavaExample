package com.example.rxjava.operationalCharacter.ConditionsAndBooleanOperation;

import com.example.rxjava.operationalCharacter.BaseOperational;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by zhaoli on 2017/3/4.
 * All 操作符
 * 判断Observable发出的信号是否【都满足】这个条件
 */
public class All extends BaseOperational {
    @Override
    protected void create() {
        /**
         * @param predicate 判断条件
         * Observable<Boolean> all(Func1<? super T, Boolean> predicate)
         *
         * 示例：是否都小于10
         * 示例结果：
         *      D/LogUtils[All]: false
         *      D/LogUtils[All]: 完成了
         * [由于10等于10]
         */
        observableList.add(Observable.range(1, 10).all(
                new Func1<Integer, Boolean>() {
                    @Override
                    public Boolean call(Integer integer) {
                        return integer < 10;
                    }
                }
        ));
    }
}

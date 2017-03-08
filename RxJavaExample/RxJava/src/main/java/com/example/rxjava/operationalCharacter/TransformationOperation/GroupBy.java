package com.example.rxjava.operationalCharacter.TransformationOperation;

import com.example.rxjava.log.LogUtils;
import com.example.rxjava.operationalCharacter.BaseOperational;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.observables.GroupedObservable;

/**
 * Created by zhaoli on 2017/2/19.
 * GroupBy 操作符示例
 * 将一个Observable分拆为一些Observables集合，它们中的每一个发射原始Observable的一个子序列
 *
 */
public class GroupBy extends BaseOperational {
    @Override
    protected void create() {
        /**
         * 将[1, 10]按照奇偶数分组
         * 流程：将原始抛出信号的数据按照Func1的方法进行分组，并按照分组信息，每组由一个新的Observable发出
         * 例如：[1, 10] --- (1, 3, 5, 7, 9) 由一个发出 ---(2, 4, 6, 8, 10) 由另外一个发出
         */
        Observable.range(1, 10).groupBy(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                LogUtils.d(TAG, "[收到原始信号数据] integer = " + integer);
                return ((integer % 2) == 1);
            }
        }).subscribe(new Action1<GroupedObservable<Boolean, Integer>>() {
            @Override
            public void call(final GroupedObservable<Boolean, Integer> result) {
                result.subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        LogUtils.d(TAG, "[收到最终信号数据] integer = " + integer +
                                " keySet = " + result.getKey());
                    }
                });
            }
        });

        /**
         * 比上面多一个变化方法
         */
        Observable.range(1, 10).groupBy(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return ((integer % 2) == 1);
            }
        }, new Func1<Integer, String>() {
            @Override
            public String call(Integer integer) {
                return "[变换]" + integer;
            }
        }).subscribe(new Action1<GroupedObservable<Boolean, String>>() {
            @Override
            public void call(GroupedObservable<Boolean, String> result) {
                result.subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        LogUtils.d(TAG, "[收到最终信号数据] = " + s);
                    }
                });
            }
        });
    }
}

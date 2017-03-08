package com.example.rxjava.operationalCharacter.CombinedOperation;

import com.example.rxjava.operationalCharacter.BaseOperational;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by zhaoli on 2017/2/21.
 * StartWith 操作符示例
 * 在数据序列的开头插入一条指定的项、数组、发射数据的Observable
 */
public class StartWith extends BaseOperational {
    @Override
    protected void create() {
        /**
         * @param t1 在原始的Observable发射序列前插入[1-9]个数据项 或者列表
         * Observable<T> startWith(T t1)
         * Observable<T> startWith(Iterable<T> values)
         * @param values 插入一个序列
         * Observable<T> startWith(Observable<T> values)
         *
         * 执行结果："插入1" "adb" 4
         */
        observableList.add(Observable.just("adb", 4).startWith("插入1"));

        /**
         * 注意：原始Observable的数据类型决定了后面插入的数据类型
         * 插入的是一个整体
         * 执行结果：[2.3, 4.5] "adb" "ef" "hjk"
         */
        ArrayList<Float> floats = new ArrayList<>();
        floats.add(2.3f);
        floats.add(4.5f);
        observableList.add(Observable.from(new Object[] {"adb", "ef", "hjk"}).startWith(floats));

        /**
         * -10, -9, ... -6, 1, 2...
         */
        observableList.add(Observable.range(1, 5).startWith(Observable.range(-10, 5)));
    }
}

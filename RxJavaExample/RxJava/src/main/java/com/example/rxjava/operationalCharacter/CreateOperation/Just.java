package com.example.rxjava.operationalCharacter.CreateOperation;

import com.example.rxjava.operationalCharacter.BaseOperational;

import java.util.ArrayList;

import rx.Observable;

/**
 * Created by zhaoli on 2017/2/18.
 * Just 操作符示例
 * 创建一个发射指定值的Observable
 */
public class Just extends BaseOperational {
    @Override
    protected void create() {
        /**
         * 注：Just类似于From，但是From会将数组或Iterable的数据取出然后逐个发射，而Just只是简单的原样发射
         * 支持1-10个参数
         * 内部代码：
         * return from((T[])new Object[] { t1, t2, t3, t4, t5, t6 ...});
         */
        String[] data = new String[]{"a1", "a2"};
        observableList.add(Observable.just(1, "adb", new ArrayList<String>(),
                null, new Object(), data));
    }
}

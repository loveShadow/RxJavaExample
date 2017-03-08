package com.example.rxjava.operationalCharacter.CreateOperation;

import com.example.rxjava.operationalCharacter.BaseOperational;

import java.util.concurrent.TimeUnit;

import rx.Observable;


/**
 * Created by zhaoli on 2017/2/18.
 * Repeat操作符 示例
 * 创建一个发射特定数据重复多次的Observable
 * 执行线程：默认在trampoline调度器上执行
 */
public class Repeat extends BaseOperational {
    @Override
    protected void create() {
        /**
         * 注意：它不是创建一个Observable，而是重复发射原始Observable的数据序列
         * 无参数表示一直重复
         * Observable<T> repeat()
         *
         * @param count 重复次数
         * Observable<T> repeat(final long count)
         */
//        observableList.add(Observable.just(1, 2).repeat());
        /**
         * 重复一个无法停止的任务
         */
        observableList.add(Observable.interval(1, TimeUnit.SECONDS).repeat(2));
    }
}

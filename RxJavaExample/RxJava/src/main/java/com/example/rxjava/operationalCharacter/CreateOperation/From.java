package com.example.rxjava.operationalCharacter.CreateOperation;

import com.example.rxjava.operationalCharacter.BaseOperational;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.schedulers.Schedulers;

/**
 * Created by zhaoli on 2017/2/16.
 * From操作符示例
 * 将其它种类的对象和数据类型转换为Observable
 * 执行线程：默认不在任何调度器上执行
 */
public class From extends BaseOperational {

    @Override
    protected void create() {
        /**
         * 第一种：数组形式
         */
        String[] sItems = {"abc", "acd", "edf"};
        observableList.add(Observable.from(sItems));

        /**
         * 第二种：继承了迭代器接口(Iterable)的数组
         * 例如常见的：ArrayList
         */
        ArrayList<Object> stringList = new ArrayList<>();
        stringList.add("adb");
        stringList.add(3.4f);
        stringList.add(2);
        observableList.add(Observable.from(stringList));

        /**
         * 第三种：继承了(Future)的对象
         * 它会发射Future.get()方法返回的单个数据，总共有三个方法
         *
         * @param future
         * Observable<T> from(Future<? extends T> future)
         *
         * 设置一个特定的调度器
         * @param scheduler 调度器
         * Observable<T> from(Future<? extends T> future, Scheduler scheduler)
         *
         * 如果过了指定的时长Future没有返回一个值，就会发射错误信号并终止
         * @param timeout 超时时长
         * @param unit 时间单位
         * Observable<T> from(Future<? extends T> future, long timeout, TimeUnit unit)
         */
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        Future<Integer> future = threadPool.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {}
                return new Random().nextInt(100);
            }
        });
        observableList.add(Observable.from(future));
        observableList.add(Observable.from(future, 100, TimeUnit.MILLISECONDS));
        observableList.add(Observable.from(future, Schedulers.newThread()));
    }
}

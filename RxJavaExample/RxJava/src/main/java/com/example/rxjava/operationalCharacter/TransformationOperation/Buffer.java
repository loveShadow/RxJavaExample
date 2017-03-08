package com.example.rxjava.operationalCharacter.TransformationOperation;

import com.example.rxjava.operationalCharacter.BaseOperational;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func0;
import rx.schedulers.Schedulers;

/**
 * Created by zhaoli on 2017/2/19.
 * Buffer 操作符示例
 * 定期收集Observable的数据放进一个数据包裹，然后发射这些数据包裹，而不是一次发射一个值
 * 例如:
 *      原有的Observable------0--1--2--3--4--5--6------(一个一个发射)
 *              |
 *              |--Buffer-----0,1,2---4,5,6
 * 注意：如果原来的Observable发出一个onError的信号，Buffer会立即传递，而不是先把缓存的数据发射，即使之前的缓存内有数据
 */
public class Buffer extends BaseOperational {
    @Override
    protected void create() {
        /**
         * 根据数量来缓存
         * @param count 多少个数量一组再进行发射
         * Observable<List<T>> buffer(int count)
         *
         * @param skip 下次输出跳过skip个
         *             <count 会有重复的信号发出 [1,2][2,3]
         *             =count 和原来一样，没有变化[1,2][3,4]
         *             >count 会有被跳过的信号[1,2][5,6]
         * Observable<List<T>> buffer(int count, int skip)
         */
        observableList.add(Observable.range(1, 7).buffer(3));
        observableList.add(Observable.range(1, 7).buffer(2, 4));
        observableList.add(Observable.range(1, 7).buffer(2, 2));
        observableList.add(Observable.range(1, 7).buffer(2, 1));

        /**
         * 根据时间来缓存
         * @param timeSpan 缓存的时间
         * Observable<List<T>> buffer(long timeSpan, TimeUnit unit)
         *
         * @param count 缓存的数量 < count 依旧会发射
         * Observable<List<T>> buffer(long timeSpan, TimeUnit unit, int count)
         *
         * @param timeShift 创建缓存的时间(timeShift时间发射一次 timeSpan时间缓存的)
         *                  > timeSpan 会有遗漏的
         *                  < timeSpan 会有重复的数据
         *        1-2-3-4-5-6-7-8-9-10-11-12-13-------
         *        ------4-5-6-------10-11-------------
         *        1-2-3---3-4-5---
         * Observable<List<T>> buffer(long timeSpan, long timeShift, TimeUnit unit)
         */
//        observableList.add(Observable.interval(2, TimeUnit.SECONDS).buffer(3, TimeUnit.SECONDS));
//        observableList.add(Observable.interval(2, TimeUnit.SECONDS).buffer(3, TimeUnit.SECONDS, 5));
//        observableList.add(Observable.interval(1, TimeUnit.SECONDS).buffer(2, 5, TimeUnit.SECONDS));

        /**
         * 当开始收集数据时，会调用bufferClosingSelector生成第二个Observable，当第二个Observable发出信号时，会把缓存的数据发出去，
         * 并一直重复，直到原始的Observable执行完成
         *
         * Observable<List<T>> buffer(Func0<? extends Observable<? extends TClosing>> bufferClosingSelector)
         */
        observableList.add(Observable.interval(1, TimeUnit.SECONDS).buffer(new Func0<Observable<?>>() {
            @Override
            public Observable<?> call() {
                /**
                 * 返回一个2秒定时发出一个信号的Observable
                 */
                return Observable.interval(2, TimeUnit.SECONDS);
            }
        }));

        /**
         * 监视另外一个Observable，每当这个Observable发出一个信号，就会创建一个新的缓存
         *
         * <B> Observable<List<T>> buffer(Observable<B> boundary)
         *
         * @param initialCapacity 每一个缓冲区的初始化容量大小
         * <B> Observable<List<T>> buffer(Observable<B> boundary, int initialCapacity)
         */
        observableList.add(Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                if (subscriber.isUnsubscribed()) return;
                int count = 0;
                Random random = new Random(System.currentTimeMillis());
                while (count < 100) {
                    long sleepTime = random.nextInt(500);
                    try {
                        Thread.sleep(sleepTime);
                    } catch (Exception e){
                        subscriber.onError(e);
                    }
                    count ++;
                    subscriber.onNext("消息[" + count + "]");
                }
                subscriber.onCompleted();
            }
        }).buffer(Observable.interval(1, TimeUnit.SECONDS))
        //使用调度器，要不然会导致主线程卡住[为什么会导致主线程卡住? create是在默认调度器上执行]
        .subscribeOn(Schedulers.newThread()));
    }
}

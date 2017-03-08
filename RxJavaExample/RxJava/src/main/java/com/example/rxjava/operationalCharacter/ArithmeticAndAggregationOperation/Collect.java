package com.example.rxjava.operationalCharacter.ArithmeticAndAggregationOperation;

import com.example.rxjava.log.LogUtils;
import com.example.rxjava.operationalCharacter.BaseOperational;

import java.util.ArrayList;
import java.util.HashMap;

import rx.Observable;
import rx.functions.Action2;
import rx.functions.Func0;

/**
 * Created by zhaoli on 2017/3/4.
 * Collect 操作符
 * 将原始Observable发射的信号收集到一个数据结构中
 */
public class Collect extends BaseOperational {
    @Override
    protected void create() {
        /**
         *
         * <R> Observable<R> collect(Func0<R> stateFactory, final Action2<R, ? super T> collector)
         */
        observableList.add(Observable.just(1, 3, 5).collect(
                /**
                 * 创建一个数据结构
                 */
                new Func0<ArrayList<Integer>>() {
                    @Override
                    public ArrayList<Integer> call() {
                        return new ArrayList<>();
                    }
                },
                /**
                 * 把数据结构和原始Observable发射的信号，放到数据结构中
                 */
                new Action2<ArrayList<Integer>, Integer>() {
                    @Override
                    public void call(ArrayList<Integer> arrayList, Integer integer) {
                        arrayList.add(integer);
                    }
                }
        ));

        observableList.add(Observable.just("aaa", "bbb", 3.4f).collect(
                new Func0<HashMap<String, Object>>() {
                    @Override
                    public HashMap<String, Object> call() {
                        return new HashMap<>();
                    }
                },
                new Action2<HashMap<String, Object>, Object>() {
                    @Override
                    public void call(HashMap<String, Object> hashMap, Object object) {
                        hashMap.put("key_" + object, object);
                    }
                }
        ));
    }
}

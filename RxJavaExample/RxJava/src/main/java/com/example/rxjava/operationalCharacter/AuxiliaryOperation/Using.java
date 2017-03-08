package com.example.rxjava.operationalCharacter.AuxiliaryOperation;

import com.example.rxjava.log.LogUtils;
import com.example.rxjava.operationalCharacter.BaseOperational;

import java.util.HashMap;

import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;

/**
 * Created by zhaoli on 2017/3/4.
 * Using 操作符
 * 创建一个只在Observable生命周期存在的一次性资源
 */
public class Using extends BaseOperational {
    @Override
    protected void create() {
        observableList.add(Observable.using(
                /**
                 * 创建一次性资源的工厂函数
                 */
                new Func0() {
                    @Override
                    public Object call() {
                        LogUtils.d(TAG, "[创建资源]");
                        HashMap<String, Integer> custom = new HashMap<>();
                        custom.put("aaa", 10);
                        custom.put("bbb", 4);
                        custom.put("ccc", 7);
                        custom.put("ddd", 3);
                        return custom;
                    }
                },
                /**
                 * 用于创建Observable的工厂函数
                 */
                new Func1() {
                    @Override
                    public Object call(Object o) {
                        LogUtils.d(TAG, "[创建Observable] " + o);
                        final HashMap<String, Integer> custom = (HashMap<String, Integer>) o;
                        return Observable.from(custom.keySet()).scan(
                                new Func2<String, String, String>() {
                                    @Override
                                    public String call(String s, String s2) {
                                        LogUtils.d(TAG, "[Scan] s = " + s + " s2 = " + s2);
                                        return (custom.get(s) > custom.get(s2)) ? s : s2;
                                    }
                                }
                        ).last();
                    }
                },
                /**
                 * 用于释放资源的函数
                 */
                new Action1() {
                    @Override
                    public void call(Object o) {
                        //释放o
                        LogUtils.d(TAG, "[释放] " + o);
                    }
                }
        ));
    }
}

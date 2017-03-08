package com.example.rxjava.operationalCharacter;

import com.example.rxjava.log.LogUtils;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by zhaoli on 2017/2/18.
 */
public abstract class BaseOperational {

    final protected List<Observable> observableList = new ArrayList<>();
    final protected String TAG;

    public BaseOperational() {
        TAG = LogUtils.getTag(this.getClass());
    }

    public void startTest() {
        create();
        subscribeAll();
    }

    protected abstract void create();
    protected void subscribeAll() {
        for (Observable o : observableList) {
            subscribe(o);
        }
    }

    protected void subscribe(Observable observable) {
        observable.subscribe(new Action1() {
            @Override
            public void call(Object o) {
                LogUtils.d(TAG, o.toString());
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                LogUtils.d(TAG, "出异常了 -- " + throwable.getMessage());
            }
        }, new Action0() {
            @Override
            public void call() {
                LogUtils.d(TAG, "完成了");
            }
        });
    }
}

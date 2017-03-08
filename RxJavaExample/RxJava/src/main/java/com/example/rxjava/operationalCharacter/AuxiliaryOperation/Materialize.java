package com.example.rxjava.operationalCharacter.AuxiliaryOperation;

import com.example.rxjava.operationalCharacter.BaseOperational;

import java.util.ArrayList;

import rx.Notification;
import rx.Observable;

/**
 * Created by zhaoli on 2017/3/1.
 * Materialize 操作符      ----  将原始Observable发射的数据项、事件通知都当做数据Notification来重新发射
 * DeMaterialize 操作符    ----  将Notification转换为原始Observable发射的数据项、事件通知来重新发射
 */
public class Materialize extends BaseOperational {
    @Override
    protected void create() {
        observableList.add(Observable.just("aaa", "bbb").materialize());

        ArrayList<Notification> notifications = new ArrayList<>();
        notifications.add(0, Notification.createOnNext("ccc"));
        notifications.add(1, Notification.createOnNext("ddd"));
        notifications.add(2, Notification.createOnCompleted());
        observableList.add(Observable.from(notifications).dematerialize());
    }
}

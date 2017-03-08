package com.example.rxjava.operationalCharacter.StringOperation;

import com.example.rxjava.operationalCharacter.BaseOperational;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import rx.observables.StringObservable;

/**
 * Created by zhaoli on 2017/3/5.
 *  From 操作符
 *  将一个字符流或Reader转换为一个发射字节数组或字符串的Observable
 * 【注意】：不在RxJava的主模块中，在【RxJava-String】模块中
 */
public class From extends BaseOperational {
    @Override
    protected void create() {
        InputStream in = new ByteArrayInputStream("hello, new world!".getBytes());
        observableList.add(StringObservable.from(in));
    }
}

package com.example.rxjava.operationalCharacter.StringOperation;

import com.example.rxjava.log.LogUtils;
import com.example.rxjava.operationalCharacter.BaseOperational;

import java.util.Arrays;

import rx.Observable;
import rx.functions.Action1;
import rx.observables.StringObservable;

/**
 * Created by zhaoli on 2017/3/5.
 *  Encode 操作符
 *  将字符串解码，按字符边界发射数组
 * 【注意】：不在RxJava的主模块中，在【RxJava-String】模块中
 */
public class Encode extends BaseOperational {
    @Override
    protected void create() {
        String src = "你好";
        //示例结果：[-28, -67, -96, -27, -91, -67]
        StringObservable.encode(
                Observable.just(src),
                "UTF-8"
        ).subscribe(new Action1<byte[]>() {
            @Override
            public void call(byte[] bytes) {
                LogUtils.d(TAG, Arrays.toString(bytes));
            }
        });
    }
}

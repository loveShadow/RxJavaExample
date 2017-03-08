package com.example.rxjava.operationalCharacter.StringOperation;

import com.example.rxjava.operationalCharacter.BaseOperational;

import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

import rx.Observable;
import rx.observables.StringObservable;

/**
 * Created by zhaoli on 2017/3/5.
 * Decode 操作符
 * 将字符串编码，按字符边界发射数组
 * 【注意】：不在RxJava的主模块中，在【RxJava-String】模块中
 */
public class Decode extends BaseOperational {
    @Override
    protected void create() {
        byte[] src = new byte[]{-28, -67, -96, -27, -91, -67};
        /**
         * 示例结果：D/LogUtils[Decode]: 你好
         */
        /**
         * Observable<String> decode(Observable<byte[]> src, String charsetName)
         * Observable<String> decode(Observable<byte[]> src, Charset charset)
         * Observable<String> decode(final Observable<byte[]> src, final CharsetDecoder charsetDecoder)
         */
        observableList.add(StringObservable.decode(
                Observable.just(src),
                "UTF-8"
        ));

        observableList.add(StringObservable.decode(
                Observable.just(src),
                Charset.forName("UTF-8")
        ));

        observableList.add(StringObservable.decode(
                Observable.just(src),
                Charset.forName("UTF-8").newDecoder()
        ));
    }
}

package com.example.rxjava.operationalCharacter.TransformationOperation;

import com.example.rxjava.log.LogUtils;
import com.example.rxjava.operationalCharacter.BaseOperational;

import rx.Observable;
import rx.functions.Func2;

/**
 * Created by zhaoli on 2017/2/19.
 * Scan 操作符示例
 * 连续地对数据序列的每一项应用一个函数，然后连续发射结果
 * 流程：将原始发出的数据通过一个函数得到结果后连结果和下一个原始数据传递下去
 * 第一个数单独发射；第二次发送前两个数的运算后结果；第三为第二次运算的结果和第三个数做运算；后面类似
 */
public class Scan extends BaseOperational {
    @Override
    protected void create() {
        /**
         * -1-2-3-4-5-------
         * -1-3-6-10-15-----
         */
        observableList.add(Observable.range(1, 5).scan(new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) {
                LogUtils.d(TAG, "[上一个生成的数据] " + integer + " [新的原始数据] " + integer2);
                return integer + integer2;
            }
        }));

        /**
         * -1-2-3-4-5-----(初始数据100)
         * 100-99-97-94-90-85--------
         *
         * @param initialValue 用于第一次没有数据可以使用的情况
         * <R> Observable<R> scan(R initialValue, Func2<R, ? super T, R> accumulator)
         */
        observableList.add(Observable.range(1, 5).scan(100, new Func2<Integer, Integer, Integer>() {
            @Override
            public Integer call(Integer integer, Integer integer2) {
                LogUtils.d(TAG, "[上一个生成的数据] " + integer + " [新的原始数据] " + integer2);
                return integer - integer2;
            }
        }));
    }
}

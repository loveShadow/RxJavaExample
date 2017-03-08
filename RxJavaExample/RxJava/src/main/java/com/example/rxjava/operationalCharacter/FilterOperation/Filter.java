package com.example.rxjava.operationalCharacter.FilterOperation;

import com.example.rxjava.operationalCharacter.BaseOperational;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by zhaoli on 2017/2/19.
 * Filter 操作符示例
 * 只发射通过了谓词测试[过滤方法]的数据项
 */
public class Filter extends BaseOperational {
    @Override
    protected void create() {
        /**
         * 1-100的质数
         */
        observableList.add(Observable.range(1, 100).filter(new Func1<Integer, Boolean>() {
            @Override
            public Boolean call(Integer integer) {
                return isPrimeNumber(integer);
            }

            /**
             * 判断是否为质数
             * @param integer
             * @return
             */
            private boolean isPrimeNumber(Integer integer) {
                if (integer <= 2) {
                    return false;
                }
                for (int i = 2; i < integer; i ++) {
                    if (integer % i == 0) {
                        return false;
                    }
                }
                return true;
            }
        }));
    }
}

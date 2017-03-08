package com.example.rxjava.operationalCharacter.FilterOperation;

import com.example.rxjava.operationalCharacter.BaseOperational;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by zhaoli on 2017/2/19.
 * Distinct 操作符
 * 抑制（过滤掉）重复的数据项
 */
public class Distinct extends BaseOperational {
    @Override
    protected void create() {
        /**
         * 过滤掉重复的数据项
         * [0, 2, 3, 4, 5, 6]
         */
        observableList.add(Observable.just(0, 2, 2, 3, 2, 4, 4, 5, 6).distinct());

        /**
         * 过滤掉连续重复的数据项
         * [0, 2, 3, 2, 4, 5, 6]
         */
        observableList.add(Observable.just(0, 2, 2, 3, 2, 4, 4, 5, 6).distinctUntilChanged());

        /**
         * 根据原始数据生成key，根据key来判断是否重复
         * @param keySelector key的生成方法
         * <U> Observable<T> distinct(Func1<? super T, ? extends U> keySelector)
         */
        class House {
            public int id;
            public String name;

            public House(int id, String name) {
                this.id = id;
                this.name = name;
            }

            @Override
            public String toString() {
                return "House{" +
                        "id=" + id +
                        ", name='" + name + '\'' +
                        '}';
            }
        }
        List<House> house = new ArrayList<>();
        house.add(new House(1, "小房子"));
        house.add(new House(2, "房子"));
        house.add(new House(1, "大房子"));
        house.add(new House(3, "大房子"));
        house.add(new House(4, "平房"));
        house.add(new House(2, "土房"));
        observableList.add(Observable.from(house).distinct(new Func1<House, Object>() {
            @Override
            public Object call(House house) {
                return house.id;
            }
        }));
    }
}

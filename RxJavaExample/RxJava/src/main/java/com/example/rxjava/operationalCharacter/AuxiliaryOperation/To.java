package com.example.rxjava.operationalCharacter.AuxiliaryOperation;

import com.example.rxjava.log.LogUtils;
import com.example.rxjava.operationalCharacter.BaseOperational;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;

import rx.Observable;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.observables.BlockingObservable;

/**
 * Created by zhaoli on 2017/3/4.
 * [To]
 * [GetIterator]
 * [ToFuture]
 * [ToIterator]
 * [ToList]
 * [ToMap]
 * [ToMultiMap]
 * [ToSortedList]
 */
public class To extends BaseOperational {
    @Override
    protected void create() {
        observableList.add(Observable.just("aaa", "bbb").to(
                new Func1<Observable<String>, Observable>() {
                    @Override
                    public Observable call(Observable<String> stringObservable) {
                        LogUtils.d(TAG, "[转换] " + stringObservable);
                        return null;
                    }
                }
        ));

        Iterator<String> getIterator = BlockingObservable.from(Observable.just("abc", "def")).getIterator();
        while (getIterator.hasNext()) {
            LogUtils.d(TAG, "[getIterator] " + getIterator.next());
        }
        Iterable<String> getIterable = BlockingObservable.from(Observable.just("hik", "lmn")).toIterable();
        Iterator<String> iterator = getIterable.iterator();
        while (iterator.hasNext()) {
            LogUtils.d(TAG, "[toIterable] " + iterator.next());
        }
        Future<String> future = BlockingObservable.from(Observable.just("opq", "rst")).toFuture();

        /**
         * ToList
         * 将原始Observable转换成List并发出信号
         * 示例结果：
         *      D/LogUtils[To]: [ccc, ddd]
         *      D/LogUtils[To]: 完成了
         * Observable<List<T>> toList()
         */
        observableList.add(Observable.just("ccc", "ddd").toList());

        ToMap();

        ToMultiMap();

        ToSortedList();
    }

    /**
     *
     */
    private void ToMap() {
        /**
         * ToMap
         *
         * <K> Observable<Map<K, T>> toMap(Func1<? super T, ? extends K> keySelector)
         *
         * <K, V> Observable<Map<K, V>> toMap(Func1<? super T, ? extends K> keySelector,
         *                                    Func1<? super T, ? extends V> valueSelector)
         *
         * <K, V> Observable<Map<K, V>> toMap(Func1<? super T, ? extends K> keySelector,
         *                                    Func1<? super T, ? extends V> valueSelector,
         *                                    Func0<? extends Map<K, V>> mapFactory)
         */
        /**
         * 示例结果：
         *      D/LogUtils[To]: [ToMap][KeySelector] eee
         *      D/LogUtils[To]: [ToMap][KeySelector] fff
         *      D/LogUtils[To]: {key_fff=fff, key_eee=eee}
         *      D/LogUtils[To]: 完成了
         */
        observableList.add(Observable.just("eee", "fff").toMap(
                /**
                 * 转换成Map时Key的选择器
                 */
                new Func1<String, Object>() {
                    @Override
                    public Object call(String s) {
                        LogUtils.d(TAG, "[ToMap][KeySelector] " + s);
                        return "key_" + s;
                    }
                }
        ));
        /**
         * 示例结果：
         *      D/LogUtils[To]: [ToMap][KeySelector2] ggg
         *      D/LogUtils[To]: [ToMap][ValueSelector2] ggg
         *      D/LogUtils[To]: [ToMap][KeySelector2] hhh
         *      D/LogUtils[To]: [ToMap][ValueSelector2] hhh
         *      D/LogUtils[To]: {key2_ggg=value2_ggg, key2_hhh=value2_hhh}
         *      D/LogUtils[To]: 完成了
         */
        observableList.add(Observable.just("ggg", "hhh").toMap(
                new Func1<String, Object>() {
                    @Override
                    public Object call(String s) {
                        LogUtils.d(TAG, "[ToMap][KeySelector2] " + s);
                        return "key2_" + s;
                    }
                },
                /**
                 * 转换成Map时Value的选择器
                 */
                new Func1<String, Object>() {
                    @Override
                    public Object call(String s) {
                        LogUtils.d(TAG, "[ToMap][ValueSelector2] " + s);
                        return "value2_" + s;
                    }
                }
        ));
        /**
         * 示例结果：
         *      D/LogUtils[To]: [ToMap][MapFactory]
         *      D/LogUtils[To]: [ToMap][KeySelector3] iii
         *      D/LogUtils[To]: [ToMap][ValueSelector3] iii
         *      D/LogUtils[To]: [ToMap][KeySelector3] jjj
         *      D/LogUtils[To]: [ToMap][ValueSelector3] jjj
         *      D/LogUtils[To]: {test_1=t_1, key3_iii=value3_iii, key3_jjj=value3_jjj}
         *      D/LogUtils[To]: 完成了
         */
        observableList.add(Observable.just("iii", "jjj").toMap(
                new Func1<String, Object>() {
                    @Override
                    public Object call(String s) {
                        LogUtils.d(TAG, "[ToMap][KeySelector3] " + s);
                        return "key3_" + s;
                    }
                },
                new Func1<String, Object>() {
                    @Override
                    public Object call(String s) {
                        LogUtils.d(TAG, "[ToMap][ValueSelector3] " + s);
                        return "value3_" + s;
                    }
                },
                /**
                 * 将数据项转换到Map存储的值
                 */
                new Func0<Map<Object, Object>>() {
                    @Override
                    public Map<Object, Object> call() {
                        LogUtils.d(TAG, "[ToMap][MapFactory]");
                        Map<Object, Object> map = new HashMap<>();
                        map.put("test_1", "t_1");
                        return map;
                    }
                }
        ));
    }


    /**
     *
     */
    private void ToMultiMap() {
        /**
         * 类似ToMap，区别是ToMultiMap生成的Map同时还是一个ArrayList
         *
         * @param collectionFactory 返回Map中特定键Key的值Value
         * <K> Observable<Map<K, Collection<T>>> toMultimap(Func1<? super T, ? extends K> keySelector)
         * <K, V> Observable<Map<K, Collection<V>>> toMultimap(
         *                          Func1<? super T, ? extends K> keySelector,
         *                          Func1<? super T, ? extends V> valueSelector,
         *                          Func0<? extends Map<K, Collection<V>>> mapFactory,
         *                          Func1<? super K, ? extends Collection<V>> collectionFactory)
         */
        /**
         * 示例结果：
         *      D/LogUtils[To]: [ToMultiMap][KeySelector] kkk
         *      D/LogUtils[To]: [ToMultiMap][KeySelector] lll
         *      D/LogUtils[To]: {toMultiMap_Key_kkk=[kkk], toMultiMap_Key_lll=[lll]}
         *      D/LogUtils[To]: 完成了
         */
        observableList.add(Observable.just("kkk", "lll").toMultimap(
                new Func1<String, Object>() {
                    @Override
                    public Object call(String s) {
                        LogUtils.d(TAG, "[ToMultiMap][KeySelector] " + s);
                        return "toMultiMap_Key_" + s;
                    }
                }
        ));
        /**
         * D/LogUtils[To]: [ToMultiMap][MapFactory]
         * D/LogUtils[To]: [ToMultiMap][KeySelector1] mmm
         * D/LogUtils[To]: [ToMultiMap][ValueSelector1] mmm
         * D/LogUtils[To]: [ToMultiMap][CollectionFactory]
         * D/LogUtils[To]: [ToMultiMap][KeySelector1] nnn
         * D/LogUtils[To]: [ToMultiMap][ValueSelector1] nnn
         * D/LogUtils[To]: [ToMultiMap][CollectionFactory]
         * D/LogUtils[To]: {toMultiMap_Map_Key_1=[toMultiMap_Collection_1],
         *                  toMultiMap_Key1_nnn=[toMultiMap_Collection_2, toMultiMap_value1_nnn],
         *                  toMultiMap_Key1_mmm=[toMultiMap_Collection_2, toMultiMap_value1_mmm]}
         * D/LogUtils[To]: 完成了
         */
        observableList.add(Observable.just("mmm", "nnn").toMultimap(
                new Func1<String, Object>() {
                    @Override
                    public Object call(String s) {
                        LogUtils.d(TAG, "[ToMultiMap][KeySelector1] " + s);
                        return "toMultiMap_Key1_" + s;
                    }
                },
                new Func1<String, Object>() {
                    @Override
                    public Object call(String s) {
                        LogUtils.d(TAG, "[ToMultiMap][ValueSelector1] " + s);
                        return "toMultiMap_value1_" + s;
                    }
                },
                /**
                 * 给Map添加一项默认项
                 */
                new Func0<Map<Object, Collection<Object>>>() {
                    @Override
                    public Map<Object, Collection<Object>> call() {
                        LogUtils.d(TAG, "[ToMultiMap][MapFactory]");
                        Map<Object, Collection<Object>> map = new HashMap<>();
                        List<Object> list = new ArrayList<>();
                        list.add("toMultiMap_Collection_1");
                        map.put("toMultiMap_Map_Key_1", list);
                        return map;
                    }
                },
                /**
                 * 给Map的Value的列表添加一项默认项
                 */
                new Func1<Object, Collection<Object>>() {
                    @Override
                    public Collection<Object> call(Object o) {
                        LogUtils.d(TAG, "[ToMultiMap][CollectionFactory]");
                        List<Object> list = new ArrayList<>();
                        list.add("toMultiMap_Collection_2");
                        return list;
                    }
                }
        ));
    }

    /**
     *
     */
    private void ToSortedList() {
        /**
         * @param sortFunction 排序的方法（默认升序）
         * @param initialCapacity 累计ArrayList的初始容量
         * Observable<List<T>> toSortedList()
         * Observable<List<T>> toSortedList(int initialCapacity)
         * Observable<List<T>> toSortedList(Func2<? super T, ? super T, Integer> sortFunction)
         * Observable<List<T>> toSortedList(Func2<? super T, ? super T, Integer> sortFunction, int initialCapacity)
         */
        /**
         * 示例结果：
         *      D/LogUtils[To]: [ooo, pp]
         *      D/LogUtils[To]: 完成了
         */
        observableList.add(Observable.just("ooo", "pp").toSortedList());
        /**
         * 示例结果：
         *      D/LogUtils[To]: [rr, qqq]
         *      D/LogUtils[To]: 完成了
         */
        observableList.add(Observable.just("qqq", "rr").toSortedList(
                /**
                 * 排序方法
                 * @return 0 s == s2
                 *         1 s > s2
                 *        -1 s < s2
                 */
                new Func2<String, String, Integer>() {
                    @Override
                    public Integer call(String s, String s2) {
                        int length1 = (null == s) ? 0 : s.length();
                        int length2 = (null == s2) ? 0 : s2.length();
                        return length1 - length2;
                    }
                }
        ));
    }
}

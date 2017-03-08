package com.example.rxjava;

import android.app.Activity;
import android.os.Bundle;

import com.example.rxjava.operationalCharacter.ArithmeticAndAggregationOperation.Average;
import com.example.rxjava.operationalCharacter.ArithmeticAndAggregationOperation.Collect;
import com.example.rxjava.operationalCharacter.ArithmeticAndAggregationOperation.Concat;
import com.example.rxjava.operationalCharacter.ArithmeticAndAggregationOperation.Count;
import com.example.rxjava.operationalCharacter.ArithmeticAndAggregationOperation.Max;
import com.example.rxjava.operationalCharacter.ArithmeticAndAggregationOperation.Min;
import com.example.rxjava.operationalCharacter.ArithmeticAndAggregationOperation.Reduce;
import com.example.rxjava.operationalCharacter.ArithmeticAndAggregationOperation.Sum;
import com.example.rxjava.operationalCharacter.BaseOperational;

import com.example.rxjava.operationalCharacter.AuxiliaryOperation.Delay;
import com.example.rxjava.operationalCharacter.AuxiliaryOperation.Do;
import com.example.rxjava.operationalCharacter.AuxiliaryOperation.Materialize;
import com.example.rxjava.operationalCharacter.AuxiliaryOperation.ObserveOn;
import com.example.rxjava.operationalCharacter.AuxiliaryOperation.Serialize;
import com.example.rxjava.operationalCharacter.AuxiliaryOperation.SubscribeOn;
import com.example.rxjava.operationalCharacter.AuxiliaryOperation.TimeInterval;
import com.example.rxjava.operationalCharacter.AuxiliaryOperation.Timeout;
import com.example.rxjava.operationalCharacter.AuxiliaryOperation.Timestamp;
import com.example.rxjava.operationalCharacter.AuxiliaryOperation.To;
import com.example.rxjava.operationalCharacter.AuxiliaryOperation.Using;
import com.example.rxjava.operationalCharacter.CombinedOperation.CombineLatest;
import com.example.rxjava.operationalCharacter.CombinedOperation.Join;
import com.example.rxjava.operationalCharacter.CombinedOperation.Merge;
import com.example.rxjava.operationalCharacter.CombinedOperation.StartWith;
import com.example.rxjava.operationalCharacter.CombinedOperation.Zip;
import com.example.rxjava.operationalCharacter.ConditionsAndBooleanOperation.All;
import com.example.rxjava.operationalCharacter.ConditionsAndBooleanOperation.Amb;
import com.example.rxjava.operationalCharacter.ConditionsAndBooleanOperation.Contains;
import com.example.rxjava.operationalCharacter.ConditionsAndBooleanOperation.DefaultIfEmpty;
import com.example.rxjava.operationalCharacter.ConditionsAndBooleanOperation.Exists;
import com.example.rxjava.operationalCharacter.ConditionsAndBooleanOperation.IsEmpty;
import com.example.rxjava.operationalCharacter.ConditionsAndBooleanOperation.SequenceEqual;
import com.example.rxjava.operationalCharacter.ConditionsAndBooleanOperation.SkipUntil;
import com.example.rxjava.operationalCharacter.ConditionsAndBooleanOperation.SkipWhile;
import com.example.rxjava.operationalCharacter.ConditionsAndBooleanOperation.TakeUntil;
import com.example.rxjava.operationalCharacter.ConditionsAndBooleanOperation.TakeWhile;
import com.example.rxjava.operationalCharacter.CreateOperation.Create;
import com.example.rxjava.operationalCharacter.CreateOperation.From;
import com.example.rxjava.operationalCharacter.CreateOperation.Interval;
import com.example.rxjava.operationalCharacter.CreateOperation.Just;
import com.example.rxjava.operationalCharacter.CreateOperation.Range;
import com.example.rxjava.operationalCharacter.CreateOperation.Repeat;
import com.example.rxjava.operationalCharacter.CreateOperation.Timer;
import com.example.rxjava.operationalCharacter.ErrorOperation.Catch;
import com.example.rxjava.operationalCharacter.ErrorOperation.Retry;
import com.example.rxjava.operationalCharacter.FilterOperation.Debounce;
import com.example.rxjava.operationalCharacter.FilterOperation.Distinct;
import com.example.rxjava.operationalCharacter.FilterOperation.ElementAt;
import com.example.rxjava.operationalCharacter.FilterOperation.Filter;
import com.example.rxjava.operationalCharacter.FilterOperation.First;
import com.example.rxjava.operationalCharacter.FilterOperation.IgnoreElements;
import com.example.rxjava.operationalCharacter.FilterOperation.Last;
import com.example.rxjava.operationalCharacter.FilterOperation.Sample;
import com.example.rxjava.operationalCharacter.FilterOperation.Skip;
import com.example.rxjava.operationalCharacter.FilterOperation.SkipLast;
import com.example.rxjava.operationalCharacter.FilterOperation.Take;
import com.example.rxjava.operationalCharacter.FilterOperation.TakeLast;
import com.example.rxjava.operationalCharacter.StringOperation.ByLine;
import com.example.rxjava.operationalCharacter.StringOperation.Decode;
import com.example.rxjava.operationalCharacter.StringOperation.Encode;
import com.example.rxjava.operationalCharacter.StringOperation.Split;
import com.example.rxjava.operationalCharacter.StringOperation.StringConcat;
import com.example.rxjava.operationalCharacter.TransformationOperation.Buffer;
import com.example.rxjava.operationalCharacter.TransformationOperation.FlatMap;
import com.example.rxjava.operationalCharacter.TransformationOperation.GroupBy;
import com.example.rxjava.operationalCharacter.TransformationOperation.Map;
import com.example.rxjava.operationalCharacter.TransformationOperation.Scan;
import com.example.rxjava.operationalCharacter.TransformationOperation.Window;


/**
 * Created by zhaoli on 2017/2/16.
 */
public class MainActivity extends Activity {

    private Class[] classes = new Class[] {
            //创建操作符
            From.class,
            Interval.class,
            Just.class,
            Range.class,
            Repeat.class,
            Timer.class,
            Create.class,
            //转换操作符
            Buffer.class,
            FlatMap.class,
            GroupBy.class,
            Map.class,
            Scan.class,
            Window.class,
            //过滤操作符
            Debounce.class,
            Distinct.class,
            ElementAt.class,
            Filter.class,
            First.class,
            Last.class,
            IgnoreElements.class,
            Sample.class,
            Skip.class,
            SkipLast.class,
            Take.class,
            TakeLast.class,
            //合并操作符
            CombineLatest.class,
            Join.class,
            Merge.class,
            StartWith.class,
            Zip.class,
            //错误操作符
            Catch.class,
            Retry.class,
            //辅助操作符
            Delay.class,
            Do.class,
            Materialize.class,
            ObserveOn.class,
            Serialize.class,
            SubscribeOn.class,
            TimeInterval.class,
            Timeout.class,
            Timestamp.class,
            Using.class,
            To.class,
            //条件和布尔操作符
            All.class,
            Amb.class,
            Contains.class,
            DefaultIfEmpty.class,
            Exists.class,
            IsEmpty.class,
            SequenceEqual.class,
            SkipUntil.class,
            SkipWhile.class,
            TakeUntil.class,
            TakeWhile.class,
            //算数和聚合操作符
            //【RxJava-Math】
            Average.class,
            Max.class,
            Min.class,
            Sum.class,
            //【RxJava】
            Collect.class,
            Concat.class,
            Count.class,
            Reduce.class,
            //字符串操作符
            //【RxJava-String】
            ByLine.class,
            Decode.class,
            Encode.class,
            com.example.rxjava.operationalCharacter.StringOperation.From.class,
            com.example.rxjava.operationalCharacter.StringOperation.Join.class,
            Split.class,
            StringConcat.class
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        for (Class c : classes) {
            try {
                BaseOperational operational = (BaseOperational) c.newInstance();
                operational.startTest();
            } catch (Exception e){}
        }
    }
}

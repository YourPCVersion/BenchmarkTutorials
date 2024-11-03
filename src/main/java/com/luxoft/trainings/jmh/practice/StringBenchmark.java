package com.luxoft.trainings.jmh.practice;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.StringJoiner;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@Warmup(iterations = 5, time = 5, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 5, timeUnit = TimeUnit.SECONDS)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class StringBenchmark {
    private static final int size = 1_000;
    private static final String str = "string value|";

    private String[] strArray = new String[size];

    @Setup
    public void setup() {
        for (int i = 0; i < size; i++) {
            strArray[i] = str;
        }
    }

    @Benchmark
    public void stringBuffer(Blackhole blackhole) {
        StringBuffer result = new StringBuffer(size * str.length());
        for (int i = 0; i < size; i++) {
            result.append(strArray[i]);
        }
        blackhole.consume(result.toString());
    }

    @Benchmark
    public void stringBuilder(Blackhole blackhole) {
        StringBuilder result = new StringBuilder(size * str.length());
        for (int i = 0; i < size; i++) {
            result.append(strArray[i]);
        }
        blackhole.consume(result.toString());
    }

    @Benchmark
    public void stringJoiner(Blackhole blackhole) {
        StringJoiner result = new StringJoiner("");
        for (int i = 0; i < size; i++) {
            result.add(strArray[i]);
        }
        blackhole.consume(result.toString());
    }

    @Benchmark
    public void stringConcat(Blackhole blackhole) {
        String result = "";
        for (int i = 0; i < size; i++) {
            result += strArray[i];
        }
        blackhole.consume(result);
    }

    public static void main(String[] args) throws RunnerException {
        Options option = new OptionsBuilder()
                .include(StringBenchmark.class.getSimpleName())
                .build();

        new Runner(option).run();
    }
}

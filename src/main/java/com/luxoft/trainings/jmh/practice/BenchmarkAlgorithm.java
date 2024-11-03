package com.luxoft.trainings.jmh.practice;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@State(Scope.Benchmark)
@Warmup(iterations = 5, time = 1 , timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class BenchmarkAlgorithm {

    @Param({"10", "100", "1000", "10000"})
    private int arraySize;
    @Param({"ASC", "DESC", "RANDOM"})
    private ArrayType arrayType;
    private static final int arrayBorder = 10_000;

    private Integer[] startedArray;
    private Integer[]  sortedArray;

    @Setup
    public void setup() {
        startedArray = new Random().ints(arraySize, 0, arrayBorder)
                .boxed()
                .toArray(Integer[]::new);
        sortedArray = new Integer[arraySize];

        switch(arrayType) {
            case ASC:
                Arrays.sort(startedArray);
                break;
            case DESC:
                Arrays.sort(startedArray, Comparator.reverseOrder());
                break;
            case RANDOM:
                break;
        }
        System.out.printf("\nsrcArrayType: %s\nsrcArraySize: %d\nsrcArray: %s\n",
                arrayType, arraySize,  Arrays.toString(startedArray));
    }

    @Benchmark
    public void bubbleSort(Blackhole bh) {
        System.arraycopy(startedArray, 0, sortedArray, 0, arraySize);
        SortingAlgorithm.bubbleSort(startedArray);
        bh.consume(sortedArray);
    }
    @Benchmark
    public void quickSort(Blackhole bh) {
        System.arraycopy(startedArray, 0, sortedArray, 0, arraySize);
        SortingAlgorithm.quickSort(startedArray);
        bh.consume(sortedArray);
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
                .include(BenchmarkAlgorithm.class.getSimpleName())
                .build();
        new Runner(options).run();
    }

}

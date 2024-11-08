package com.luxoft.trainings.jmh;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.concurrent.TimeUnit;

// to remove warning in Java 9+ you can use -Djdk.module.illegalAccess=deny
@State(Scope.Thread)
public class _1_Baseline {

    long l;

    @Benchmark
    public void emptyMethod() {
        // Baseline for computing benchmark "payload"
        l++;
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder().include(_1_Baseline.class.getSimpleName())
                .warmupIterations(3)
                .warmupTime(TimeValue.seconds(3))
                .measurementIterations(3)
                .measurementTime(TimeValue.seconds(3))
                .mode(Mode.All)
                .timeUnit(TimeUnit.MILLISECONDS)
                .forks(2)
                .build();

        new Runner(opt).run();
    }
}

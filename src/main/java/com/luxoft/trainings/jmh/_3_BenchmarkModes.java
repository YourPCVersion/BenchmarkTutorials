package com.luxoft.trainings.jmh;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@Fork(1)
@Warmup(iterations = 1)
@Measurement(iterations = 1)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class _3_BenchmarkModes {

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public void measureThroughput() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(10);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    public void measureAvgTime() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(10);
    }

    @Benchmark
    @BenchmarkMode(Mode.SampleTime)
    public void measureSamples() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(10);
    }

    @Benchmark
    @BenchmarkMode(Mode.SingleShotTime)
    public void measureSingleShot() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(10);
    }

    @Benchmark
    @BenchmarkMode({Mode.Throughput, Mode.AverageTime, Mode.SampleTime, Mode.SingleShotTime})
    public void measureMultiple() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(10);
    }

    @Benchmark
    @BenchmarkMode(Mode.All)
    public void measureAll() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(10);
    }
}

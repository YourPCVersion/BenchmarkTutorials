/*
 * Copyright (c) 2014, Oracle America, Inc.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *  * Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 *
 *  * Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 *  * Neither the name of Oracle nor the names of its contributors may be used
 *    to endorse or promote products derived from this software without
 *    specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.luxoft.trainings.jmh;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

@Fork(1)
@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Warmup(iterations = 5, time = 10)
@Measurement(iterations = 1, time = 5)
public class _12_Loops {

    int x = 1;
    int y = 2;

    @Benchmark
    public int measureSingle() {
        return (x + y);
    }

    private int reps(int reps) {
        int result = 0;
        for (int i = 0; i < reps; i++) {
            result += (x + y);
        }
        return result;
    }

    @Benchmark
    @OperationsPerInvocation(1)
    public int measureLoop_1() {
        return reps(1);
    }

    @Benchmark
    @OperationsPerInvocation(10)
    public int measureLoop_10() {
        return reps(10);
    }

    @Benchmark
    @OperationsPerInvocation(100)
    public int measureLoop_100() {
        return reps(100);
    }

    @Benchmark
    @OperationsPerInvocation(1_000)
    public int measureLoop_1000() {
        return reps(1_000);
    }

    @Benchmark
    @OperationsPerInvocation(10_000)
    public int measureLoop_10000() {
        return reps(10_000);
    }

    @Benchmark
    @OperationsPerInvocation(100_000)
    public int measureLoop_100000() {
        return reps(100_000);
    }

    @Benchmark
    @OperationsPerInvocation(1000_000)
    public int measureLoop_1000000() {
        return reps(1000_000);
    }
}

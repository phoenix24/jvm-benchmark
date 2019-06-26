package org.jvmbenchmark.measure3;


import org.jvmbenchmark.Utils;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Threads(Threads.MAX)
@State(Scope.Benchmark)
public class LoopSum {

    @Param({"1395", "1396", "1397"})
    public int length;

    @Benchmark
    public void baseline() {
    }

    @Benchmark
    public int loopsum() {
        int result = 0;
        for (int i = 0; i < length; i++) {
            result += i;
        }
        return result;
    }

    @Benchmark
    public int formula1() {
        return (length * (length + 1)) >> 1;
    }

    @Benchmark
    public int formula2() {
        return (length * (length + 1)) / 2;
    }

    public static void main(String[] args) throws RunnerException {
        String report = "src/main/java/org/jvmbenchmark/measure3/" + Utils.getReportName();
        Options opt = new OptionsBuilder()
                .include(".*" + LoopSum.class.getSimpleName() + ".*")
                .forks(1)
                .resultFormat(ResultFormatType.CSV)
                .result(report)
                .build();
        new Runner(opt).run();
    }
}

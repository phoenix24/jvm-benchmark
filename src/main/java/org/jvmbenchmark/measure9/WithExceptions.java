package org.jvmbenchmark.measure9;

import org.jvmbenchmark.Utils;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@Threads(Threads.MAX)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class WithExceptions {

    @Param({"10", "100", "1000", "10000"})
    private int interval;

    @Benchmark
    public int loopWithExceptions() {
        int result = 0;
        for (int i = 0; i < 1_000_000; i++) {
            result += i;
            try {
                if (i % interval == 0) {
                    throw new RuntimeException("yoda!");
                }
            } catch (Exception x) {}
        }
        return result;
    }

    @Benchmark
    public int loopWithoutExceptions() {
        int result = 0;
        for (int i = 0; i < 1_000_000; i++) {
            result += i;
        }
        return result;
    }

    public static void main(String[] args) throws RunnerException {
        String report = "src/main/java/org/jvmbenchmark/measure9/" + Utils.getReportName();
        Options options = new OptionsBuilder()
                .include(".*" + WithExceptions.class.getSimpleName() + ".*" )
                .resultFormat(ResultFormatType.CSV)
                .result(report)
                .forks(1)
                .shouldDoGC(false)
                .build();
        new Runner(options).run();
    }
}

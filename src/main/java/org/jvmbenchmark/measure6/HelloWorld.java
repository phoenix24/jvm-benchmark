package org.jvmbenchmark.measure6;


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
@Threads(Threads.MAX)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class HelloWorld {

    private HelloWorld instance;

    @Setup
    public void setup() {
        instance = new HelloWorld();
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.INLINE)
    public int computeInline() {
        return instance.compute(0xcafebabe);
    }

    @Benchmark
    @CompilerControl(CompilerControl.Mode.DONT_INLINE)
    public int computeDoNotInline() {
        return instance.compute(0xcafebabe);
    }

    public int compute(int value) {
        return add(value / 0xdeadbeef);
    }

    public int add(int value) {
        return value + 254;
    }

    public static void main(String[] args) throws RunnerException {
        String report = "src/main/java/org/jvmbenchmark/measure6/" + Utils.getReportName();
        Options opts = new OptionsBuilder()
                .include(".*" + HelloWorld.class.getSimpleName() + ".*")
                .resultFormat(ResultFormatType.CSV)
                .forks(1)
                .result(report)
                .build();
        new Runner(opts).run();
    }
}

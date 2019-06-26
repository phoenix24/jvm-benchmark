package org.jvmbenchmark.measure4;


import org.jvmbenchmark.Utils;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@Threads(Threads.MAX)
@State(Scope.Benchmark)
public class StringConcat {

    int number = ThreadLocalRandom.current().nextInt(1000, 100000);
    int another0 = 1 << 10;
    final int another1 = 1 << 15;
    static int another2 = 1 << 20;
    static final int another3 = 1 << 30;

    @Benchmark
    public void baseline() {
    }

    @Benchmark
    public String concat00() {
        return "" + another0;
    }

    @Benchmark
    public String concat01() {
        return "" + another1;
    }

    @Benchmark
    public String concat02() {
        return "" + another2;
    }

    @Benchmark
    public String concat03() {
        return "" + another3;
    }

    @Benchmark
    public String concat11() {
        return "" + number;
    }

    @Benchmark
    public String concat12() {
        return Integer.toString(number);
    }

    @Benchmark
    public String concat13() {
        return new StringBuilder()
                .append(number)
                .toString();
    }

    public static void main(String[] args) throws RunnerException {
        String report = "src/main/java/org/jvmbenchmark/measure4/" + Utils.getReportName();
        Options opt = new OptionsBuilder()
                .include(".*" + StringConcat.class.getSimpleName() + ".*")
                .forks(3)
                .shouldDoGC(false)
                .result(report)
                .resultFormat(ResultFormatType.CSV)
                .build();
        new Runner(opt).run();
    }
}

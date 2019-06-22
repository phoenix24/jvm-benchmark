package org.jvmbenchmark.measure7;


import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@Threads(Threads.MAX)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class ToIntOrNotToInt {

    @Param({"100", "1000", "10000"})
    private int range;

    private int[] nums;
    private Integer num;

    @Setup
    public void setup() {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        nums = new int[1_000_000];
        for (int i = 0; i < nums.length; ++i) {
            nums[i] = random.nextInt(-range, range);
        }
    }

    @Benchmark
    public void noop() {}

    @Benchmark
    public void newInteger(Blackhole blackhole) {
        for (int i = 0; i < nums.length; i++) {
            blackhole.consume(new Integer(nums[i]));
        }
    }

    @Benchmark
    public void toIntegerValue(Blackhole blackhole) {
        for (int i = 0; i < nums.length; i++) {
            blackhole.consume(Integer.valueOf(nums[i]));
        }
    }

    @Benchmark
    public void toIntegerAuto(Blackhole blackhole) {
        for (int i = 0; i < nums.length; i++) {
            blackhole.consume(nums[i]);
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options opts = new OptionsBuilder()
                .include(".*" + ToIntOrNotToInt.class.getSimpleName() + ".*" )
                .forks(1)
                .shouldDoGC(false)
                .resultFormat(ResultFormatType.CSV)
                .result("src/main/java/org/jvmbenchmark/measure7/jmh-report.csv")
                .build();

        new Runner(opts).run();
    }
}

package org.jvmbenchmark.measure8;


import org.jvmbenchmark.Utils;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

@Warmup(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 5, time = 1, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@Threads(Threads.MAX)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
public class ToLogDebug {

    private static final Logger logger = LoggerFactory.getLogger(ToLogDebug.class);

    @Benchmark
    public void baseline() {
    }

    @Benchmark
    public void concatStrings() {
        String x = "", y = "", z = "";
        for (int i = 0; i < 1_000; i++) {
            x += i; y += i; z += i;
            logger.debug("Concatenating strings " + x + y + z);
        }
    }

    @Benchmark
    public void withoutIfDebug() {
        String x = "", y = "", z = "";
        for (int i = 0; i < 1_000; i++) {
            x += i; y += i; z += i;
            logger.debug("Variable arguments {} {} {}", x, y, z);
        }
    }

    @Benchmark
    public void withIfDebug() {
        String x = "", y = "", z = "";
        for (int i = 0; i < 1_000; i++) {
            x += i; y += i; z += i;
            if (logger.isDebugEnabled()) {
                logger.debug("If debug enabled {} {} {}", x, y, z);
            }
        }
    }

    public static void main(String[] args) throws RunnerException {
        String report = "src/main/java/org/jvmbenchmark/measure8/" + Utils.getReportName();
        Options options = new OptionsBuilder()
                .include(".*" + ToLogDebug.class.getSimpleName() + ".*" )
                .resultFormat(ResultFormatType.CSV)
                .result(report)
                .forks(1)
                .shouldDoGC(false)
                .build();
        new Runner(options).run();
    }
}

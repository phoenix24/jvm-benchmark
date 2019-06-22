package org.jvmbenchmark.measure1;

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
public class ArrayVsLinked {

    private static class Node {
        final int x;
        public Node(int x) {
            this.x = x;
        }
    }

    private static class LinkedNode {
        final int x;
        final LinkedNode next;

        public LinkedNode(int x, LinkedNode next) {
            this.x = x;
            this.next = next;
        }
    }

    @Param({"1", "100", "10000", "1000000"})
    private int size;

    int[] ints;
    Node[] nodes;
    LinkedNode root;

    @Setup
    public void setup() {
        ints = new int[size];
        nodes = new Node[size];
        LinkedNode linked = new LinkedNode(size, null);

        for (int c = 1; c < size; c++) { // minus first node
            linked = new LinkedNode(size - c, linked);
        }

        for (int c = 0; c < size; c++) {
            nodes[c] = new Node(c + 1);
        }

        for (int c = 0; c < size; c++) {
            ints[c] = c + 1;
        }

        root = linked;

        if (array() != linked()) {
            throw new IllegalStateException("Oops, tests return different results");
        }
    }

    @Benchmark
    public int ints() {
        int v = 0;
        for (int i = 0; i < ints.length; i++) {
            v += ints[i];
        }
        return v;
    }

    @Benchmark
    public int array() {
        int v = 0;
        for (Node n : nodes) {
            v += n.x;
        }
        return v;
    }

    @Benchmark
    public int linked() {
        int v = 0;
        LinkedNode n = root;
        do {
            v += n.x;
            n = n.next;
        } while (n != null);
        return v;
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(".*" + ArrayVsLinked.class.getSimpleName() + ".*")
                .resultFormat(ResultFormatType.CSV)
                .result("src/main/java/org/jvmbenchmark/measure1/jmh-report.csv")
                .shouldDoGC(false)
                .forks(1)
                .build();
        new Runner(opt).run();
    }
}

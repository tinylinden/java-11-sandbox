package eu.tinylinden;

import io.vavr.collection.Seq;
import io.vavr.collection.Stream;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;

import java.util.*;
import java.util.concurrent.TimeUnit;

import static io.vavr.API.*;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Benchmark)
@Fork(value = 1)
@Warmup(iterations = 1, time = 5)
@Measurement(iterations = 7, time = 5)
public class ForEachBenchmark {

    private final Map<String, Iterable<UUID>> cache = new HashMap<>();

    private void run(Iterable<UUID> iterable, Blackhole bh) {
        long n = Long.MAX_VALUE;
        for (var elem : iterable) {
            n = n % elem.getLeastSignificantBits();
        }
        bh.consume(n);
    }

    private Seq<UUID> vavrSeq(int size) {
        Seq<UUID> seq = Seq();
        for (int i = 0; i < size; i++) {
            seq = seq.append(UUID.randomUUID());
        }
        return seq;
    }

    private Stream<UUID> vavrStream(int size) {
        Stream<UUID> stream = Stream();
        for (int i = 0; i < size; i++) {
            stream = stream.append(UUID.randomUUID());
        }
        return stream;
    }

    private List<UUID> javaList(int size) {
        List<UUID> list = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            list.add(UUID.randomUUID());
        }
        return list;
    }

    @Benchmark
    public void vavrSeq_5_000(Blackhole bh) {
        run(cache.computeIfAbsent("vavrSeq_5_000", it -> vavrSeq(5_000)), bh);
    }

    @Benchmark
    public void vavrSeq_100_000(Blackhole bh) {
        run(cache.computeIfAbsent("vavrSeq_100_000", it -> vavrSeq(100_000)), bh);
    }

    @Benchmark
    public void vavrStream_5_000(Blackhole bh) {
        run(cache.computeIfAbsent("vavrStream_5_000", it -> vavrStream(5_000)), bh);
    }

    @Benchmark
    public void vavrStream_100_000(Blackhole bh) {
        run(cache.computeIfAbsent("vavrStream_100_000", it -> vavrStream(100_000)), bh);
    }

    @Benchmark
    public void javaList_5_000(Blackhole bh) {
        run(cache.computeIfAbsent("javaList_5_000", it -> javaList(5_000)), bh);
    }

    @Benchmark
    public void javaList_100_000(Blackhole bh) {
        run(cache.computeIfAbsent("javaList_100_000", it -> javaList(100_000)), bh);
    }
}

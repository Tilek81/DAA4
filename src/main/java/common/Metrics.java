package common;

public class Metrics {
    public long start, end;
    public long dfs = 0, relax = 0, queueOps = 0;

    public void start() { start = System.nanoTime(); }
    public void stop() { end = System.nanoTime(); }
    public long time() { return end - start; }
}

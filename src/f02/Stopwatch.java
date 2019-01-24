package f02;

public class Stopwatch {
    private long startTime;
    private long stopTime;

    public void start() {
        startTime = stopTime = System.currentTimeMillis();
    }

    public void stop() {
        stopTime = System.currentTimeMillis();
    }

    public long getMilliseconds() {
        return stopTime - startTime;
    }

    public double getSeconds() {
        return (stopTime - startTime) / 1000.0;
    }
}
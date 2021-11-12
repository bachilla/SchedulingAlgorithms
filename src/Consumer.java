//package Semaphores.boundedbuffer;

/**
 * This is the consumer thread for the bounded buffer problem.
 */
import java.util.*;

public class Consumer implements Runnable {

    private Buffer buffer;

    public Consumer(Buffer b) {
        this.buffer = b;
    }

    public void run() {

        while (true) {
            if (Statistics.stat_totalNrJobs > 30) {
                Statistics.end();
            }
            Job job = buffer.remove();
            double start = System.currentTimeMillis();
            SleepUtilities.nap(90);
            double end = System.currentTimeMillis();
            Statistics.updateStats(job, end, start);
        }
    }
}




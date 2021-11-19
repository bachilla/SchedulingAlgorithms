/**
 * Consumer.java
 *
 * This class runs as a thread that consumes jobs from the bounded buffer.
 */
public class Consumer implements Runnable {

    private Buffer buffer;

    public Consumer(Buffer b) {
        this.buffer = b;
    }


    /**
     * run method.
     *
     * while this thread runs, removes jobs from the buffer.
     * Job's statistics and other stats are then updated.
     *
     * Ends once 30 jobs have been ran.
     * */
    public void run() {

        while (true) {
            if (Statistics.stat_totalNrJobs > 99) {
                Factory.end_time = System.currentTimeMillis();
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




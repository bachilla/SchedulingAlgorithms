import java.util.concurrent.Semaphore;

/**
 * ScheduleServer.java
 *
 * This class runs as a thread that consumes jobs from the bounded buffer server.
 */
public class ScheduleServer implements Runnable {

    private Buffer buffer;
    private Semaphore mutex = new Semaphore(1);

    public ScheduleServer(Buffer b) {
        this.buffer = b;
    }

    /**
     * run method.
     *
     * while this thread runs, removes jobs from the buffer
     * and runs them in FIFO fashion, one at a time.
     * Job's statistics and other stats are then updated.
     *
     * Ends once # of jobs have been ran.
     * */
    public void run() {

        while (true) {
            Thread job = (Thread) buffer.remove();
            job.start();
        }
    }
}




import java.util.concurrent.Semaphore;

/**
 * ScheduleServer.java
 * <p>
 * This class runs as a thread that consumes jobs from the bounded buffer server.
 */
public class ScheduleServer implements Runnable {

    private Buffer buffer;

    public ScheduleServer(Buffer b) {
        this.buffer = b;
    }

    /**
     * run method.
     * <p>
     * while this thread runs, removes jobs from the buffer
     * and runs them in FIFO fashion, one at a time.
     * Job's statistics and other stats are then updated.
     * <p>
     * Runs until main thread is done sleeping.
     */
    public void run() {
        while (true) {
            Thread job = (Thread) buffer.remove();
            job.start();

        }
    }
}




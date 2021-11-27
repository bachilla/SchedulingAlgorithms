import java.util.ArrayDeque;
/**
 * RoundRobin class.
 *
 * This class inherits from the ScheduleServer class.
 *
 * Runs job for 10 ms before switching threads, until each job is completed.
 * */
public class RoundRobin extends ScheduleServer implements Runnable  {

    private Buffer buffer;
    private static final int TIME_SLICE = 10; // time (milliseconds) each job should run before switching context
    private ArrayDeque<Thread> stat_jobsInProgress = new ArrayDeque<>();

    public RoundRobin(Buffer b) {
        super(b);
        this.buffer = b;
    }

    /**
     * run method.
     *
     * while this thread runs, removes jobs from the buffer.
     * and processes them in RoundRobin fashion for 10 ms each before rotating jobs.
     * Job's statistics and other stats are then updated.
     *
     * Ends once # of jobs have been ran.
     * */
    @Override
    public void run()  {
        while (true) {
            // get the job from the buffer in place it in queue
            stat_jobsInProgress.addLast((Thread) buffer.remove());

            // get the next job from the queue
            Thread job = stat_jobsInProgress.removeFirst();

            // if the thread has not been started, start it
            if (job.getState().equals(Thread.State.NEW)) {
                job.start();
            }
            // if the thread is waiting, wake it up
            else if (job.getState().equals(Thread.State.TIMED_WAITING)) {
                job.notify();
            }

            try {
                this.wait(TIME_SLICE); // let the job thread run for the time slice
                job.wait(); // job thread waits until it is awoken by notify
                stat_jobsInProgress.addLast(job);
            } catch (Exception ex) {
            }
        }
    }
}


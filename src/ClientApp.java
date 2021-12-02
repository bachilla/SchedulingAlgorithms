import java.util.concurrent.Semaphore;

/**
 * ClientApp.java
 * <p>
 * This class runs as a thread that creates job threads and adds them into the BoundedBuffer.
 */
public class ClientApp implements Runnable {

    public String appType;
    private Buffer buffer;
    public double interArrivalTime;
    public double serviceTime;
    private Semaphore running;

    public ClientApp(Buffer b, String appType, double interArrivalTime, double serviceTime) {
        this.appType = appType;
        this.buffer = b;
        this.interArrivalTime = interArrivalTime;
        this.serviceTime = serviceTime;
        Statistics.stat_numThreads += 1;
    }

    /**
     * run method.
     * <p>
     * sleep for the interarrival time, then create and add a job to the bounded buffer.
     */
    public void run() {
        while (true) {
            SleepUtilities.napRandom((int) this.interArrivalTime);
            buffer.insert(new Thread(new Job(this.appType, this.serviceTime)));
        }
    }
}


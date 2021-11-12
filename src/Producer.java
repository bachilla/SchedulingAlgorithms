//package Semaphores.boundedbuffer;

/**
 * This is the producer thread for the bounded buffer problem.
 */
import java.util.*;

public class Producer implements Runnable {

    public String appType;
    private Buffer buffer;
    public double interArrivalTime;
    public double serviceTime;

    public Producer(Buffer b, String appType, double interArrivalTime, double serviceTime) {
        this.appType = appType;
        this.buffer = b;
        this.interArrivalTime = interArrivalTime;
        this.serviceTime = serviceTime;
    }

    public void run() {

        while (true) {
            SleepUtilities.nap((int) (interArrivalTime));
            buffer.insert(new Job());
        }
    }
}

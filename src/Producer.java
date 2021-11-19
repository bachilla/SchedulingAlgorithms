/**
 * Producer.java
 *
 * This class runs as a thread that creates job objects and adds them into the BoundedBuffer.
 * */
public class Producer implements Runnable {

    public String appType;
    private Buffer buffer;
    public int interArrivalTime;
    public double serviceTime;

    public Producer(Buffer b, String appType, int interArrivalTime, double serviceTime) {
        this.appType = appType;
        this.buffer = b;
        this.interArrivalTime = interArrivalTime;
        this.serviceTime = serviceTime;
    }
    /**
     * run method.
     *
     * sleep for the interarrival time, then create and add a job to the bounded buffer.
     * */
    public void run() {
        while (true) {
            SleepUtilities.nap(interArrivalTime);
            buffer.insert(new Job());
        }
    }
}

/**
 * Job class.
 *
 * Defines the jobs produced and consumed to/from the BoundedBuffer.
 *
 * It's data members are used to calculate statistics after each job is done running.
 * */
public class Job {

    private String appType;
    public double creationTime;
    public double completionTime;
    public double serviceTime;
    public double turnaroundTime;
    public double waitTime;
    public int schedulerID = 0;

    public Job() {
        ++this.schedulerID;
        this.creationTime = System.currentTimeMillis();
    }

}

/**
 * Job class.
 * <p>
 * Defines the job threads produced and consumed to/from the BoundedBuffer.
 * <p>
 * It's data members are used to calculate statistics after each job is done running.
 */
public class Job implements Runnable {

    private String appType;
    public double creationTime;
    public double completionTime;
    public double serviceTime;
    public double startTime;
    public double turnaroundTime;
    public double waitTime;
    public int schedulerID = 0;

    public Job(String appType) {
        this.creationTime = System.currentTimeMillis();
        ++this.schedulerID;
        this.appType = appType;
        if (this.appType.equals("IO")) {
            this.serviceTime = 4;
        }
        else {
            this.serviceTime = 90;
        }
    }

    /**
     * run method.
     *
     * While job thread runs, it naps for a specified or random amount of time.
     * Its data members and gloabl stats are updated at the end of its run.
     * The update happens outside of recorded time as to not skew results.
     * */
    public void run() {
        this.startTime = System.currentTimeMillis();
        SleepUtilities.nap((int) this.serviceTime);
        this.completionTime = System.currentTimeMillis();

        // update job-specific data members
        this.serviceTime = (this.completionTime - this.startTime);
        this.waitTime = (this.startTime - this.creationTime);
        this.turnaroundTime = (this.serviceTime + this.waitTime);

        // update global stats
        Statistics.updateStats(this);
    }
}

public class Job {

    private String appType;
    public double creationTime;
    public double completionTime;
    public double serviceTime;
    public double turnaroundTime;
    public double waitTime;
    public int schedulerID = 0;

    public Job() {
        schedulerID++;
        creationTime = System.currentTimeMillis();
    }




}

import java.util.ArrayList;

public class Statistics {

    public static ArrayList<Job> completedJobsBuffer = new ArrayList<>();

    public static int stat_totalNrJobs = 0;
    public static double stat_totalBusyTime;
    public static double stat_totalTurnTime;
    public static double stat_totalWaitTime;

    public static double max_service_time;
    public static double max_turnaround_time;
    public static double max_wait_time;

    public static double avg_service_time;
    public static double avg_turnaround_time;
    public static double avg_wait_time;

    public static double CPU_utilization;

    public static void calculateStats() {
        avg_service_time = (stat_totalBusyTime / stat_totalNrJobs);
        avg_turnaround_time = (stat_totalTurnTime / stat_totalNrJobs);
        avg_wait_time = (stat_totalWaitTime / stat_totalNrJobs);

        CPU_utilization = (stat_totalBusyTime / (Factory.end_time - Factory.start_time) * 100);
    }

    public static void updateStats(Job job, double end, double start) {
        stat_totalNrJobs++;
        Statistics.stat_totalBusyTime += job.serviceTime;
        Statistics.stat_totalTurnTime += job.turnaroundTime;
        Statistics.stat_totalWaitTime += job.waitTime;

        job.serviceTime = (end - start) / 100;
        job.waitTime = (start - job.creationTime) / 100;
        job.turnaroundTime = (job.serviceTime + job.waitTime) / 100;
        job.completionTime = System.currentTimeMillis();

        if (job.serviceTime > max_service_time) {
            max_service_time = job.serviceTime;
        }
        if (job.turnaroundTime > max_turnaround_time) {
            max_turnaround_time = job.turnaroundTime;
        }
        if (job.waitTime > max_wait_time) {
            max_wait_time = job.waitTime;
        }

    }

    public static void printStats() {

        System.out.println("Total number of jobs ran: " + stat_totalNrJobs);

        System.out.println("Average service time: " + avg_service_time  + " ms");
        System.out.println("Average turnaround time: " + avg_turnaround_time + " ms");
        System.out.println("Average wait time: " + avg_wait_time + " ms");

        System.out.println("Maximum service time: " + max_service_time + " ms");
        System.out.println("Maximum turnaround time: " + max_turnaround_time + " ms");
        System.out.println("Maximum wait time: " + max_wait_time + " ms");

        System.out.println("CPU utilization was: " + CPU_utilization + "%");
    }

    public static void end() {
        System.out.println(stat_totalNrJobs + " Jobs were completed. Printing statistics and ending program...\n");
        Factory.end_time = System.currentTimeMillis();
        calculateStats();
        printStats();
        System.exit(0);
    }
}




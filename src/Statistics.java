/**
 * Statistics.java
 *
 * This class keeps track of, updates, and prints statistics
 * pertaining to both individual jobs, and global
 * averages and max values.
 * */
public class Statistics {
    public static int stat_totalNrJobs = 0; // total number of jobs produced and consumed

    public static double stat_totalBusyTime; // total time jobs spent running
    public static double stat_totalTurnTime; // total turnaround time among jobs
    public static double stat_totalWaitTime; // total wait time among jobs

    public static double max_service_time; // highest time job spent running
    public static double max_turnaround_time; // highest time job spent in turnaround
    public static double max_wait_time; // highest time job spent waiting to run

    public static double avg_service_time;  // average time jobs spent running
    public static double avg_turnaround_time; // average time jobs spent in turnaround
    public static double avg_wait_time; // average time jobs spent waiting

    public static double CPU_utilization; // how much CPU was utilized throughout the runtime

    /**
     * updateStats method.
     *
     * This method updates the global statistics of the program.
     * It is called every time a job is completed/consumed.
     * */
    public static void updateStats(Job job) {
        ++stat_totalNrJobs; // update # of jobs ran

        // update totals
        Statistics.stat_totalBusyTime += job.serviceTime;
        Statistics.stat_totalTurnTime += job.turnaroundTime;
        Statistics.stat_totalWaitTime += job.waitTime;

        // update max values
        if (job.serviceTime > max_service_time) {
            max_service_time = job.serviceTime;
        }
        if (job.turnaroundTime > max_turnaround_time) {
            max_turnaround_time = job.turnaroundTime;
        }
        if (job.waitTime > max_wait_time) {
            max_wait_time = job.waitTime;
        }

        // update averages
        avg_service_time = (stat_totalBusyTime / stat_totalNrJobs);
        avg_turnaround_time = (stat_totalTurnTime / stat_totalNrJobs);
        avg_wait_time = (stat_totalWaitTime / stat_totalNrJobs);

        // update current 'end-time' here so CPU utilization may be continuously updated
        Factory.end_time = System.currentTimeMillis();

        // update CPU utilization
        CPU_utilization = (stat_totalBusyTime / (Factory.end_time - Factory.start_time) * 100);
    }

    /**
     * printStatsAndExit method.
     *
     * This method prints the current global statistics at the end of runtime.
     * Then ends the program.
     * It is only called at the end of runtime.
     * */
    public synchronized static void printStatsAndExit() {
        System.out.println("\n\n" + stat_totalNrJobs + " Jobs were completed. Printing statistics and ending program...\n");

        System.out.println("Average service time: " + String.format("%.2f", avg_service_time)  + " ms");
        System.out.println("Average turnaround time: " + String.format("%.2f",avg_turnaround_time) + " ms");
        System.out.println("Average wait time: " + String.format("%.2f",avg_wait_time) + " ms");

        System.out.println("Maximum service time: " + max_service_time + " ms");
        System.out.println("Maximum turnaround time: " + max_turnaround_time + " ms");
        System.out.println("Maximum wait time: " + max_wait_time + " ms");

        System.out.println("CPU utilization was: " + String.format("%.2f",CPU_utilization) + "%");

        System.exit(0);
    }
}




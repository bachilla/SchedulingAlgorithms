import java.util.Iterator;
import java.util.Set;

/**
 * Factory class.
 * <p>
 * This creates the buffer and the producer and consumer threads.
 */
public class Factory {

    public static double start_time; // start time of the program
    public static double end_time; // end time of the program
    // the BoundedBuffer where jobs are inserted and removed by producer & consumer.
    public static BoundedBuffer server = new BoundedBuffer();

    public static void main(String[] args) throws InterruptedException {
        /**
         * normally, I would only pass in the buffer and app type and set the interArrivalTime and service time
         * according to that. But passing them in here is easier for testing and documenting.
         * */
        Thread CPU_thread = new Thread(new ClientApp(server, "CPU", 50, 55));
        Thread IO_thread = new Thread(new ClientApp(server, "IO", 2, 4));

//      Thread SS_thread1 = new Thread(new ScheduleServer(server));
//      Thread SS_thread2 = new Thread(new ScheduleServer(server));
//      Thread SS_thread3 = new Thread(new ScheduleServer(server));
//      Thread SS_thread4 = new Thread(new ScheduleServer(server));

//      Thread RR_thread1 = new Thread(new RoundRobin(server));
//      Thread RR_thread2 = new Thread(new RoundRobin(server));
//      Thread RR_thread3 = new Thread(new RoundRobin(server));
//      Thread RR_thread4 = new Thread(new RoundRobin(server));

        CPU_thread.start();
        IO_thread.start();
//        SS_thread1.start();
//        SS_thread2.start();
//        SS_thread3.start();
//        SS_thread4.start();
//        RR_thread1.start();
//        RR_thread2.start();
//        RR_thread3.start();
//        RR_thread4.start();

        // get the start time and sleep while the other threads run. Then print statistics and exit.
        Factory.start_time = System.currentTimeMillis();
        Thread.sleep(10000);
        Statistics.printStatsAndExit();
    }
}
// 1) 1 Server Processor: CPU bound inter-arrival=100, service=55, I/O Bound inter-arrival time=10, service=4
// 2) 2 Server Processors: CPU bound inter-arrival=100, service=55, I/O Bound inter-arrival time=10, service=4
// 3) 2 Server Processors: CPU bound inter-arrival=50, service=55, I/O Bound inter-arrival time=2, service=4
// 4) 4 Server Processors:  CPU bound inter-arrival=50, service=55, I/O Bound inter-arrival time=2, service=4;

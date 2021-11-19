/**
 * This creates the buffer and the producer and consumer threads.
 *
 */
public class Factory {

    public static double start_time; // start time of the program
    public static double end_time; // end time of the program
    public static BoundedBuffer server; // the BoundedBuffer where jobs are inserted and removed by producer & consumer.

    public static void main(String[] args) {

        // get the start time
        start_time = System.currentTimeMillis();

        // initialize the 'server'
        server = new BoundedBuffer();

        // now create and start the producer and consumer threads
        Thread IO_thread = new Thread(new Producer(server, "IO", 100, 90));
        Thread CONS_thread = new Thread(new Consumer(server));
        IO_thread.start();
        CONS_thread.start();
    }
}

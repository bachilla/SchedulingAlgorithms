//package Semaphores.boundedbuffer;

import java.sql.SQLOutput;
import java.util.Scanner;

/**
 * This creates the buffer and the producer and consumer threads.
 *
 */
public class Factory {

    public static double start_time;
    public static double end_time;
    public static BoundedBuffer server;

    public static void main(String args[]) {

        // get the start time
        start_time = System.currentTimeMillis();

        // intialize the 'server'
        server = new BoundedBuffer();

        // now create the producer and consumer threads
        Thread IO_thread = new Thread(new Producer(server, "IO", 100, 90));
        Thread CONS_thread = new Thread(new Consumer(server));
        IO_thread.start();
        CONS_thread.start();
    }

    public void getUserArgs() {
        Scanner scanner = new Scanner(System.in);


    }
}

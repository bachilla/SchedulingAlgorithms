//package Semaphores.boundedbuffer;
import java.util.concurrent.Semaphore;

/**
 * BoundedBuffer.java
 *
 * This program implements the bounded buffer with semaphores.
 * Note that the use of count only serves to output whether
 * the buffer is empty of full.
 */
public class BoundedBuffer implements Buffer {

    private static final int BUFFER_SIZE = 5;
    private Semaphore mutex;
    private Semaphore empty;
    private Semaphore full;
    private int count;
    private int in, out;
    private Job[] buffer;

    public BoundedBuffer() {
        // buffer is initially empty
        count = 0;
        in = 0;
        out = 0;

        buffer = new Job[BUFFER_SIZE];
        mutex = new Semaphore(1);
        empty = new Semaphore(BUFFER_SIZE);
        full = new Semaphore(0);
    }

    // producer calls this method
    public void insert(Job item) {
        try {
            empty.acquire();
            mutex.acquire();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // add an item to the buffer
        ++count;
        buffer[in] = item;
        in = (in + 1) % BUFFER_SIZE;
        System.out.println("Added job to buffer... current buffer size: " + count);

        mutex.release();
        full.release();
    }

    // consumer calls this method
    public Job remove() {
        try {
            full.acquire();
            mutex.acquire();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // remove an item from the buffer
        --count;
        Job item = buffer[out];
        out = (out + 1) % BUFFER_SIZE;
        System.out.println("Removed job from buffer... current buffer size: " + count);

        mutex.release();
        empty.release();
        return item;
    }
}

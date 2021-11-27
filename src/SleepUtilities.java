/**
 * Utilities for causing a thread to sleep.
 * Note, we should be handling interrupted exceptions
 * but choose not to do so for code clarity.
 *
 */
public class SleepUtilities {

	/**
	 * Nap for specific amount of time
	 */
	public static void nap(int duration) {
        	try {Thread.sleep(duration);}
        	catch (InterruptedException e) {}
	}

	/**
	 * Nap for a randomized amount of time.
	 * */
	public static void napRandom(int sleepTime) {
		try {Thread.sleep((long) (sleepTime * -Math.log(Math.random()))); }
		catch (InterruptedException e) {}
	}
}

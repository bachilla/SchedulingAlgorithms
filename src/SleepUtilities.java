/**
 * Utilities for causing a thread to sleep.
 * Note, we should be handling interrupted exceptions
 * but choose not to do so for code clarity.
 *
 */
public class SleepUtilities {

	private static final int NAP_TIME = 5;

	/**
	 * Nap between zero and duration seconds.
	 */
	public static void nap(int duration) {
        	try {Thread.sleep(duration);}
        	catch (InterruptedException e) {e.printStackTrace();}
	}

	/**
	 * Nap between zero and NAP_TIME seconds.
	 */
	public static void nap() {
		nap(NAP_TIME);
	}
}

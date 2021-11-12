//package Semaphores.boundedbuffer;

 /**
 * An interface for buffers
 *
 */


public interface Buffer
{
	/**
	 * insert an item into the Buffer.
	 * Note this may be either a blocking
	 * or non-blocking operation.
	 */
	public abstract void insert(Job item);

	/**
	 * remove an item from the Buffer.
	 * Note this may be either a blocking
	 * or non-blocking operation.
     * @return
     */
	public abstract Job remove();
}

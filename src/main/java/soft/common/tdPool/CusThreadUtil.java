package soft.common.tdPool;

import java.lang.Thread.State;

public class CusThreadUtil {

	/**
	 * 中断线程
	 * 
	 * @param td
	 * @throws InterruptedException
	 * @throws Exception
	 */
	public static void interrupt(Thread td) {
		try {
			if (td != null && !State.TERMINATED.equals(td.getState())) {
				td.interrupt();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * 中断线程
	 * 
	 * @param td
	 * @throws InterruptedException
	 * @throws Exception
	 */
	public static void stopAndJoin(Thread td) throws InterruptedException {
		if (td != null && !State.TERMINATED.equals(td.getState())) {
			td.interrupt();
			td.join();
		}
	}

	@SuppressWarnings("deprecation")
	public static void stopThread(Thread td) throws Exception {
		if (td != null && !State.TERMINATED.equals(td.getState())) {
			try {
				td.stop();
				td.join();
			} catch (InterruptedException e) {
				throw new Exception("the thread is interrupted while waitting the thread end");
			}
		}
	}

}

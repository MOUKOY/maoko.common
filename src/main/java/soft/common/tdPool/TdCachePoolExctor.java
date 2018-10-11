package soft.common.tdPool;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 线程池[可重用]执行中心
 * 
 * @author fanpei
 * @version 创建时间：2016年9月26日 下午2:35:30
 */
public class TdCachePoolExctor {

	private ExecutorService threadSver;

	public ExecutorService getTdExeService() {
		return threadSver;
	}

	public TdCachePoolExctor() {
		threadSver = Executors.newCachedThreadPool();
	}

	/**
	 * 自定义线程名
	 * 
	 * @param poolName
	 */
	public TdCachePoolExctor(String poolName) {
		threadSver = Executors.newCachedThreadPool(new CusThreadFactory(poolName));
	}

	public void execute(Runnable commond) {
		threadSver.execute(commond);
	}

	public void submit(Runnable commond) {
		threadSver.submit(commond);
	}

	public <T> Future<T> submit(Callable<T> commond) {
		return threadSver.submit(commond);
	}

	public void executes(Collection<Runnable> tasks) {
		if (tasks != null && !tasks.isEmpty()) {
			for (Runnable r : tasks) {
				if (r != null && r instanceof Runnable) {
					threadSver.execute(r);
				} else
					continue;
			}
		}
	}

	public void executes(Iterable<Runnable> tasks) {
		Iterator<Runnable> it = tasks.iterator();
		while (it.hasNext()) {
			Runnable r = it.next();
			if (r != null && r instanceof Runnable) {
				threadSver.execute(r);
			} else
				continue;
		}
	}

	public void shutdownNow() {
		threadSver.shutdownNow();
	}
}

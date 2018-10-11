package soft.common.file;

import java.io.File;

import soft.common.StringUtil;
import soft.common.agorithm.HashUtil;

public class FileIDUtil {
	private static IdWorker idWorker;

	public static synchronized void init(long svrid) {

		idWorker = new IdWorker(svrid, 0);
	}

	/**
	 * 产生下一个文件id-Long
	 * 
	 * @return
	 */
	public static long getNextIdLong() {
		return idWorker.nextId();
	}

	/**
	 * 产生下一个文件id
	 * 
	 * @return
	 */
	public static String getNextId() {
		long id = getNextIdLong();
		return HashUtil.string32MD5(Long.toString(id));
	}

	/**
	 * 获取个人账户根目录id
	 * 
	 * @param userid
	 * @return
	 */
	public static String getAccontRootId(String userid) {
		return HashUtil.string32MD5(userid);
	}

	/**
	 * 是否是根目录
	 * 
	 * @param dir
	 * @return
	 */
	public static boolean isRootDir(String dir) {
		if (StringUtil.isStringNull(dir))
			throw new NullPointerException("判断路径是否为根目录时,路径为空");
		return File.pathSeparator.equals(dir) || File.separator.equals(dir);
	}

}

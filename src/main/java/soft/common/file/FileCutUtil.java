package soft.common.file;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 文件切割助手
 * 
 * @author fanpei
 *
 */
public class FileCutUtil {
	// static Logger log = LogAssist.getLoger(HashUtil.class);

	/**
	 * 获取指定文件块索引的文件数据
	 * 
	 * @param filesrc
	 *            文件地址
	 * @param fileIndex
	 *            文件索引分组号
	 * @param destfileSize
	 *            目标文件大小【分块文件大小】单位MB范围[1,10)
	 * @return 文件数据
	 * @throws Exception
	 */
	public static byte[] GetFileBlock(String filesrc, int fileIndex, int destfileSize) throws Exception {
		byte[] outbytes = null;

		if ("".equals(filesrc) || filesrc == null || destfileSize == 0 || fileIndex < 0 || destfileSize > 10) {
			throw new Exception("切割文件失败:传入参数有误");
		}

		long filesrcSize = getFileSize(filesrc);// 源文件的大小
		int destSize = 1024 * 1024 * destfileSize;// 目标文件的大小（分割后每个文件的大小）

		int number = (int) (filesrcSize / destSize);
		number = filesrcSize % destSize == 0 ? number : number + 1;// 分割后文件的数目

		InputStream in = null;// 输入字节流
		BufferedInputStream bis = null;// 输入缓冲流

		try {
			in = new FileInputStream(filesrc);
			bis = new BufferedInputStream(in);

			long skiplength = fileIndex * destSize;
			int length = 1024 * 1024 * destfileSize;
			outbytes = new byte[length];
			skipFully(bis, skiplength);

			int offset = 0;
			int numRead = 0;
			while (offset < length && (numRead = bis.read(outbytes, offset, length - offset)) > 0) {
				offset += numRead;

			}

			// 确保所有数据均被读取
			if (offset != length) {
				throw new IOException("Could not completely read file: " + filesrc);
			}

		} finally {
			// 关闭流
			try {
				if (bis != null)
					bis.close();
				if (in != null)
					in.close();
			} catch (IOException e) {
			}
		}

		return outbytes != null && outbytes.length > 0 ? outbytes : null;
	}

	/**
	 * 从流中读取数据
	 * 
	 * @param buffStream
	 *            输入流
	 * @param blockLength
	 *            切割文件每块大小
	 * @param readLength
	 *            将要读取读取大小
	 * @return
	 * @throws Exception
	 */
	public static byte[] GetFileBlock(BufferedInputStream buffStream, int blockLength, int readLength)
			throws Exception {
		byte[] outbuff = null;
		int offset = 0;// 实际读取长度
		readLength = readLength < blockLength ? readLength : blockLength;
		outbuff = new byte[readLength];
		// buffStream = (BufferedInputStream) skipFully(buffStream, skiplength);

		int numRead = 0;
		boolean IsReadStreamComplet = false;
		while (offset < readLength && (numRead = buffStream.read(outbuff, offset, readLength - offset)) > 0) {
			offset += numRead;
		}
		// 流已经读完
		if (numRead == -1) {
			IsReadStreamComplet = true;
		}

		// 确保所有数据均被读取
		if (!IsReadStreamComplet && offset != readLength) {
			throw new IOException("Could not completely read InputStream");
		}

		if (offset == readLength) {
			return outbuff;
		} else
			return null;
	}

	/**
	 * 获取文件大小
	 * 
	 * @param filesrc
	 *            源文件目录
	 * @return 文件大小 单位byte-字节
	 * @throws Exception
	 */
	public static long getFileSize(String filesrc) throws Exception {
		if ("".equals(filesrc) || filesrc == null) {
			throw new Exception("获取文件大小失败:传入参数有误");
		}
		File filesrcFile = new File(filesrc);// 源文件
		long filesrcSize = filesrcFile.length();// 源文件的大小
		return filesrcSize;
	}

	public static InputStream skipFully(InputStream in, long howMany) throws IOException {
		// IOUtils.skipFully(in, howMany);

		long remainning = howMany;
		while (remainning > 0) {
			long amt = in.skip(remainning);
			remainning -= amt;
		}
		return in;
	}
}

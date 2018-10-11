package soft.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collection;

import org.apache.commons.io.FileUtils;

/**
 * 文件编码转换（由一种转换为其他种）
 * 
 * @author fanpei
 *
 */
public class EncodingConverter extends StaticClass {

	/**
	 * 由一种编码转换为另一种编码
	 * 
	 * @param srcCode
	 *            源文件编码格式
	 * @param srcDir
	 *            源文件目录
	 * @param destCode
	 *            目标文件编码格式
	 * @param destDir
	 *            目标文件目录
	 * @throws IOException
	 */
	public static void convert(String srcCode, String srcDir, String destCode, String destDir) throws IOException {
		Collection<File> srcFileColl = null;
		int fileCount = 0;
		File srcFile = new File(srcDir);
		File destFile = new File(destDir);
		if (!destFile.exists())
			destFile.mkdirs();
		if (!srcFile.exists())
			throw new FileNotFoundException(srcDir);

		srcFileColl = FileUtils.listFiles(srcFile, new String[] { "java" }, true);

		if (!srcFileColl.isEmpty()) {
			int srcDirLen = srcDir.length();
			for (File file : srcFileColl) {
				fileCount++;
				File destFilePath = new File(destDir, file.getAbsolutePath().substring(srcDirLen));
				FileUtils.writeLines(destFilePath, destCode, FileUtils.readLines(file, srcCode));
			}
		}
		System.out.println("total convert files:" + Integer.toString(fileCount));
	}

	public static void main(String[] args) {
		// test
		try {
			EncodingConverter.convert("GBK", "D:\\CloudServer\\DataPlatformSDK4J\\src\\soft.net", "UTF-8",
					"D:\\MyPerson\\netPlat\\src\\com.net.frame\\src\\soft.net");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

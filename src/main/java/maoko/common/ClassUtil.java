package maoko.common;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 获取类助手
 * 
 * @author fanpei
 * @date 2018-09-18 21:10
 *
 */
public class ClassUtil extends StaticClass {

	// private static final IWriteLog log = new Log4j2Writer(ClassUtil.class);
	private static final String FILETER = ".class";
	private static final String PACKAGE_FILETER = ".";// 包分隔符

	/**
	 * 从包package中指定注解的获取单个的Class
	 * 
	 * @param packageClassPath 带包路径的类名
	 * @param anno             待匹配的注解，为空时忽略
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static Class<?> getSingleClass(String packageClassPath, Class<? extends Annotation> anno)
			throws ClassNotFoundException, NoSuchElementException {
		return matchAnno(packageClassPath, anno);
	}

	/**
	 * 从包package中获取指定注解的所有的Class-不递归
	 * 
	 * @param pack 包名
	 * @param anno 待匹配的注解，为空时忽略
	 * @return
	 * @throws ClassNotFoundException
	 */
	public static Set<Class<?>> getClasses(String pack, Class<? extends Annotation> anno)
			throws ClassNotFoundException {
		return getClasses(pack, anno, false);
	}

	/**
	 * 从包package中获取指定注解的所有的Class
	 * 
	 * @param packageName 包名
	 * @param anno        待匹配的注解，为空时忽略
	 * @param recursive   是否递归获取
	 * @return
	 * @throws ClassNotFoundException
	 */
	public static Set<Class<?>> getClasses(String packageName, Class<? extends Annotation> anno, boolean recursive)
			throws ClassNotFoundException {

		// 第一个class类的集合
		Set<Class<?>> classes = new LinkedHashSet<>();
		// String packageDirName = packageName.replace('.', '/');

		try {
			// 得到协议的名称

			// String protocol = AppRunPathUitl.getRunProtocol(ClassUtil.class);
			// dirs =
			// Thread.currentThread().getContextClassLoader().getResources(packageDirName);

//			String protocol = "jar";
//			URL urlJar = new File(
//					"E:\\study\\project\\electroniclock\\ElectronicLock\\releaseTmp\\gpslock_2019-01-08.jar").toURI()
//							.toURL();
//			URL[] urls = new URL[] { urlJar };
//			@SuppressWarnings("resource")
//			URLClassLoader loader = new URLClassLoader(urls);
//			dirs = loader.getResources(packageDirName);

			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			String packagePath = packageName.replace(".", "/");
			Enumeration<URL> dirs = loader.getResources(packagePath);

			// 循环迭代下去
			while (dirs.hasMoreElements()) {

				URL url = dirs.nextElement();
				String protocol = url.getProtocol();
				// 如果是以文件的形式保存在服务器上
				if ("file".equals(protocol)|| "rsrc".equals(protocol)) {
					// 获取包的物理路径
					String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
					findClassesInPackByFileAndAdd(packageName, filePath, anno, recursive, classes);
				} else if ("jar".equals(protocol)) {
					findClassesInPackByJarAndAdd(packagePath, url, anno, recursive, classes);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return classes;
	}

	/**
	 * 以文件的形式来获取包下的所有
	 *
	 * @param packageName
	 * @param packagePath
	 * @param fileter     筛选函数
	 * @param anno        指定注解
	 * @param recursive   是否递归
	 * @param classes     存储类列表
	 * @throws ClassNotFoundException
	 */
	public static void findClassesInPackByFileAndAdd(String packageName, String filePath,
			Class<? extends Annotation> anno, final boolean recursive, Set<Class<?>> classes)
			throws ClassNotFoundException {

		// 获取此包的目录 建立一个File
		File file = new File(filePath);
		// 如果不存在或者 也不是目录就直接返回
		if (!file.exists() || !file.isDirectory()) {
			System.err.println("用户定义包名 " + packageName + " 不存在或其下没有任何文件");
			return;
		}
		// 如果存在 就获取包下的所有文件
		File[] dirfiles = file.listFiles(new FileFilter() {
			// 自定义过滤规则 如果可以循环(包含子目录) 或则是以.class结尾的文件(编译好的java类文件)
			public boolean accept(File file) {
				return (recursive && file.isDirectory()) || (file.getName().endsWith(FILETER));
			}
		});
		// 循环所有文件
		for (File f : dirfiles) {
			String packFilePath = packageName + PACKAGE_FILETER + f.getName();
			// 如果是目录 则继续扫描
			if (f.isDirectory()) {
				findClassesInPackByFileAndAdd(packFilePath, f.getAbsolutePath(), anno, recursive, classes);
			} else {
				addToStore(packFilePath, anno, classes);
			}
		}
	}

	/**
	 * 扫描包类文件
	 * 
	 * @param packageDirName 包名
	 * @param url            文件地址url形式
	 * @param anno           指定注解
	 * @param recursive      是否递归
	 * @param classes        存储类列表
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	private static void findClassesInPackByJarAndAdd(String packageDirName, URL url, Class<? extends Annotation> anno,
			boolean recursive, Set<Class<?>> classes) throws ClassNotFoundException, IOException {
		// 获取jar
		JarFile jar = ((JarURLConnection) url.openConnection()).getJarFile();
		if (jar != null) {
			// 从此jar包 得到一个枚举类
			Enumeration<JarEntry> entries = jar.entries();
			// 同样的进行循环迭代
			while (entries.hasMoreElements()) {
				// 获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
				JarEntry entry = entries.nextElement();
				String name = entry.getName();
				if (name.endsWith(FILETER)) {
					// 如果是以/开头的
					if (name.charAt(0) == '/') {
						name = name.substring(1);// 获取后面的字符串
					}
					int idx = name.lastIndexOf('/');

					boolean goOn = false;
					if (!recursive) {// 排除掉其余包
						String parentDir = name.substring(0, idx);
						goOn = packageDirName.equals(parentDir);
					}
					if (goOn) {
						if (idx != -1) {
							// 如果是一个.class文件 而且不是目录
							if (!entry.isDirectory()) {
								name = name.replace("/", PACKAGE_FILETER);
								addToStore(name, anno, classes);
							}
						}
					}
				}
			}
		}
	}

	/**
	 * 添加到类仓库
	 * 
	 * @param fileClassPath 类的带包路径
	 * @param anno          指定注解
	 * @param classes       类存储仓库
	 * @throws ClassNotFoundException
	 */
	private static void addToStore(String fileClassPath, Class<? extends Annotation> anno, Set<Class<?>> classes)
			throws ClassNotFoundException {
		// 去掉后面的".class" 获取真正的类名
		String packageClassPath = fileClassPath.substring(0, fileClassPath.length() - FILETER.length());
		Class<?> clazz = matchAnno(packageClassPath, anno);
		if (null != clazz) {
			classes.add(clazz);
		}
	}

	/**
	 * 匹配注解
	 * 
	 * @param packageClassPath 指定类带包路径
	 * @param anno             指定注解
	 * @return
	 * @throws ClassNotFoundException
	 */
	private static Class<?> matchAnno(String packageClassPath, Class<? extends Annotation> anno)
			throws ClassNotFoundException {
		Class<?> clazz = Thread.currentThread().getContextClassLoader().loadClass(packageClassPath);
		if (anno != null && clazz.getAnnotation(anno) == null)
			clazz = null;
		return clazz;
	}

	/**
	 * 获取实现指定接口的类
	 * 
	 * @param clazz      指定接口
	 * @param classesAll 指定类集合
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Set<Class<?>> getByInterface(Class clazz, Set<Class<?>> classesAll) {
		Set<Class<?>> classes = new LinkedHashSet<Class<?>>();
		// 获取指定接口的实现类
		if (!clazz.isInterface()) {
			try {
				/**
				 * 循环判断路径下的所有类是否继承了指定类 并且排除父类自己
				 */
				Iterator<Class<?>> iterator = classesAll.iterator();
				while (iterator.hasNext()) {
					Class<?> cls = iterator.next();
					/**
					 * isAssignableFrom该方法的解析，请参考博客：
					 * http://blog.csdn.net/u010156024/article/details/44875195
					 */
					if (clazz.isAssignableFrom(cls)) {
						if (!clazz.equals(cls)) {// 自身并不加进去
							classes.add(cls);
						} else {

						}
					}
				}
			} catch (Exception e) {
				System.out.println("出现异常");
			}
		}
		return classes;
	}

}
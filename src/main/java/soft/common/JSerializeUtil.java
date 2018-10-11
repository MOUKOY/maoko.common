package soft.common;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * java 对象序内置列化助手
 * 
 * @author fanpei
 *
 */
public class JSerializeUtil extends StaticClass {

	public static Object byteToObject(byte[] bytes) {
		Object obj = null;
		ByteArrayInputStream bi = null;
		ObjectInputStream oi = null;
		try {
			// bytearray to object
			bi = new ByteArrayInputStream(bytes);
			oi = new ObjectInputStream(bi);
			obj = oi.readObject();
		} catch (IOException | ClassNotFoundException e) {
		} finally {
			try {
				if (bi != null)
					bi.close();
				if (oi != null)
					oi.close();
			} catch (IOException e) {
			}
		}

		return obj;
	}

	public static byte[] objectToByte(java.lang.Object obj) {
		byte[] bytes = null;
		ByteArrayOutputStream bo = null;
		ObjectOutputStream oo = null;
		try {
			// object to bytearray
			bo = new ByteArrayOutputStream();
			oo = new ObjectOutputStream(bo);
			oo.writeObject(obj);

			bytes = bo.toByteArray();

		} catch (IOException e) {
		} finally {
			try {
				if (bo != null)
					bo.close();
				if (oo != null)
					oo.close();
			} catch (IOException e) {
			}

		}

		return bytes;
	}

}

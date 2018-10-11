package soft.common;

import java.util.Collection;

public class NullObjUtil extends StaticClass {

	@SuppressWarnings("rawtypes")
	public static boolean notEmpty(Object o) {
		boolean notEmpty = false;
		if (o instanceof String) {
			String s = (String) o;
			if (!"".equals(s) && !"undefined".equals(s) && !"null".equals(s)) {
				notEmpty = true;
			}
		} else if (o instanceof Collection) {
			Collection c = (Collection) o;
			if (!c.isEmpty()) {
				notEmpty = true;
			}
		} else if (o instanceof Object[]) {
			Object[] arr = (Object[]) o;
			if (arr.length > 0) {
				notEmpty = true;
			}
		} else if (o != null) {
			return true;
		}
		return notEmpty;
	}

}

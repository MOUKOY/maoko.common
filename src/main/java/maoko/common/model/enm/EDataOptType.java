package maoko.common.model.enm;


/**
 * 数据操作类型
 * 
 * @author fanpei
 * @version 创建时间：2016年11月7日 下午8:25:20
 */
public enum EDataOptType {
	Add((byte) 0x00), Delete((byte) 0x01), Update((byte) 0x02);
	private byte nCode;

	private EDataOptType(byte _nCode) {
		this.nCode = _nCode;
	}

	public byte getnCode() {
		return nCode;
	}

	public static EDataOptType getEnum(String code) {
		switch (code) {
		case "0":
			return Add;
		case "1":
			return Delete;
		case "2":
			return Update;

		default:
			return Update;
		}
	}

	@Override
	public String toString() {
		return Byte.toString(nCode);
	}
}

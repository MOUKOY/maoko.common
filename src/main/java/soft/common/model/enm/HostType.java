package soft.common.model.enm;

/**
 * 主机类型
 * 
 * @author fanpei
 *
 * @date 2016年7月12日下午2:24:08
 */
public enum HostType {
	MASTER((byte) 0), SLAVE((byte) 1);

	private byte code;

	public byte getValue() {
		return code;
	}

	private HostType(byte _code) {
		this.code = _code;
	}

}

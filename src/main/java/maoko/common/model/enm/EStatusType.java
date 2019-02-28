
package maoko.common.model.enm;

/**
 * 状态类型
 * 
 * @author fanpei
 * @version 创建时间：2016年9月22日 下午2:18:23
 */
public enum EStatusType{

	/**
	 * 正常
	 */
	Normal((byte) 0),

	/**
	 * 异常，错误
	 */
	Error((byte) 1),

	/**
	 * 故障
	 */
	Fault((byte) 2);

	private byte nCode;

	private EStatusType(byte _nCode) {
		this.nCode = _nCode;
	}

	public byte getValue() {
		return nCode;
	}
}

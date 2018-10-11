package soft.common.model.enm;



/**
 * 命令执行结果
 * 
 * @author fanpei
 *
 *
 *
 *
 */
public enum CmdReult {
	Defeat((byte) 0), // 执行失败
	Success((byte) 1);// 执行成功

	private byte nCode;

	private CmdReult(byte _nCode) {
		this.nCode = _nCode;
	}

	public byte GetValue() {
		return nCode;
	}

	public static CmdReult GetValues(byte result) throws Exception {
		switch (result) {
		case (short) 0:

			return CmdReult.Defeat;
		case (short) 1:
			return CmdReult.Success;
		default:
			throw new Exception("命令执行结果值错误，超出枚举值定义范围");
		}

	}
}

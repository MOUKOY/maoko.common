package maoko.common.model.net;

import maoko.common.model.enm.EHostType;

/**
 *
 * @author fanpei
 * @version 创建时间：2016年9月21日 下午6:03:28
 */
public class CusHostAndPort {

	/**
	 * 是否时长连接
	 */
	private boolean longConect;

	// 服务器IP
	private String IP;

	// 端口号
	private int Port;

	// 通道是否建立 连接状态
	private boolean isConnected;

	// 主机类型
	private EHostType type;

	public CusHostAndPort() {
		type = EHostType.MASTER;
	}

	/**
	 * 
	 * @param longConect 是否是长连接
	 * @param ip
	 * @param port
	 */
	public CusHostAndPort(boolean longConect, String ip, int port) {
		this.longConect = longConect;
		this.type = EHostType.MASTER;
		this.IP = ip;
		this.Port = port;
	}

	/**
	 * 短连接端口创建
	 * 
	 * @param ip
	 * @param port
	 */
	public CusHostAndPort(String ip, int port) {
		this.longConect = false;
		this.type = EHostType.MASTER;
		this.IP = ip;
		this.Port = port;
	}

	/**
	 * @param _type 主机类型
	 * @param ip
	 * @param port
	 */
	public CusHostAndPort(EHostType _type, String ip, int port) {
		longConect = false;
		type = _type;
		IP = ip;
		Port = port;
	}

	/**
	 * 获取ip地址字符串
	 * 
	 * @return
	 */
	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public int getPort() {
		return Port;
	}

	public void setPort(int port) {
		Port = port;
	}

	public boolean isConnected() {
		return isConnected;
	}

	public void setConnected(boolean isConnected) {
		this.isConnected = isConnected;
	}

	public boolean isKeepMap() {
		return longConect;
	}

	public void setKeepMap(boolean keepMap) {
		longConect = keepMap;
	}

	public EHostType getType() {
		return type;
	}

	public void setType(EHostType type) {
		this.type = type;
	}

	/**
	 * 获取ip和端口字符串
	 * 
	 * @return
	 */
	public String getAddrStr() {
		return String.format("%s:%d", IP, Port);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;

		if (this.getClass() != obj.getClass())
			return false;

		CusHostAndPort other = (CusHostAndPort) obj;
		if (this.IP.equals(other.IP) && this.Port == other.getPort() && this.type == other.type)
			return true;
		else
			return false;
	}

	@Override
	public int hashCode() {

		int result = 1;
		long[] elements = new long[] { this.Port, this.type.getValue() };
		for (int i = 0; i < elements.length; i++) {
			int elmentHash = (int) (elements[i] ^ (elements[i] >>> 32));
			result = 31 * result + elmentHash;
		}
		return result;
	}

}

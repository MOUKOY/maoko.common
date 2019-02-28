package maoko.common;


/**
 * @author zoushuai
 * @version V1.0
 * @Title OrderCode2ChilddatasUtil
 * @Package maoko.common
 * @Description: 指令码to子功能码Util类
 * @date 2019年1月18日18:19:48
 */
public class OrderCode2ChilddatasUtil {

	/**
	 * 将short型的指令回码转为需要回复形式的子功能码格式
	 * @param orderCode
	 * @return
	 */
	public static byte[] formatOrderCode2NeedReturnChilddatas(short orderCode){
		byte[] bchilddatas = new byte[3];
		bchilddatas[0]=0x02;
		bchilddatas[1]=BConvrtUtil.shortToByte(orderCode)[0];
		bchilddatas[2]=BConvrtUtil.shortToByte(orderCode)[1];
		return bchilddatas;
	}
	
	/**
	 * 将byte[]、需要回复形式的子功能码 转为short型的指令回码
	 * @param childdatas
	 * @return
	 */
	public static short formatReturnChilddatas2OrderCode(byte[] childdatas){
		byte[] orderCode_bytes = new byte[2];
		orderCode_bytes[0] = childdatas[1];
		orderCode_bytes[1] = childdatas[2]; 
		short orderCode = BConvrtUtil.byteToShort(orderCode_bytes);
		return orderCode;
	}
	
	/**
	 * 将施封解封操作的childdatas去掉固定的前两位0x0381,转为short型的指令回码
	 * @param childdatas
	 * @return
	 */
	public static short formatSealUnSealChilddatas(byte[] childdatas){
		byte[] orderCode_bytes = new byte[2];
		orderCode_bytes[0] = childdatas[2];
		orderCode_bytes[1] = childdatas[3];
		short orderCode = BConvrtUtil.byteToShort(orderCode_bytes);
		return orderCode;
	}
	
}

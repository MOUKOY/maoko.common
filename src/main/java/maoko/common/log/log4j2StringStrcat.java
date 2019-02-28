package maoko.common.log;

import org.apache.logging.log4j.message.Message;
import org.apache.logging.log4j.message.MessageFactory;
import org.apache.logging.log4j.message.MessageFactory2;
import org.apache.logging.log4j.message.ParameterizedMessageFactory;
import org.apache.logging.log4j.message.ReusableMessageFactory;
import org.apache.logging.log4j.spi.MessageFactory2Adapter;
import org.apache.logging.log4j.util.Constants;
import org.apache.logging.log4j.util.LoaderUtil;
import org.apache.logging.log4j.util.PropertiesUtil;

import maoko.common.StaticClass;

/**
 * log4j2字符串拼接助手
 * 
 * @author fanpei
 *
 */
public final class log4j2StringStrcat extends StaticClass {

	private static MessageFactory2 messageFactory;
	/**
	 * The default MessageFactory class.
	 */
	private static final Class<? extends MessageFactory> DEFAULT_MESSAGE_FACTORY_CLASS;
	static {
		DEFAULT_MESSAGE_FACTORY_CLASS = createClassForProperty("log4j2.messageFactory", ReusableMessageFactory.class,
				ParameterizedMessageFactory.class);
		messageFactory = createDefaultMessageFactory();
	}

	private log4j2StringStrcat() {
	}

	public static Message getMsg(String message, Object... params) {
		checkinit();
		return messageFactory.newMessage(message, params);
	}

	public static String getMsgStr(String message, Object... params) {
		checkinit();
		return messageFactory.newMessage(message, params).getFormattedMessage();
	}

	private static MessageFactory2 createDefaultMessageFactory() {
		try {
			final MessageFactory result = DEFAULT_MESSAGE_FACTORY_CLASS.newInstance();
			return narrow(result);
		} catch (final InstantiationException | IllegalAccessException e) {
			throw new IllegalStateException(e);
		}
	}

	private static Class<? extends MessageFactory> createClassForProperty(final String property,
			final Class<ReusableMessageFactory> reusableParameterizedMessageFactoryClass,
			final Class<ParameterizedMessageFactory> parameterizedMessageFactoryClass) {
		try {
			final String fallback = Constants.ENABLE_THREADLOCALS ? reusableParameterizedMessageFactoryClass.getName()
					: parameterizedMessageFactoryClass.getName();
			final String clsName = PropertiesUtil.getProperties().getStringProperty(property, fallback);
			return LoaderUtil.loadClass(clsName).asSubclass(MessageFactory.class);
		} catch (final Throwable t) {
			return parameterizedMessageFactoryClass;
		}
	}

	private static MessageFactory2 narrow(final MessageFactory result) {
		if (result instanceof MessageFactory2) {
			return (MessageFactory2) result;
		}
		return new MessageFactory2Adapter(result);
	}

	private static void checkinit() {
		if (messageFactory == null)
			throw new InstantiationError("字符串助手StringUtil：messageFactory 未被初始化");
	}

}

package maoko.common.model.system;

/**
 * 日志等级
 * 
 * @author fanpei
 *
 */
public enum LogLevel {

	DEBUG(0), INFO(1), WARN(2), ERROR(3);

	int level;

	private LogLevel(int _level) {
		level = _level;
	}
}

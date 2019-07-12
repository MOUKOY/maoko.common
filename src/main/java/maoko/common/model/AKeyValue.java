package maoko.common.model;

public abstract class AKeyValue {

	protected String key;
	protected String value;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public AKeyValue() {
	}

	public AKeyValue(String key, String value) {
		this.key = key;
		this.value = value;

	}

}

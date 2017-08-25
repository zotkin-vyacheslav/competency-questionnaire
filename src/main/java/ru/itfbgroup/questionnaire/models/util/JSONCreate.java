package ru.itfbgroup.questionnaire.models.util;

public class JSONCreate extends JSONParse {

	private String key;
	private String[] values;

	public JSONCreate(String key, String[] values) {
		this.key = key;
		this.values = values;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String[] getValues() {
		return values;
	}

	public void setValues(String[] values) {
		this.values = values;
	}
}

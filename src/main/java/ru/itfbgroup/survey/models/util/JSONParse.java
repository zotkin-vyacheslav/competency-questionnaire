package ru.itfbgroup.survey.models.util;

import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;

public class JSONParse implements Serializable {

	private String id;

	private String value;

	public JSONParse() {
	}

	public JSONParse(String id, String value) {
		this.id = id;
		this.value = value;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JsonValue
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return id + ", " + value;
	}
}

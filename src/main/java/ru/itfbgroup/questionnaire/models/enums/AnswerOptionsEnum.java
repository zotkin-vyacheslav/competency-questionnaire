package ru.itfbgroup.questionnaire.models.enums;

public enum AnswerOptionsEnum {
	NO("Нет"), BEGIN("Начальные знания"), BASIC("Базовые знания"), ADVANCED("Продвинутые знания");

	private final String var;

	AnswerOptionsEnum(String var) {
		this.var = var;
	}

	@Override
	public String toString() {
		return var;
	}
}

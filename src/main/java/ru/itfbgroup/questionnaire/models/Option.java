package ru.itfbgroup.questionnaire.models;

import ru.itfbgroup.questionnaire.models.enums.AnswerOptionsEnum;

import javax.persistence.*;

@Entity
@Table(name = "option")
public class Option {

	@Id
	@GeneratedValue
	@Column(name = "option_id")
	private Long optionId;

	@Column(name = "answer_options")
	@Enumerated(EnumType.STRING)
	private AnswerOptionsEnum answerOptionsEnum;

	@Column(name = "additional")
	private String additional;

	public Long getOptionId() {
		return optionId;
	}

	public void setOptionId(Long optionId) {
		this.optionId = optionId;
	}

	public AnswerOptionsEnum getAnswerOptionsEnum() {
		return answerOptionsEnum;
	}

	public void setAnswerOptionsEnum(AnswerOptionsEnum answerOptionsEnum) {
		this.answerOptionsEnum = answerOptionsEnum;
	}

	public String getAdditional() {
		return additional;
	}

	public void setAdditional(String additional) {
		this.additional = additional;
	}
}

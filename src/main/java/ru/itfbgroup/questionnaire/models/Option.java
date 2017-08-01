package ru.itfbgroup.questionnaire.models;

import ru.itfbgroup.questionnaire.models.enums.AnswerOptionsEnum;

import javax.persistence.*;

@Entity
@Table(name = "options")
public class Option {

	@Id
	@GeneratedValue
//	@GeneratedValue(strategy=GenerationType.AUTO, generator="my_entity_seq_gen")
//	@SequenceGenerator(name="my_entity_seq_gen", sequenceName="MY_ENTITY_SEQ", allocationSize = 1)
	@Column(name = "option_id")
	private Long optionId;

	@Column(name = "answer_options")
	@Enumerated(EnumType.STRING)
	private AnswerOptionsEnum answerOptionsEnum;

	@Column(name = "option_name")
	private String optionName;

	@Column(name = "additional")
	private String additional;

	public Option() {
	}

	public Option(AnswerOptionsEnum answerOptionsEnum, String additional) {
		this.answerOptionsEnum = answerOptionsEnum;
		this.additional = additional;
	}

	public Option(AnswerOptionsEnum answerOptionsEnum, String optionName, String additional) {
		this.answerOptionsEnum = answerOptionsEnum;
		this.optionName = optionName;
		this.additional = additional;
	}

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

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
}

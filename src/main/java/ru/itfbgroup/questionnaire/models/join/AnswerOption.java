package ru.itfbgroup.questionnaire.models.join;

import ru.itfbgroup.questionnaire.models.Answer;
import ru.itfbgroup.questionnaire.models.Option;

import javax.persistence.*;

@Entity
@Table(name = "answers_options")
public class AnswerOption {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "answer_options_id")
	private Long id;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "answer_id")
	private Answer answer;

	@OneToOne
	@JoinColumn(name = "option_id")
	private Option option;

	@Column(name = "string_answer")
	private String stringAnswer;

	public AnswerOption() {
	}

	public AnswerOption(Answer answer, Option option, String stringAnswer) {
		this.answer = answer;
		this.option = option;
		this.stringAnswer = stringAnswer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public Option getOption() {
		return option;
	}

	public void setOption(Option option) {
		this.option = option;
	}

	public String getStringAnswer() {
		return stringAnswer;
	}

	public void setStringAnswer(String stringAnswer) {
		this.stringAnswer = stringAnswer;
	}
}

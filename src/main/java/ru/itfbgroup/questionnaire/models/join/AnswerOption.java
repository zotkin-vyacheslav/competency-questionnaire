package ru.itfbgroup.questionnaire.models.join;

import ru.itfbgroup.questionnaire.models.Option;
import ru.itfbgroup.questionnaire.models.util.PossibleAnswer;

import javax.persistence.*;

@Entity
@Table(name = "answers_options")
public class AnswerOption implements Comparable<AnswerOption> {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "answer_options_id")
	private Long id;

	@OneToOne
	@JoinColumn(name = "option_id")
	private Option option;

	@OneToOne
	@JoinColumn(name = "id")
	private PossibleAnswer possibleAnswer;

	public AnswerOption() {
	}

	public AnswerOption(Option option, PossibleAnswer possibleAnswer) {
		this.option = option;
		this.possibleAnswer = possibleAnswer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Option getOption() {
		return option;
	}

	public void setOption(Option option) {
		this.option = option;
	}

	public PossibleAnswer getPossibleAnswer() {
		return possibleAnswer;
	}

	public void setPossibleAnswer(PossibleAnswer possibleAnswer) {
		this.possibleAnswer = possibleAnswer;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		AnswerOption that = (AnswerOption) o;

		if (id != null ? !id.equals(that.id) : that.id != null) return false;
		if (option != null ? !option.equals(that.option) : that.option != null) return false;
		return possibleAnswer != null ? possibleAnswer.equals(that.possibleAnswer) : that.possibleAnswer == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (option != null ? option.hashCode() : 0);
		result = 31 * result + (possibleAnswer != null ? possibleAnswer.hashCode() : 0);
		return result;
	}

	@Override
	public int compareTo(AnswerOption o) {
		if (this.option.getOptionId() > o.option.getOptionId()) {
			return 1;
		} else if (this.option.getOptionId() < o.option.getOptionId()) {
			return -1;
		} else {
			return 0;
		}
	}
}

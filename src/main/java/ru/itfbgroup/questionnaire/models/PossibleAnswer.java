package ru.itfbgroup.questionnaire.models;

import javax.persistence.*;

@Entity
@Table(name = "possible_answers")
public class PossibleAnswer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "possible_answer_id")
	private Long id;

	@Column(name = "possible_answer_name")
	private String possibleAnswer;

	public PossibleAnswer() {
	}

	public PossibleAnswer(String possibleAnswer) {
		this.possibleAnswer = possibleAnswer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPossibleAnswer() {
		return possibleAnswer;
	}

	public void setPossibleAnswer(String possibleAnswer) {
		this.possibleAnswer = possibleAnswer;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		PossibleAnswer that = (PossibleAnswer) o;

		if (id != null ? !id.equals(that.id) : that.id != null) return false;
		return possibleAnswer != null ? possibleAnswer.equals(that.possibleAnswer) : that.possibleAnswer == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (possibleAnswer != null ? possibleAnswer.hashCode() : 0);
		return result;
	}
}

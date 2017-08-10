package ru.itfbgroup.questionnaire.models;

import ru.itfbgroup.questionnaire.models.join.AdditionalInfo;
import ru.itfbgroup.questionnaire.models.join.AnswerOption;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "answers")
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "answer_id")
	private Long id;

	@OneToMany(cascade = CascadeType.ALL)
	private Set<AnswerOption> answerOptions;

	@OneToMany(cascade = CascadeType.ALL)
	private Set<AdditionalInfo> additionalInfoSet;

	public Answer() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<AnswerOption> getAnswerOptions() {
		return answerOptions;
	}

	public void setAnswerOptions(Set<AnswerOption> answerOptions) {
		this.answerOptions = answerOptions;
	}

	public Set<AdditionalInfo> getAdditionalInfoSet() {
		return additionalInfoSet;
	}

	public void setAdditionalInfoSet(Set<AdditionalInfo> additionalInfoSet) {
		this.additionalInfoSet = additionalInfoSet;
	}
}

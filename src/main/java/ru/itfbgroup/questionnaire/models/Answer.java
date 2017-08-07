package ru.itfbgroup.questionnaire.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "answers")
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "answer_id")
	private Long id;

	@OneToMany(mappedBy = "option", cascade = CascadeType.ALL)
	private Set<AnswerOption> answerOptions;

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
}

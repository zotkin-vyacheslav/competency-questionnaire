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

//	@OneToOne
//	private User user;
//
//	@ManyToOne(cascade = CascadeType.ALL)
//	private Option option;

//	private SubCategory subCategory;

	@Column(name = "additional")
	private String additional;

	@OneToMany(mappedBy = "option")
	private Set<AnswerOption> answerOptions;

	public Answer() {
	}

//	public UserAnswer(User user) {
//		this.user = user;
//	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
//
//	public Option getOption() {
//		return option;
//	}
//
//	public void setOption(Option option) {
//		this.option = option;
//	}

	public String getAdditional() {
		return additional;
	}

	public void setAdditional(String additional) {
		this.additional = additional;
	}

	public Set<AnswerOption> getAnswerOptions() {
		return answerOptions;
	}

	public void setAnswerOptions(Set<AnswerOption> answerOptions) {
		this.answerOptions = answerOptions;
	}
}

package ru.itfbgroup.questionnaire.models;

import ru.itfbgroup.questionnaire.models.join.AdditionalInfo;
import ru.itfbgroup.questionnaire.models.join.AnswerOption;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "answers")
public class Answer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "answer_id")
	private Long id;

	@OneToMany(cascade = CascadeType.ALL)
	private List<AnswerOption> answerOptions;

	@OneToMany(cascade = CascadeType.ALL)
	private Set<AdditionalInfo> additionalInfoSet;

	@Column(name = "last_try_date")
	private Date lastTryDate;

	@OneToOne
	private User user;

	public Answer() {
	}

	public Answer(List<AnswerOption> answerOptions, Set<AdditionalInfo> additionalInfoSet, Date lastTryDate, User user) {
		this.answerOptions = answerOptions;
		this.additionalInfoSet = additionalInfoSet;
		this.lastTryDate = lastTryDate;
		this.user = user;
	}

	public Answer(Date lastTryDate) {
		this.lastTryDate = lastTryDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<AnswerOption> getAnswerOptions() {
		return answerOptions;
	}

	public void setAnswerOptions(List<AnswerOption> answerOptions) {
		this.answerOptions = answerOptions;
	}

	public Set<AdditionalInfo> getAdditionalInfoSet() {
		return additionalInfoSet;
	}

	public void setAdditionalInfoSet(Set<AdditionalInfo> additionalInfoSet) {
		this.additionalInfoSet = additionalInfoSet;
	}

	public Date getLastTryDate() {
		return lastTryDate;
	}

	public void setLastTryDate(Date lastTryDate) {
		this.lastTryDate = lastTryDate;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Answer answer = (Answer) o;

		return id != null ? id.equals(answer.id) : answer.id == null;
	}

	@Override
	public int hashCode() {
		return id != null ? id.hashCode() : 0;
	}
}

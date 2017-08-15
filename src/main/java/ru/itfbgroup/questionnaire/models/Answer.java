package ru.itfbgroup.questionnaire.models;

import org.hibernate.annotations.Type;
import ru.itfbgroup.questionnaire.models.join.AdditionalInfo;
import ru.itfbgroup.questionnaire.models.join.AnswerOption;

import javax.persistence.*;
import java.time.LocalDateTime;
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

	@Column(name = "last_try_date")
	@Type(type = "org.hibernate.type.LocalDateTimeType")
	private LocalDateTime lastTryDate;

	public Answer() {
	}

	public Answer(LocalDateTime lastTryDate) {
		this.lastTryDate = lastTryDate;
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

	public LocalDateTime getLastTryDate() {
		return lastTryDate;
	}

	public void setLastTryDate(LocalDateTime lastTryDate) {
		this.lastTryDate = lastTryDate;
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

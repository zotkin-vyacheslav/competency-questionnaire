package ru.itfbgroup.questionnaire.models;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "user_id")
	private Long id;

	@Column(name = "email", nullable = false, unique = true)
	private String email;

	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "answer_id")
	private Answer answer;

	public User() {
	}

	public User(String email) {
		this.email = email;
	}

	public User(String email, Answer answer) {
		this.email = email;
		this.answer = answer;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Answer getAnswer() {
		return answer;
	}

	public void setUserAnswer(Answer answer) {
		this.answer = answer;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;

		if (id != null ? !id.equals(user.id) : user.id != null) return false;
		return email != null ? email.equals(user.email) : user.email == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (email != null ? email.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", email='" + email + '\'' +
				'}';
	}
}

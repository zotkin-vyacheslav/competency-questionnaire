package ru.itfbgroup.questionnaire.models;

import javax.persistence.*;

@Entity
@Table(name = "options")
public class Option {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "option_id")
	private Long optionId;

	@Column(name = "option_name")
	private String optionName;

	public Option() {
	}

	public Option(String optionName) {
		this.optionName = optionName;
	}

	public Long getOptionId() {
		return optionId;
	}

	public void setOptionId(Long optionId) {
		this.optionId = optionId;
	}

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Option option = (Option) o;

		if (optionId != null ? !optionId.equals(option.optionId) : option.optionId != null) return false;
		return optionName != null ? optionName.equals(option.optionName) : option.optionName == null;
	}

	@Override
	public int hashCode() {
		int result = optionId != null ? optionId.hashCode() : 0;
		result = 31 * result + (optionName != null ? optionName.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Option{" +
				"optionId=" + optionId +
				", optionName='" + optionName + '\'' +
				'}';
	}
}

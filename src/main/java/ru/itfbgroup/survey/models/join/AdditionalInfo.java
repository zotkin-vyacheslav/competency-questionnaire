package ru.itfbgroup.survey.models.join;

import ru.itfbgroup.survey.models.Answer;
import ru.itfbgroup.survey.models.SubCategory;

import javax.persistence.*;

@Entity
@Table(name = "additional_information")
public class AdditionalInfo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "additional_info_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "answer_id")
	private Answer answer;

	@OneToOne
	@JoinColumn(name = "subcategory_id")
	private SubCategory subCategory;

	@Column(name = "additional_info")
	private String additional;

	public AdditionalInfo() {
	}

	public AdditionalInfo(SubCategory subCategory, String additional) {
		this.subCategory = subCategory;
		this.additional = additional;
	}

	//	public AdditionalInfo(Answer answer, SubCategory subCategory, String additional) {
//		this.answer = answer;
//		this.subCategory = subCategory;
//		this.additional = additional;
//	}

	public Answer getAnswer() {
		return answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	public String getAdditional() {
		return additional;
	}

	public void setAdditional(String additional) {
		this.additional = additional;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		AdditionalInfo that = (AdditionalInfo) o;

		if (id != null ? !id.equals(that.id) : that.id != null) return false;
		if (subCategory != null ? !subCategory.equals(that.subCategory) : that.subCategory != null) return false;
		return additional != null ? additional.equals(that.additional) : that.additional == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (subCategory != null ? subCategory.hashCode() : 0);
		result = 31 * result + (additional != null ? additional.hashCode() : 0);
		return result;
	}
}

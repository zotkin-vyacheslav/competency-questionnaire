package ru.itfbgroup.questionnaire.models;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "subcategory")
public class SubCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "subcategory_id")
	private Long subCategoryId;

	@Column(name = "name")
	private String name;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, targetEntity = Option.class)
//	@JoinTable(name = "subcategory_options", joinColumns = { @JoinColumn(name = "subcategory_id") },
//			inverseJoinColumns = { @JoinColumn(name = "option_id") })
	private List<Option> options;

	@Column(name = "additional")
	private String additional;

	public SubCategory() {
	}

	public SubCategory(String name, List<Option> options) {
		this.name = name;
		this.options = options;
	}

	public SubCategory(String name, List<Option> options, String additional) {
		this.name = name;
		this.options = options;
		this.additional = additional;
	}

	public Long getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(Long subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Option> getOptions() {
		return options;
	}

	public void setOptions(List<Option> options) {
		this.options = options;
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

		SubCategory that = (SubCategory) o;

		if (subCategoryId != null ? !subCategoryId.equals(that.subCategoryId) : that.subCategoryId != null)
			return false;
		return name != null ? name.equals(that.name) : that.name == null;
	}

	@Override
	public int hashCode() {
		int result = subCategoryId != null ? subCategoryId.hashCode() : 0;
		result = 31 * result + (name != null ? name.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "SubCategory{" +
				"subCategoryId=" + subCategoryId +
				", name='" + name + '\'' +
				", options=" + options +
				'}';
	}
}

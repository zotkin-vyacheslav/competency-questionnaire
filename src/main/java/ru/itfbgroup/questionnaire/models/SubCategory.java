package ru.itfbgroup.questionnaire.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "subcategory")
public class SubCategory {

	@Id
	@GeneratedValue
	@Column(name = "subcategory_id")
	private Long subCategoryId;

	@Column(name = "name")
	private String name;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = Option.class)
	private Set<Option> options;

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

	public Set<Option> getOptions() {
		return options;
	}

	public void setOptions(Set<Option> options) {
		this.options = options;
	}
}

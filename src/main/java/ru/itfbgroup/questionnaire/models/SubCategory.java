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
	@JoinTable(name = "subcategory_options", joinColumns = { @JoinColumn(name = "subcategory_id") },
			inverseJoinColumns = { @JoinColumn(name = "option_id") })
	private Set<Option> options;

	public SubCategory() {
	}

	public SubCategory(String name, Set<Option> options) {
		this.name = name;
		this.options = options;
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

	public Set<Option> getOptions() {
		return options;
	}

	public void setOptions(Set<Option> options) {
		this.options = options;
	}
}

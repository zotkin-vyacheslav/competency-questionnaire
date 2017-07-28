package ru.itfbgroup.questionnaire.models;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category {

	@Id
	@GeneratedValue
	@Column(name = "category_id")
	private Long categoryId;

	@Column(name = "category_name")
	private String categoryName;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = SubCategory.class)
	private Set<SubCategory> subCategories;

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Set<SubCategory> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(Set<SubCategory> subCategories) {
		this.subCategories = subCategories;
	}
}

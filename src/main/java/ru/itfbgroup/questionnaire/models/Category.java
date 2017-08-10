package ru.itfbgroup.questionnaire.models;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "category")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "category_id")
	private Long categoryId;

	@Column(name = "category_name")
	private String categoryName;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = SubCategory.class)
	private List<SubCategory> subCategories;

	@Column(name = "image_source")
	private String image;

	public Category() {
	}

	public Category(String categoryName, List<SubCategory> subCategories) {
		this.categoryName = categoryName;
		this.subCategories = subCategories;
	}

	public Category(String categoryName, List<SubCategory> subCategories, String image) {
		this.categoryName = categoryName;
		this.subCategories = subCategories;
		this.image = image;
	}

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

	public List<SubCategory> getSubCategories() {
		return subCategories;
	}

	public void setSubCategories(List<SubCategory> subCategories) {
		this.subCategories = subCategories;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Category category = (Category) o;

		if (categoryId != null ? !categoryId.equals(category.categoryId) : category.categoryId != null) return false;
		return categoryName != null ? categoryName.equals(category.categoryName) : category.categoryName == null;
	}

	@Override
	public int hashCode() {
		int result = categoryId != null ? categoryId.hashCode() : 0;
		result = 31 * result + (categoryName != null ? categoryName.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Category{" +
				"categoryId=" + categoryId +
				", categoryName='" + categoryName + '\'' +
				", subCategories=" + subCategories +
				'}';
	}
}

package ru.itfbgroup.survey.service.abstr;

import ru.itfbgroup.survey.models.Category;

import java.util.List;

public interface CategoryService {

	List<Category> getAllCategories();

	void addCategory(Category category);

	void updateCategory(Category category);

	Category getCategoryById(Long id);

	void deleteCategory(Long id);

	List<Integer> getAllCategoriesId();
}

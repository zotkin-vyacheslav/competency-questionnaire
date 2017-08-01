package ru.itfbgroup.questionnaire.service.abstr;

import ru.itfbgroup.questionnaire.models.Category;

import java.util.List;

public interface CategoryService {

	List<Category> getAllCategories();

	void addCategory(Category category);

	void updateCategory(Category category);

	Category getCategoryById(Long id);

	void deleteCategory(Long id);
}

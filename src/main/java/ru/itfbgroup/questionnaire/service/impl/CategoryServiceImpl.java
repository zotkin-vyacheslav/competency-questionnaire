package ru.itfbgroup.questionnaire.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itfbgroup.questionnaire.dao.abstr.CategoryDao;
import ru.itfbgroup.questionnaire.models.Category;
import ru.itfbgroup.questionnaire.service.abstr.CategoryService;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	@Override
	public List<Category> getAllCategories() {
		return categoryDao.getAll();
	}

	@Override
	public void addCategory(Category category) {
		categoryDao.persist(category);
	}

	@Override
	public void updateCategory(Category category) {
		categoryDao.update(category);
	}

	@Override
	public Category getCategoryById(Long id) {
		return categoryDao.getByKey(id);
	}

	@Override
	public void deleteCategory(Long id) {
		categoryDao.deleteByKey(id);
	}
}

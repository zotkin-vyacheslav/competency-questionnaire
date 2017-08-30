package ru.itfbgroup.survey.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itfbgroup.survey.dao.abstr.CategoryDao;
import ru.itfbgroup.survey.models.Category;
import ru.itfbgroup.survey.service.abstr.CategoryService;

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

	@Override
	public List<Integer> getAllCategoriesId() {
		return categoryDao.getAllCategoriesId();
	}
}

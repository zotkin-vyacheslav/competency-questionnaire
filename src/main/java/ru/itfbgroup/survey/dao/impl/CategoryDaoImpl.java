package ru.itfbgroup.survey.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itfbgroup.survey.dao.abstr.CategoryDao;
import ru.itfbgroup.survey.models.Category;

import java.util.List;

@Repository
@Transactional
public class CategoryDaoImpl extends AbstractDao<Long, Category> implements CategoryDao {

	@Override
	public List<Integer> getAllCategoriesId() {
		return entityManager.createNativeQuery("SELECT CATEGORY_ID FROM CATEGORY").getResultList();
	}
}

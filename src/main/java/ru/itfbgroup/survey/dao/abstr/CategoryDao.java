package ru.itfbgroup.survey.dao.abstr;

import ru.itfbgroup.survey.models.Category;

import java.util.List;

public interface CategoryDao extends GenericDao<Long, Category> {

	List<Integer> getAllCategoriesId();
}

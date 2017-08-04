package ru.itfbgroup.questionnaire.dao.abstr;

import ru.itfbgroup.questionnaire.models.Category;

import java.util.List;

public interface CategoryDao extends GenericDao<Long, Category> {

	List<Integer> getAllCategoriesId();
}

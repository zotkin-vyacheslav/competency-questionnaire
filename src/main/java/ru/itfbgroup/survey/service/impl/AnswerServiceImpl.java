package ru.itfbgroup.survey.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itfbgroup.survey.dao.abstr.*;
import ru.itfbgroup.survey.models.Answer;
import ru.itfbgroup.survey.models.Category;
import ru.itfbgroup.survey.models.SubCategory;
import ru.itfbgroup.survey.models.User;
import ru.itfbgroup.survey.models.util.JSONParse;
import ru.itfbgroup.survey.service.abstr.AnswerService;
import ru.itfbgroup.survey.service.abstr.CategoryService;

import java.math.BigDecimal;
import java.util.*;

@Service
public class AnswerServiceImpl implements AnswerService {

	@Autowired
	private AnswerDao answerDao;

	@Autowired
	private OptionDao optionDao;

	@Autowired
	private PossibleAnswerDao possibleAnswerDao;

	@Autowired
	private SubcategoryDao subcategoryDao;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private UserDao userDao;

	@Override
	public void saveOptionsUserAnswer(Answer answer, List<JSONParse> jsonParses) {
		Long answerId = answer.getId();
		for (JSONParse jsonParse : jsonParses) {
			answerDao.saveOptionsAnswer(answerId, Long.parseLong(jsonParse.getId()),Long.parseLong(jsonParse.getValue()));
		}
		answerDao.updateAnswerDate(answerId);
	}

	@Override
	public void saveAdditionalInfo(Answer answer, List<JSONParse> jsonParses) {
		Long answerId = answer.getId();
		for (JSONParse jsonParse : jsonParses) {
			answerDao.saveAdditionalAnswer(answerId, Long.parseLong(jsonParse.getId()), jsonParse.getValue());
		}

		answerDao.update(answer);
	}

	@Override
	public Answer getAnswerById(Long id) {
		return answerDao.getByKey(id);
	}

	@Override
	public void saveUserAnswer(Answer answer) {
		answerDao.persist(answer);
	}

	@Override
	public List<JSONParse> getUserAnswerForJSON(Long userId) {
		return answerDao.getUserAnswerForJSON(userId);
	}

	@Override
	public List<JSONParse> getAdditionalUserAnswerForJSON(Long userId) {
		return answerDao.getAdditionalAnswers(userId);
	}

	@Override
	public List getDataForStatistics(Long categoryId) {
		Category category = categoryService.getCategoryById(categoryId);

		List list = new ArrayList();
		list.add(category.getCategoryName());

		for (SubCategory subCategory : category.getSubCategories()) {
			List strings = new ArrayList();

			strings.add(subCategory.getName());
			strings.add(answerDao.getOptionsNames(subCategory.getSubCategoryId()));

			for (int i = 1; i < 5; i++) {
				strings.add(answerDao.getDataForStatistics(subCategory.getSubCategoryId(), new Long(i)));
			}

			list.add(strings);
		}

		return list;
	}

	@Override
	public List getDataForPersonalStatistics(Long userId, Long categoryId) {
		User user = userDao.getByKey(userId);

		Category category = categoryService.getCategoryById(categoryId);

		List list = new ArrayList();
		list.add(category.getCategoryName());

		for (SubCategory subCategory : category.getSubCategories()) {
			List strings = new ArrayList();

			strings.add(subCategory.getName());
			strings.add(answerDao.getOptionsNames(subCategory.getSubCategoryId()));

			List<BigDecimal> allUserAnswersId = answerDao.getAllUserAnswerId(userId);

			for (BigDecimal j : allUserAnswersId) {
				List temp = new ArrayList();
				Answer answer = answerDao.getByKey(j.longValue());
				temp.add(answerDao.getUserAnswerByCategory(j.longValue(), subCategory.getSubCategoryId()));
				temp.add(answer.getTimestamp());
				strings.add(temp);
			}

			list.add(strings);
		}

		return list;
	}
}

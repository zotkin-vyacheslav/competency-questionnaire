package ru.itfbgroup.survey.dao.abstr;

import ru.itfbgroup.survey.models.Answer;
import ru.itfbgroup.survey.models.User;
import ru.itfbgroup.survey.models.util.JSONParse;

import java.math.BigDecimal;
import java.util.List;

public interface AnswerDao extends GenericDao<Long, Answer> {

	void saveOptionsAnswer(Long answerId, Long optionId, Long possibleAnswerId);

	void saveAdditionalAnswer(Long answerId, Long subcategoryId, String answer);

	void updateAnswerDate(Long answerId);

	List<JSONParse> getUserAnswerForJSON(Long userId);

	List<JSONParse> getAdditionalAnswers(Long userId);

	List<String> getDataForStatistics(Long subCategoryId, Long possibleAnswerId);

	List<String> getOptionsNames(Long subCategoryId);

	List<Answer> getUserAnswers(User user);

	List<BigDecimal> getAllUserAnswerId(Long userId);

	List<BigDecimal> getUserAnswerByCategory(Long answerId, Long subCatId);
}

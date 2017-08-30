package ru.itfbgroup.survey.dao.abstr;

import ru.itfbgroup.survey.models.Answer;
import ru.itfbgroup.survey.models.util.JSONParse;

import java.util.List;

public interface AnswerDao extends GenericDao<Long, Answer> {

	void saveOptionsAnswer(Long answerId, Long optionId, Long possibleAnswerId);

	void saveAdditionalAnswer(Long answerId, Long subcategoryId, String answer);

	void updateAnswerDate(Long answerId);

	List<JSONParse> getUserAnswerForShow(Long userId);
}

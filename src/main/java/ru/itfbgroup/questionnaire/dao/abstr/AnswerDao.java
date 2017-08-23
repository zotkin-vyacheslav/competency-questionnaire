package ru.itfbgroup.questionnaire.dao.abstr;

import ru.itfbgroup.questionnaire.models.Answer;
import ru.itfbgroup.questionnaire.models.util.JSONParse;

import java.util.List;

public interface AnswerDao extends GenericDao<Long, Answer> {

	void saveOptionsAnswer(Long answerId, Long optionId, Long possibleAnswerId);

	void saveAdditionalAnswer(Long answerId, Long subcategoryId, String answer);

	void updateAnswerDate(Long answerId);

	List<JSONParse> getUserAnswerForShow(Long userId);
}

package ru.itfbgroup.questionnaire.dao.abstr;

import ru.itfbgroup.questionnaire.models.Answer;
import ru.itfbgroup.questionnaire.models.util.JSONParse;

import java.util.List;

public interface AnswerDao extends GenericDao<Long, Answer> {

	void saveAnswer(Long answerId, Long optionId, Long possibleAnswerId);

	void updateAnswerDate(Long answerId);

	List<JSONParse> getUserAnswerForJSON(Long userId);
}

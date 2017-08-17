package ru.itfbgroup.questionnaire.dao.abstr;

import ru.itfbgroup.questionnaire.models.Answer;

public interface AnswerDao extends GenericDao<Long, Answer> {

	void saveAnswer(Long answerId, Long optionId, Long possibleAnswerId);

	void updateAnswerDate(Long answerId);
}

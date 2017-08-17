package ru.itfbgroup.questionnaire.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itfbgroup.questionnaire.dao.abstr.AnswerDao;
import ru.itfbgroup.questionnaire.models.Answer;

@Repository
@Transactional
public class AnswerDaoImpl extends AbstractDao<Long, Answer> implements AnswerDao {

	@Override
	public void saveAnswer(Long answerId, Long optionId, Long possibleAnswerId) {
//		entityManager.createNativeQuery("UPDATE ANSWERS SET LAST_TRY_DATE = sysdate WHERE ANSWER_ID = :answer")
//				.setParameter("answer", answerId);

		entityManager.createNativeQuery("INSERT INTO ANSWERS_OPTIONS (ANSWER_OPTIONS_ID, OPTION_ID, ID) VALUES (HIBERNATE_SEQUENCE.nextval, :option_id, :possible_answer_id)")
				.setParameter("option_id", optionId)
				.setParameter("possible_answer_id", possibleAnswerId)
				.executeUpdate();

		entityManager.createNativeQuery("INSERT INTO ANSWERS_ANSWERS_OPTIONS (ANSWERS_ANSWER_ID, ANSWEROPTIONS_ANSWER_OPTIONS_ID) VALUES (:answer, HIBERNATE_SEQUENCE.currval)")
				.setParameter("answer", answerId)
				.executeUpdate();
	}

	@Override
	public void updateAnswerDate(Long answerId) {

		entityManager.createNativeQuery("UPDATE ANSWERS SET LAST_TRY_DATE = sysdate WHERE ANSWER_ID = :answer")
				.setParameter("answer", answerId)
				.executeUpdate();
	}
}

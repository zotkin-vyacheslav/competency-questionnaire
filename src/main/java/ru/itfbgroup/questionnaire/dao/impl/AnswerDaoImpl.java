package ru.itfbgroup.questionnaire.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itfbgroup.questionnaire.dao.abstr.AnswerDao;
import ru.itfbgroup.questionnaire.models.Answer;
import ru.itfbgroup.questionnaire.models.util.JSONParse;

import java.util.List;

@Repository
@Transactional
public class AnswerDaoImpl extends AbstractDao<Long, Answer> implements AnswerDao {

	@Override
	public void saveAnswer(Long answerId, Long optionId, Long possibleAnswerId) {
		entityManager.createNativeQuery("INSERT INTO ANSWERS_OPTIONS (ANSWER_OPTIONS_ID, OPTION_ID, POSSIBLE_ANSWER_ID) VALUES (HIBERNATE_SEQUENCE.nextval, :option_id, :possible_answer_id)")
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

	@Override
	public List<JSONParse> getUserAnswerForJSON(Long userId) {
//		return entityManager.createNativeQuery("SELECT\n" +
//				"  ao.OPTION_ID,\n" +
//				"  ao.POSSIBLE_ANSWER_ID,\n" +
//				"  so.SUBCATEGORY_SUBCATEGORY_ID\n" +
//				"FROM\n" +
//				"  USERS u\n" +
//				"  JOIN ANSWERS a ON u.ANSWER_ANSWER_ID = a.ANSWER_ID\n" +
//				"  JOIN ANSWERS_ANSWERS_OPTIONS aao ON a.ANSWER_ID = aao.ANSWERS_ANSWER_ID\n" +
//				"  JOIN ANSWERS_OPTIONS ao ON aao.ANSWEROPTIONS_ANSWER_OPTIONS_ID = ao.ANSWER_OPTIONS_ID\n" +
//				"  JOIN SUBCATEGORY_OPTIONS so ON so.OPTIONS_OPTION_ID = ao.OPTION_ID\n" +
//				"WHERE u.USER_ID = :userId")
//				.setParameter("userId", userId)
//				.getResultList();
		return entityManager.createNativeQuery("SELECT\n" +
				"  ao.OPTION_ID,\n" +
				"  ao.POSSIBLE_ANSWER_ID\n" +
				"FROM\n" +
				"  USERS u\n" +
				"  JOIN ANSWERS a ON u.ANSWER_ID = a.ANSWER_ID\n" +
				"  JOIN ANSWERS_ANSWERS_OPTIONS aao ON a.ANSWER_ID = aao.ANSWERS_ANSWER_ID\n" +
				"  JOIN ANSWERS_OPTIONS ao ON aao.ANSWEROPTIONS_ANSWER_OPTIONS_ID = ao.ANSWER_OPTIONS_ID\n" +
				"WHERE u.USER_ID = :userId")
				.setParameter("userId", userId)
				.getResultList();
	}
}

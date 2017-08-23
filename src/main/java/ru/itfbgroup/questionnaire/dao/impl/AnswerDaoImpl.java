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
	public void saveOptionsAnswer(Long answerId, Long optionId, Long possibleAnswerId) {
		entityManager.createNativeQuery("INSERT INTO " +
				"ANSWERS_OPTIONS (ANSWER_OPTIONS_ID, ANSWER_ID, OPTION_ID, POSSIBLE_ANSWER_ID) " +
				"VALUES (HIBERNATE_SEQUENCE.nextval, :answer, :option_id, :possible_answer_id)")
				.setParameter("option_id", optionId)
				.setParameter("answer", answerId)
				.setParameter("possible_answer_id", possibleAnswerId)
				.executeUpdate();

//		entityManager.createNativeQuery("INSERT INTO ANSWERS_ANSWERS_OPTIONS (ANSWERS_ANSWER_ID, ANSWEROPTIONS_ANSWER_OPTIONS_ID) VALUES (:answer, HIBERNATE_SEQUENCE.currval)")
//				.setParameter("answer", answerId)
//				.executeUpdate();
	}

	@Override
	public void saveAdditionalAnswer(Long answerId, Long subcategoryId, String additionalAnswer) {
		entityManager.createNativeQuery("INSERT INTO" +
				"  ADDITIONAL_INFORMATION (ADDITIONAL_INFO_ID, ADDITIONAL_INFO, ANSWER_ID, SUBCATEGORY_ID)" +
				"VALUES (HIBERNATE_SEQUENCE.nextval, :additional, :answerId, :subcategoryId )")
				.setParameter("additional", additionalAnswer)
				.setParameter("answerId", answerId)
				.setParameter("subcategoryId", subcategoryId)
				.executeUpdate();
	}

	@Override
	public void updateAnswerDate(Long answerId) {

		entityManager.createNativeQuery("UPDATE ANSWERS SET TIMESTAMP= sysdate WHERE ANSWER_ID = :answer")
				.setParameter("answer", answerId)
				.executeUpdate();
	}

	@Override
	public List<JSONParse> getUserAnswerForShow(Long userId) {
		return entityManager.createNativeQuery("SELECT\n" +
				"  ao.OPTION_ID,\n" +
				"  ao.POSSIBLE_ANSWER_ID\n" +
				"FROM\n" +
				"  USERS u\n" +
				"  JOIN ANSWERS a ON u.ANSWER_ID = a.ANSWER_ID\n" +
				"  JOIN ANSWERS_OPTIONS ao ON a.ANSWER_ID = ao.ANSWER_ID\n" +
				"WHERE u.USER_ID = :userId")
				.setParameter("userId", userId)
				.getResultList();
	}
}

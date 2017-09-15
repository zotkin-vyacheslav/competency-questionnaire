package ru.itfbgroup.survey.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itfbgroup.survey.dao.abstr.AnswerDao;
import ru.itfbgroup.survey.models.Answer;
import ru.itfbgroup.survey.models.User;
import ru.itfbgroup.survey.models.util.JSONParse;

import java.math.BigDecimal;
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
	public List<JSONParse> getUserAnswerForJSON(Long userId) {
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

	@Override
	public List<JSONParse> getAdditionalAnswers(Long userId) {
		return (List<JSONParse> ) entityManager.createNativeQuery("SELECT ai.SUBCATEGORY_ID, ai.ADDITIONAL_INFO\n" +
				"  FROM USERS u\n" +
				"JOIN ANSWERS a ON u.ANSWER_ID = a.ANSWER_ID\n" +
				"JOIN ADDITIONAL_INFORMATION ai ON a.ANSWER_ID = ai.ANSWER_ID\n" +
				"WHERE u.USER_ID = :userId")
				.setParameter("userId", userId)
				.getResultList();
	}

	@Override
	public List<String> getDataForStatistics(Long subCategoryId, Long possibleAnswerId) {
		return entityManager.createNativeQuery("SELECT\n" +
				"  COUNT(o2.OPTION_ID)" +
				"\n" +
				"FROM OPTIONS op\n" +
				"  LEFT JOIN (SELECT o.OPTION_ID\n" +
				"             FROM OPTIONS o\n" +
				"               JOIN ANSWERS_OPTIONS ao ON o.OPTION_ID = ao.OPTION_ID\n" +
				"               JOIN POSSIBLE_ANSWERS po ON ao.POSSIBLE_ANSWER_ID = po.POSSIBLE_ANSWER_ID\n" +
				"               JOIN ANSWERS a ON ao.ANSWER_ID = a.ANSWER_ID\n" +
				"               JOIN USERS u ON a.ANSWER_ID = u.ANSWER_ID\n" +
				"             WHERE\n" +
				"                 po.POSSIBLE_ANSWER_ID = :possibleAnswerId\n" +
				"                 AND a.ISACTUAL = 1\n" +
				"                 AND u.ENABLED = 1\n" +
				"            ) o2\n" +
				"    ON o2.OPTION_ID=op.OPTION_ID\n" +
				"  JOIN SUBCATEGORY_OPTIONS so ON op.OPTION_ID = so.OPTIONS_OPTION_ID\n" +
				"  JOIN SUBCATEGORY s ON so.SUBCATEGORY_SUBCATEGORY_ID = s.SUBCATEGORY_ID\n" +
				"WHERE s.SUBCATEGORY_ID = :subCategoryId\n" +
				"\n" +
				"GROUP BY op.OPTION_NAME, op.OPTION_ID\n" +
				"ORDER BY op.OPTION_ID")
				.setParameter("subCategoryId", subCategoryId)
				.setParameter("possibleAnswerId", possibleAnswerId)
				.getResultList();
	}

	@Override
	public List<String> getOptionsNames(Long subCategoryId) {
		return entityManager.createNativeQuery("SELECT o.OPTION_NAME FROM OPTIONS o\n" +
				"  JOIN SUBCATEGORY_OPTIONS so ON o.OPTION_ID = so.OPTIONS_OPTION_ID\n" +
				"JOIN SUBCATEGORY s on so.SUBCATEGORY_SUBCATEGORY_ID = s.SUBCATEGORY_ID\n" +
				"WHERE s.SUBCATEGORY_ID = :subCategoryId").setParameter("subCategoryId", subCategoryId).getResultList();
	}

	public List<Answer> getUserAnswers(User user) {
		return entityManager.createQuery("select a from Answer a where a.user = :user").setParameter("user", user).getResultList();
	}

	@Override
	public List<BigDecimal> getAllUserAnswerId(Long userId) {
		return entityManager.createNativeQuery("SELECT a.ANSWER_ID\n" +
				"FROM ANSWERS a\n" +
				"WHERE a.USER_USER_ID = :userId").setParameter("userId", userId).getResultList();
	}

	@Override
	public List<BigDecimal> getUserAnswerByCategory(Long answerId, Long subCatId) {
		return entityManager.createNativeQuery("SELECT ao.POSSIBLE_ANSWER_ID FROM ANSWERS_OPTIONS ao\n" +
				"  JOIN SUBCATEGORY_OPTIONS so ON ao.OPTION_ID = so.OPTIONS_OPTION_ID\n" +
				"WHERE ao.ANSWER_ID = :answerId AND so.SUBCATEGORY_SUBCATEGORY_ID = :subCatId")
				.setParameter("answerId", answerId)
				.setParameter("subCatId", subCatId)
				.getResultList();
	}
}
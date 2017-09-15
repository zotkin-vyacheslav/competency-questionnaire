package ru.itfbgroup.survey.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itfbgroup.survey.dao.abstr.UserDao;
import ru.itfbgroup.survey.models.User;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl extends AbstractDao<Long, User> implements UserDao {

	@Override
	public List<User> getUsersToSendNotification() {

		return entityManager.createQuery("SELECT u from User u WHERE (current_date() - u.answer.timestamp) > 180").getResultList();
	}

	@Override
	public User getByUsername(String username) {
		try {
			return (User) entityManager.createQuery("select u from User u where u.email = :email").setParameter("email", username).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public List<User> getFilteredUsers(List<Long> options, List<Long> answers) {

		if (options.size() == 1) {

			return entityManager.createQuery("select u from AnswerOption ao " +
					"join ao.answer a " +
					"join a.user u " +
					"where a.isActual=true " +
					"and u.enabled=true " +
					"and ao.possibleAnswer.id in (:answers) " +
					"and ao.option.optionId in (:options)")
					.setParameter("answers", answers)
					.setParameter("options", options)
					.getResultList();
		} else if (options.size() == 2) {
			String innerSelect = "select u.id from AnswerOption ao " +
					"join ao.answer a " +
					"join a.user u " +
					"where a.isActual=true " +
					"and u.enabled=true " +
					"and ao.possibleAnswer.id in (:answers) " +
					"and ao.option.optionId = " + options.get(0);

			String hql = "select u from " +
					" AnswerOption ao " +
					"join ao.answer a " +
					"join a.user u " +
					"where a.isActual=true " +
					"and u.enabled=true " +
					" and u.id in (" + innerSelect + ") " +
					"and ao.possibleAnswer.id in (:answers) " +
					"and ao.option.optionId = " + options.get(1);

			return entityManager.createQuery(hql)
					.setParameter("answers", answers)
					.getResultList();

		} else if (options.size() == 3) {
			String tempSelect = "select u.id from AnswerOption ao " +
					"join ao.answer a " +
					"join a.user u " +
					"where a.isActual=true " +
					"and u.enabled=true " +
					"and ao.possibleAnswer.id in (:answers) " +
					"and ao.option.optionId = " + options.get(0);

			String innerSelect = "select u.id from AnswerOption ao " +
					"join ao.answer a " +
					"join a.user u " +
					"where a.isActual=true " +
					"and u.enabled=true " +
					"and u.id in (" + tempSelect + ") " +
					"and ao.possibleAnswer.id in (:answers) " +
					"and ao.option.optionId = " + options.get(1);

			String hql = "select u from " +
					" AnswerOption ao " +
					"join ao.answer a " +
					"join a.user u " +
					"where a.isActual=true " +
					"and u.enabled=true " +
					" and u.id in (" + innerSelect + ") " +
					"and ao.possibleAnswer.id in (:answers) " +
					"and ao.option.optionId = " + options.get(2);
			return entityManager.createQuery(hql)
					.setParameter("answers", answers)
					.getResultList();
		} else {
			String tempSelect = "select u.id from AnswerOption ao " +
					"join ao.answer a " +
					"join a.user u " +
					"where a.isActual=true " +
					"and u.enabled=true " +
					"and ao.possibleAnswer.id in (:answers) " +
					"and ao.option.optionId = " + options.get(0);

			String innerSelect1 = "select u.id from AnswerOption ao " +
					"join ao.answer a " +
					"join a.user u " +
					"where a.isActual=true " +
					"and u.enabled=true " +
					"and u.id in (" + tempSelect + ") " +
					"and ao.possibleAnswer.id in (:answers) " +
					"and ao.option.optionId = " + options.get(1);

			String innerSelect2 = "";

			for (int i = 3; i < options.size() - 1; i++) {

				innerSelect1 = "select u.id from AnswerOption ao " +
						"join ao.answer a " +
						"join a.user u " +
						"where a.isActual=true " +
						"and u.enabled=true " +
						"and u.id in (" + innerSelect1 + ") " +
						"and ao.possibleAnswer.id in (:answers) " +
						"and ao.option.optionId = " + options.get(i);


			}

			String hql = "select u from " +
					" AnswerOption ao " +
					"join ao.answer a " +
					"join a.user u " +
					"where a.isActual=true " +
					"and u.enabled=true " +
					" and u.id in (" + innerSelect1 + ") " +
					"and ao.possibleAnswer.id in (:answers) " +
					"and ao.option.optionId = " + options.get(2);

			return entityManager.createQuery(hql)
					.setParameter("answers", answers)
//					.setParameter("options1", options.get(0))
////				.setParameter("options2", options.get(1))
					.getResultList();

		}
	}

	@Override
	public void deleteByKey(Long aLong) {
		User user = getByKey(aLong);
		user.setEnabled(false);
		update(user);
	}
}

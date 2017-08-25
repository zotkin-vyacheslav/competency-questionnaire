package ru.itfbgroup.questionnaire.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itfbgroup.questionnaire.dao.abstr.UserDao;
import ru.itfbgroup.questionnaire.models.User;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
@Transactional
public class UserDaoImpl extends AbstractDao<Long, User> implements UserDao {

	@Override
	public List<User> getUsersToSendNotification() {

		//TODO: user > half of year
		return entityManager.createQuery("SELECT u from User u WHERE (day(current_date()) - day(u.answer.timestamp)) > -1").getResultList();
	}

	@Override
	public User getByUsername(String username) {
		try {
			return (User) entityManager.createQuery("select u from User u where u.email = :email").setParameter("email", username).getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
}

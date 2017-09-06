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
}

package ru.itfbgroup.questionnaire.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itfbgroup.questionnaire.dao.abstr.UserDao;
import ru.itfbgroup.questionnaire.models.User;

import java.util.List;

@Repository
@Transactional
public class UserDaoImpl extends AbstractDao<Long, User> implements UserDao {
	@Override
	public List<User> getUsersToSendNotification() {
		return entityManager.createNativeQuery("SELECT CATEGORY_ID FROM CATEGORY").getResultList();
	}
}

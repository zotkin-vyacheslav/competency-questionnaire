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

		//TODO: user > half of year
		return entityManager.createQuery("SELECT u from User u WHERE (day(current_date()) - day(u.answer.lastTryDate)) > -1").getResultList();
//		return entityManager.createNativeQuery("SELECT * FROM USERS u JOIN ANSWERS a on u.ANSWER_ANSWER_ID = a.ANSWER_ID WHERE trunc(sysdate - a.LAST_TRY_DATE) > -1").getResultList();
	}
}

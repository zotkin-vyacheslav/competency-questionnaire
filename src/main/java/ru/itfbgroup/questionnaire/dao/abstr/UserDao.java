package ru.itfbgroup.questionnaire.dao.abstr;

import ru.itfbgroup.questionnaire.models.User;

import java.util.List;

public interface UserDao extends GenericDao<Long, User> {

	List<User> getUsersToSendNotification();
}

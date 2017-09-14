package ru.itfbgroup.survey.dao.abstr;

import ru.itfbgroup.survey.models.User;

import java.util.List;

public interface UserDao extends GenericDao<Long, User> {

	List<User> getUsersToSendNotification();

	User getByUsername(String username);

	List<User> getFilteredUsers(List<Long> options, List<Long> answers);
}

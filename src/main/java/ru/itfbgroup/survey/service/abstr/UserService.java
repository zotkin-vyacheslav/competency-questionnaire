package ru.itfbgroup.survey.service.abstr;

import ru.itfbgroup.survey.models.User;

import java.util.List;

public interface UserService {

	List<User> getAllUsers();

	void addUser(User user);

	void updateUser(User user);

	void deleteUser(Long id);

	User getUserById(Long id);

	User getUserByName(String username);

	List<User> getUsersToSendNotification();
}

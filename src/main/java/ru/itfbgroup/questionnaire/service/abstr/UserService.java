package ru.itfbgroup.questionnaire.service.abstr;

import ru.itfbgroup.questionnaire.models.User;

import java.util.List;

public interface UserService {

	List<User> getAllUsers();

	void addUser(User user);

	void updateUser(User user);

	void deleteUser(Long id);

	User getUserById(Long id);
}

package ru.itfbgroup.survey.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itfbgroup.survey.dao.abstr.UserDao;
import ru.itfbgroup.survey.models.User;
import ru.itfbgroup.survey.service.abstr.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Override
	public List<User> getAllUsers() {
		return userDao.getAll();
	}

	@Override
	public void addUser(User user) {
		userDao.persist(user);
	}

	@Override
	public void updateUser(User user) {
		userDao.update(user);
	}

	@Override
	public void deleteUser(Long id) {
		userDao.deleteByKey(id);
	}

	@Override
	public User getUserById(Long id) {
		return userDao.getByKey(id);
	}

	@Override
	public User getUserByName(String username) {
		return userDao.getByUsername(username);
	}

	@Override
	public List<User> getUsersToSendNotification() {
		return userDao.getUsersToSendNotification();
	}
}

package ru.itfbgroup.survey.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itfbgroup.survey.dao.abstr.UserDao;
import ru.itfbgroup.survey.models.Role;
import ru.itfbgroup.survey.models.User;
import ru.itfbgroup.survey.service.abstr.RoleService;
import ru.itfbgroup.survey.service.abstr.UserService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleService roleService;

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

	@Override
	public void deleteUserById(Long id) {
		userDao.deleteByKey(id);
	}

	@Override
	public void riseUser(Long id, String stringRole) {
		User user = userDao.getByKey(id);

		Role role = null;
		if (stringRole.equals("admin")){
			role = roleService.getRoleByName("ADMIN");
		} else if (stringRole.equals("moder")) {
			role = roleService.getRoleByName("MODER");
		} else {
			return;
		}

		user.getAuthorities().add(role);

		userDao.update(user);
	}

	@Override
	public List<User> getFilteredUsers(List<Long> options, List<Long> answers) {
		return userDao.getFilteredUsers(options, answers);
	}
}

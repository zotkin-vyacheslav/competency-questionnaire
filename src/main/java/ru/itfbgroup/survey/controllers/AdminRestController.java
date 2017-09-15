package ru.itfbgroup.survey.controllers;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import ru.itfbgroup.survey.models.User;
import ru.itfbgroup.survey.service.abstr.AnswerService;
import ru.itfbgroup.survey.service.abstr.UserService;

import java.util.*;

@RestController
@RequestMapping(value = "admin")
public class AdminRestController {

	@Autowired
	private UserService userService;


	@Autowired
	private AnswerService answerService;

	@RequestMapping("get-all-employees")
	public List getAllEmployees() {
		List<User> users = userService.getAllUsers();
		return users;
	}

	@RequestMapping("personal-stat-data")
	public List getPersonalStat(@RequestParam(name = "userId") long userId, @RequestParam(name = "categoryId") long categoryId) {
		List list = answerService.getDataForPersonalStatistics(userId, categoryId);

		return list;
	}

	@RequestMapping("list")
	public List getDataStat(@RequestParam long categoryId) {
		List list = answerService.getDataForStatistics(categoryId);
		return list;
	}

	@RequestMapping(value = "delete/{userId}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteUser(@PathVariable(value = "userId") long userId) {
		try {
			userService.deleteUser(userId);
			return ResponseEntity.noContent().build();
		} catch (HibernateException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@RequestMapping(value = "rise-user", method = RequestMethod.POST)
	public ResponseEntity<Void> riseUserToAdmin(@RequestParam(name = "rise") String role, @RequestParam(name = "userId") long userId) {
		try {
			userService.riseUser(userId, role);
			return ResponseEntity.noContent().build();
		} catch (HibernateException e) {
			return ResponseEntity.notFound().build();
		}
	}


}

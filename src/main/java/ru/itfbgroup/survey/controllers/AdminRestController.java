package ru.itfbgroup.survey.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itfbgroup.survey.models.User;
import ru.itfbgroup.survey.service.abstr.AnswerService;
import ru.itfbgroup.survey.service.abstr.UserService;

import java.util.List;

@RestController
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
}

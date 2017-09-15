package ru.itfbgroup.survey.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.itfbgroup.survey.models.User;
import ru.itfbgroup.survey.service.abstr.CategoryService;
import ru.itfbgroup.survey.service.abstr.UserService;

import java.util.List;

@Controller
@RequestMapping(value = "admin")
public class AdminController {

	@Autowired
	private UserService userService;

	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "statistics")
	public ModelAndView getStatisticsPage() {

		ModelAndView modelAndView = new ModelAndView("admin/statistics");
		modelAndView.addObject("categoriesId", categoryService.getAllCategoriesId());
		return modelAndView;
	}

	@RequestMapping(value = "")
	public ModelAndView getStartAdminPage() {

		ModelAndView modelAndView = new ModelAndView("admin/adminPage");
//		modelAndView.addObject("categoriesId", categoryService.getAllCategoriesId());
		return modelAndView;
	}

	@RequestMapping(value = "employees")
	public ModelAndView getAllUsersPage() {
		
		List<User> users = userService.getAllUsers();

		ModelAndView modelAndView = new ModelAndView("admin/employeesPage");
		modelAndView.addObject("employees", users);
		return modelAndView;
	}

	@RequestMapping(value = "get-personal-answers", method = RequestMethod.GET)
	public ModelAndView getPersonalStatistics(@RequestParam(name = "userId") long userId) {

		User user = userService.getUserById(userId);
		String[] personalData = {user.getFirstName(),user.getLastName(), user.getEmail()};

		ModelAndView modelAndView = new ModelAndView("admin/personalStat");
		modelAndView.addObject("categoriesId", categoryService.getAllCategoriesId());
		modelAndView.addObject("userId", userId);
		modelAndView.addObject("personalData", personalData);
		return modelAndView;
	}
}

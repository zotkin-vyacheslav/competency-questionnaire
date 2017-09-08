package ru.itfbgroup.survey.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.itfbgroup.survey.models.User;
import ru.itfbgroup.survey.service.abstr.CategoryService;
import ru.itfbgroup.survey.service.abstr.UserService;

import java.util.List;

@Controller
public class AdminController {

	@Autowired
	private UserService userService;

	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "statistics")
	public ModelAndView getStatisticsPage() {

		ModelAndView modelAndView = new ModelAndView("statistics");
		modelAndView.addObject("categoriesId", categoryService.getAllCategoriesId());
		return modelAndView;
	}

	@RequestMapping(value = "admin")
	public ModelAndView getStartAdminPage() {

		ModelAndView modelAndView = new ModelAndView("adminPage");
//		modelAndView.addObject("categoriesId", categoryService.getAllCategoriesId());
		return modelAndView;
	}

	@RequestMapping(value = "employees")
	public ModelAndView getAllUsersPage() {
		
		List<User> users = userService.getAllUsers();

		ModelAndView modelAndView = new ModelAndView("employeesPage");
		modelAndView.addObject("employees", users);
		return modelAndView;
	}

	@RequestMapping(value = "personal-stat", method = RequestMethod.GET)
	public ModelAndView getPersonalStatistics() {

		ModelAndView modelAndView = new ModelAndView("personalStat");
		modelAndView.addObject("categoriesId", categoryService.getAllCategoriesId());
		return modelAndView;
	}
}

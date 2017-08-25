package ru.itfbgroup.questionnaire.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.itfbgroup.questionnaire.models.Answer;
import ru.itfbgroup.questionnaire.models.User;
import ru.itfbgroup.questionnaire.service.abstr.AnswerService;
import ru.itfbgroup.questionnaire.service.abstr.CategoryService;
import ru.itfbgroup.questionnaire.service.abstr.UserService;

@Controller
@SessionAttributes(value = "user")
public class UIController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private UserService userService;

//	@RequestMapping(method = RequestMethod.GET, value = "/")
//	public ModelAndView getGetStartPage() {
//		ModelAndView modelAndView = new ModelAndView("redirect:/login");
//		return modelAndView;
//	}

	@RequestMapping(method = RequestMethod.GET, value = {"/login", "/"})
	public ModelAndView getLoginPage() {
		ModelAndView modelAndView = new ModelAndView("login");
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/login")
	public ModelAndView getLoginPage(@RequestParam(value = "username") String email) {
		ModelAndView modelAndView = new ModelAndView("redirect:/survey");
		Answer userAnswer = new Answer();
		User user = new User(email, userAnswer);
		userAnswer.setUser(user);
		userAnswer.setUser(user);
		userService.addUser(user);
		modelAndView.addObject("user", user);
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/survey")
	public ModelAndView getTableData() {
		ModelAndView modelAndView = new ModelAndView("survey-page");

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.getUserByName(userDetails.getUsername());
		if (user == null) {
			Answer userAnswer = new Answer();
			user = new User(userDetails.getUsername(), userAnswer);
			userAnswer.setUser(user);
			userService.addUser(user);
		}
		modelAndView.addObject("user", user);
		modelAndView.addObject("categoriesId", categoryService.getAllCategoriesId());
		return modelAndView;
	}
}

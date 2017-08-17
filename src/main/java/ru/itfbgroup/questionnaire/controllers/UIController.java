package ru.itfbgroup.questionnaire.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.itfbgroup.questionnaire.models.Answer;
import ru.itfbgroup.questionnaire.models.User;
import ru.itfbgroup.questionnaire.service.abstr.CategoryService;
import ru.itfbgroup.questionnaire.service.abstr.UserService;

@Controller
@SessionAttributes(types = User.class)
public class UIController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET, value = "/")
	public ModelAndView getLoginPage() {
		ModelAndView modelAndView = new ModelAndView("login");
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/")
	public ModelAndView getLoginPage(Model model, @RequestParam(value = "email") String email) {
		ModelAndView modelAndView = new ModelAndView("redirect:/survey");

		Answer userAnswer = new Answer();

		User user = new User(email, userAnswer);
		userService.addUser(user);
		model.addAttribute("user", user);

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/survey")
	public ModelAndView getTableData() {
		ModelAndView modelAndView = new ModelAndView("survey-page");
		modelAndView.addObject("categoriesId", categoryService.getAllCategoriesId());
		return modelAndView;
	}

	//TODO: rename
	@RequestMapping(method = RequestMethod.GET, value = "/usr-answ")
	public ModelAndView getUserAnswer() {
		ModelAndView modelAndView = new ModelAndView("user-answer-page");
		modelAndView.addObject("categoriesId", categoryService.getAllCategoriesId());
		return modelAndView;
	}
}

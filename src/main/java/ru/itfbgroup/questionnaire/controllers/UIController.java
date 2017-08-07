package ru.itfbgroup.questionnaire.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import ru.itfbgroup.questionnaire.models.Answer;
import ru.itfbgroup.questionnaire.models.AnswerOption;
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

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView getLoginPage(Model model,
									 @RequestParam(value = "email") String email) {
		ModelAndView modelAndView = new ModelAndView("redirect:/index");

		Answer userAnswer = new Answer();
		AnswerOption answerOption = new AnswerOption();
		answerOption.setAnswer(userAnswer);

		User user = new User(email, userAnswer);
		userService.addUser(user);
		model.addAttribute("user", user);

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/index")
	public ModelAndView getTableData() {
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("categoriesId", categoryService.getAllCategoriesId());
		return modelAndView;
	}
}

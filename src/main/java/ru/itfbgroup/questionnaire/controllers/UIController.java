package ru.itfbgroup.questionnaire.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import ru.itfbgroup.questionnaire.dao.abstr.UserDao;
import ru.itfbgroup.questionnaire.models.User;
import ru.itfbgroup.questionnaire.service.abstr.CategoryService;
import ru.itfbgroup.questionnaire.service.abstr.UserService;

@Controller
@SessionAttributes(value = "user")
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
	public ModelAndView getLoginPage(@RequestParam(value = "email") String email) {
		ModelAndView modelAndView = new ModelAndView("redirect:/form");
		User user = new User(email);
		modelAndView.addObject("user", user);

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/form")
	public ModelAndView getQuestionPage() {
		ModelAndView modelAndView = new ModelAndView("formPage");
		modelAndView.addObject("categories", categoryService.getAllCategories());

		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/form")
	public ModelAndView getQuestionPage(@ModelAttribute User user,
										@RequestParam(value = "additional") String additional,
										@RequestParam(value = "optradio") String radioValue,
										SessionStatus sessionStatus) {
		ModelAndView modelAndView = new ModelAndView("formPage");
		modelAndView.addObject("categories", categoryService.getAllCategories());

//		sessionStatus.setComplete();
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/index")
	public ModelAndView getTableData() {
		ModelAndView modelAndView = new ModelAndView("tablesEx");

		return modelAndView;
	}
}

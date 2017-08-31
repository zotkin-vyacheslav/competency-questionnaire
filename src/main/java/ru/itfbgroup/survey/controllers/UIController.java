package ru.itfbgroup.survey.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.itfbgroup.survey.configs.security.CustomLdapUserDetails;
import ru.itfbgroup.survey.models.Answer;
import ru.itfbgroup.survey.models.User;
import ru.itfbgroup.survey.service.abstr.CategoryService;
import ru.itfbgroup.survey.service.abstr.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@SessionAttributes(value = "user")
public class UIController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET, value = {"/login", "/"})
	public ModelAndView getLoginPage() {
		ModelAndView modelAndView = new ModelAndView("login");
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, value = "answer")
	public ModelAndView getTableData() {
		ModelAndView modelAndView = new ModelAndView("survey-page");

		CustomLdapUserDetails userDetails = (CustomLdapUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.getUserByName(userDetails.getMail());

		if (user == null) {
			Answer userAnswer = new Answer();
			user = new User(userDetails.getMail(), userAnswer);
			userAnswer.setUser(user);
			userService.addUser(user);
		}
		logger.debug("user " + user.getEmail() + " logged");
		modelAndView.addObject("user", user);
		modelAndView.addObject("categoriesId", categoryService.getAllCategoriesId());
		return modelAndView;
	}

	@RequestMapping(value = "logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}
}

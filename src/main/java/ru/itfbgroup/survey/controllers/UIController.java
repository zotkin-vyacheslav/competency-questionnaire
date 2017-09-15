package ru.itfbgroup.survey.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.unboundid.util.json.JSONException;
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
import ru.itfbgroup.survey.models.util.JSONParse;
import ru.itfbgroup.survey.service.abstr.AnswerService;
import ru.itfbgroup.survey.service.abstr.CategoryService;
import ru.itfbgroup.survey.service.abstr.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@SessionAttributes(value = "user")
public class UIController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private UserService userService;

	@Autowired
	private AnswerService answerService;

	@RequestMapping(method = RequestMethod.GET, value = {"/login", "/"})
	public ModelAndView getLoginPage() {
		ModelAndView modelAndView = new ModelAndView("login");
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET, value = "answer", produces={"application/json; charset=UTF-8"})
	public ModelAndView getTableData() throws JsonProcessingException, JSONException {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("categoriesId", categoryService.getAllCategoriesId());

		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		List<JSONParse> objects = answerService.getUserAnswerForJSON(user.getId());
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(objects);

		modelAndView.setViewName("update-answer-page");
		modelAndView.addObject("user", user);
		modelAndView.addObject("categoriesId", categoryService.getAllCategoriesId());
		modelAndView.addObject("answers", json);

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

	@RequestMapping(value = "search")
	public ModelAndView getSearchPage() {

		List<User> users = userService.getAllUsers();

		ModelAndView modelAndView = new ModelAndView("admin/searchPage");
		modelAndView.addObject("employees", users);
		return modelAndView;
	}
}

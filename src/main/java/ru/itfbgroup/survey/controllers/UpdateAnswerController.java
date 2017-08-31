package ru.itfbgroup.survey.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import ru.itfbgroup.survey.configs.security.CustomLdapUserDetails;
import ru.itfbgroup.survey.models.User;
import ru.itfbgroup.survey.models.util.JSONParse;
import ru.itfbgroup.survey.service.abstr.AnswerService;
import ru.itfbgroup.survey.service.abstr.CategoryService;
import ru.itfbgroup.survey.service.abstr.UserService;

import java.io.UnsupportedEncodingException;
import java.util.List;

@Controller
@RequestMapping(value = "update-answer")
@SessionAttributes(value = "user")
public class UpdateAnswerController {

	@Autowired
	private AnswerService answerService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getUpdateAnswerPage() throws JsonProcessingException, UnsupportedEncodingException {
		ModelAndView modelAndView = new ModelAndView("update-answer-page");

		CustomLdapUserDetails userDetails = (CustomLdapUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.getUserByName(userDetails.getMail());

		if (user == null) {
			modelAndView.setViewName("redirect:answer");
			return modelAndView;
		}

		List<JSONParse> objects = answerService.getUserAnswerForJSON(user.getId());
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(objects);

		modelAndView.setViewName("update-answer-page");
		modelAndView.addObject("user", user);
		modelAndView.addObject("categoriesId", categoryService.getAllCategoriesId());
		modelAndView.addObject("answers", json);
		return modelAndView;
	}
}

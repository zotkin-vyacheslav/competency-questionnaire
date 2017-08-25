package ru.itfbgroup.questionnaire.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import ru.itfbgroup.questionnaire.models.Answer;
import ru.itfbgroup.questionnaire.models.User;
import ru.itfbgroup.questionnaire.models.util.JSONParse;
import ru.itfbgroup.questionnaire.service.abstr.AnswerService;
import ru.itfbgroup.questionnaire.service.abstr.CategoryService;
import ru.itfbgroup.questionnaire.service.abstr.UserService;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@Controller
@RequestMapping(value = "/update-answer")
@SessionAttributes(value = "user")
public class UpdateAnswerController {

	@Autowired
	private AnswerService answerService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private UserService userService;

	//TODO: rename
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getUserAnswer() throws JsonProcessingException, UnsupportedEncodingException {
		ModelAndView modelAndView = new ModelAndView("user-answer-page");

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User user = userService.getUserByName(userDetails.getUsername());

		if (user == null) {
			modelAndView.setViewName("redirect:/survey");
		}

		List<JSONParse> objects = answerService.getUserAnswerForJSON(user.getId());
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(objects);

		modelAndView.setViewName("user-answer-page");
		modelAndView.addObject("user", user);
		modelAndView.addObject("categoriesId", categoryService.getAllCategoriesId());
		modelAndView.addObject("answers", json);
		return modelAndView;
	}
}

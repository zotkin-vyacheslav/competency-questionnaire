package ru.itfbgroup.questionnaire.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import ru.itfbgroup.questionnaire.models.Answer;
import ru.itfbgroup.questionnaire.models.Category;
import ru.itfbgroup.questionnaire.models.util.JSONParse;
import ru.itfbgroup.questionnaire.models.User;
import ru.itfbgroup.questionnaire.service.abstr.AnswerService;
import ru.itfbgroup.questionnaire.service.abstr.CategoryService;

import java.time.LocalDateTime;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "rest")
@SessionAttributes(types = User.class)
public class RestController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private AnswerService answerService;

	@RequestMapping(value="/cat", method= RequestMethod.GET)
	public List<Category> getAllCategories(){
		return categoryService.getAllCategories();
	}

	@RequestMapping(value = "/cat/{id}", method = RequestMethod.GET)
	public Category getCategoryById(@PathVariable("id") long id){
		return categoryService.getCategoryById(id);
	}

	@RequestMapping(value = "/getAnswer", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String getAnswer(@RequestBody List<JSONParse> jsonParses,
										  @ModelAttribute User user,
										  SessionStatus status){
		Answer answer = user.getAnswer();
		answer.setLastTryDate(LocalDateTime.now());
		answerService.saveAnswers(answer, jsonParses);
		status.setComplete();
		return jsonParses.toString();
	}

	@RequestMapping(value = "/getStringAnswers", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String getAdditionalInfo(@RequestBody List<JSONParse> jsonParses,
												  @ModelAttribute User user){
		answerService.saveAdditionalInfo(user.getAnswer(), jsonParses);
		return jsonParses.toString();
	}
}

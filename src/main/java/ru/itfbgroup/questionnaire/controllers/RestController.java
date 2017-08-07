package ru.itfbgroup.questionnaire.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import ru.itfbgroup.questionnaire.models.Category;
import ru.itfbgroup.questionnaire.models.JSONParse;
import ru.itfbgroup.questionnaire.models.User;
import ru.itfbgroup.questionnaire.service.abstr.AnswerOptionService;
import ru.itfbgroup.questionnaire.service.abstr.CategoryService;

import java.util.List;
import java.util.Map;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "rest")
@SessionAttributes(types = User.class)
public class RestController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private AnswerOptionService answerOptionService;

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
		answerOptionService.saveAnswers(user.getnswer(), jsonParses);
		status.setComplete();
		return jsonParses.toString();
	}
}

package ru.itfbgroup.survey.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import ru.itfbgroup.survey.dao.abstr.AnswerDao;
import ru.itfbgroup.survey.models.Answer;
import ru.itfbgroup.survey.models.Category;
import ru.itfbgroup.survey.models.util.JSONParse;
import ru.itfbgroup.survey.models.User;
import ru.itfbgroup.survey.service.abstr.AnswerService;
import ru.itfbgroup.survey.service.abstr.CategoryService;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "rest")
@SessionAttributes(value = "user")
public class RestController {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private AnswerService answerService;

	@Autowired
	private AnswerDao answerDao;

	@RequestMapping(value="/cat", method= RequestMethod.GET)
	public List<Category> getAllCategories(){
		return categoryService.getAllCategories();
	}

	@RequestMapping(value = "/cat/{id}", method = RequestMethod.GET)
	public Category getCategoryById(@PathVariable("id") long id){
		return categoryService.getCategoryById(id);
	}

	@RequestMapping(value = "/getAnswer", method = RequestMethod.POST, produces = "application/json")
	public void getAnswer(@RequestBody List<JSONParse>[] jsonParses,
										  @ModelAttribute User user,
										  SessionStatus status){
		Answer answer = user.getAnswer();
		answerService.saveAdditionalInfo(answer, jsonParses[1]);
		answerService.saveOptionsUserAnswer(answer, jsonParses[0]);

		status.setComplete();
	}

	@RequestMapping(value = "/get-user-answer/{id}", method = RequestMethod.GET)
	public Answer getUserAnswer(@PathVariable("id") long id) {
		return answerService.getAnswerById(id);
	}


	@RequestMapping(value = "/get-user-answer-mod/{id}", method = RequestMethod.GET)
	public List<JSONParse> getUserAnswerMod(@PathVariable("id") long id) {
				List<JSONParse> objects = answerDao.getUserAnswerOptions(id);
				return objects;
	}
}

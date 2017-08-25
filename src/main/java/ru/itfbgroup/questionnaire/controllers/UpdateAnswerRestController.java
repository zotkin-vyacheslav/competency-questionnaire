package ru.itfbgroup.questionnaire.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;
import ru.itfbgroup.questionnaire.models.Answer;
import ru.itfbgroup.questionnaire.models.User;
import ru.itfbgroup.questionnaire.models.util.JSONParse;
import ru.itfbgroup.questionnaire.service.abstr.AnswerService;
import ru.itfbgroup.questionnaire.service.abstr.UserService;

import java.util.List;

@RestController
@RequestMapping(value = "/rest/update-answer")
@SessionAttributes(value = "user")
public class UpdateAnswerRestController {

	@Autowired
	private AnswerService answerService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/getAnswer", method = RequestMethod.POST, produces = "application/json")
	public void getAnswer(@RequestBody List<JSONParse>[] jsonParses,
						  @ModelAttribute User user,
						  SessionStatus status){
		Answer answer = new Answer();
		answer.setUser(user);
		user.setUserAnswer(answer);
		userService.updateUser(user);
		Answer answerFromDB = userService.getUserById(user.getId()).getAnswer();
		answerService.saveAdditionalInfo(answerFromDB, jsonParses[1]);
		answerService.saveOptionsUserAnswer(answerFromDB, jsonParses[0]);
		status.setComplete();
	}
}

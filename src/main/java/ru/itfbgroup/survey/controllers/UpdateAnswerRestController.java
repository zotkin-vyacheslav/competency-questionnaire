package ru.itfbgroup.survey.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.SessionStatus;
import ru.itfbgroup.survey.models.Answer;
import ru.itfbgroup.survey.models.User;
import ru.itfbgroup.survey.models.util.JSONParse;
import ru.itfbgroup.survey.service.abstr.AnswerService;
import ru.itfbgroup.survey.service.abstr.UserService;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping(value = "rest/update-answer")
@SessionAttributes(value = "user")
public class UpdateAnswerRestController {

	@Autowired
	private AnswerService answerService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/getAnswer", method = RequestMethod.POST, produces = "application/json")
	public void saveAnswer(@RequestBody List<JSONParse>[] jsonParses,
						   @ModelAttribute User user,
						   SessionStatus status) {
		Answer oldAnswer = user.getAnswer();
		oldAnswer.setActual(false);
		answerService.updateAnswer(oldAnswer);

		Answer answer = new Answer();
		answer.setUser(user);
		user.setUserAnswer(answer);
		userService.updateUser(user);
		Answer answerFromDB = userService.getUserById(user.getId()).getAnswer();
		answerService.saveAdditionalInfo(answerFromDB, jsonParses[1]);
		answerService.saveOptionsUserAnswer(answerFromDB, jsonParses[0]);
		status.setComplete();
	}

	@RequestMapping(value = "/getAdditionalAnswers", produces = "application/json")
	public List<JSONParse> getAdditionalAnswers(@ModelAttribute User user,
									 SessionStatus status) throws JsonProcessingException {

		return answerService.getAdditionalUserAnswerForJSON(user.getId());
	}
}

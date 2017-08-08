package ru.itfbgroup.questionnaire.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itfbgroup.questionnaire.dao.abstr.AnswerDao;
import ru.itfbgroup.questionnaire.dao.abstr.OptionDao;
import ru.itfbgroup.questionnaire.models.Answer;
import ru.itfbgroup.questionnaire.models.join.AnswerOption;
import ru.itfbgroup.questionnaire.models.JSONParse;
import ru.itfbgroup.questionnaire.service.abstr.AnswerOptionService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AnswerOptionServiceImpl implements AnswerOptionService {

	@Autowired
	private AnswerDao answerDao;

	@Autowired
	private OptionDao optionDao;

	@Override
	public void saveAnswers(Answer answer, List<JSONParse> jsonParses) {
		Set<AnswerOption> answerOptions = new HashSet<>();
		for (JSONParse jsonParse : jsonParses) {
			answerOptions.add(new AnswerOption(answer, optionDao.getByKey(Long.parseLong(jsonParse.getId())), jsonParse.getValue()));
		}
		answer.setAnswerOptions(answerOptions);
		answerDao.update(answer);

	}
}

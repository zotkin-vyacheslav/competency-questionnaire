package ru.itfbgroup.questionnaire.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itfbgroup.questionnaire.dao.abstr.AnswerDao;
import ru.itfbgroup.questionnaire.dao.abstr.OptionDao;
import ru.itfbgroup.questionnaire.dao.abstr.PossibleAnswerDao;
import ru.itfbgroup.questionnaire.dao.abstr.SubcategoryDao;
import ru.itfbgroup.questionnaire.models.Answer;
import ru.itfbgroup.questionnaire.models.join.AdditionalInfo;
import ru.itfbgroup.questionnaire.models.join.AnswerOption;
import ru.itfbgroup.questionnaire.models.util.JSONParse;
import ru.itfbgroup.questionnaire.service.abstr.AnswerService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AnswerServiceImpl implements AnswerService {

	@Autowired
	private AnswerDao answerDao;

	@Autowired
	private OptionDao optionDao;

	@Autowired
	private PossibleAnswerDao possibleAnswerDao;

	@Autowired
	private SubcategoryDao subcategoryDao;

	@Override
	public void saveAnswers(Answer answer, List<JSONParse> jsonParses) {
		Set<AnswerOption> answerOptions = new HashSet<>();
		for (JSONParse jsonParse : jsonParses) {
			answerOptions.add(new AnswerOption(optionDao.getByKey(Long.parseLong(jsonParse.getId())),
					possibleAnswerDao.getByKey(Long.parseLong(jsonParse.getValue()))));
		}
		answer.setAnswerOptions(answerOptions);
		answerDao.update(answer);

	}

	@Override
	public void saveAdditionalInfo(Answer answer, List<JSONParse> jsonParses) {
		Set<AdditionalInfo> additionalInfoSet = new HashSet<>();
		for (JSONParse jsonParse : jsonParses) {
			additionalInfoSet.add(new AdditionalInfo(subcategoryDao.getByKey(Long.parseLong(jsonParse.getId())),
					jsonParse.getValue()));
		}
		answer.setAdditionalInfoSet(additionalInfoSet);
		answerDao.update(answer);
	}
}

package ru.itfbgroup.questionnaire.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itfbgroup.questionnaire.dao.abstr.AnswerDao;
import ru.itfbgroup.questionnaire.dao.abstr.OptionDao;
import ru.itfbgroup.questionnaire.dao.abstr.PossibleAnswerDao;
import ru.itfbgroup.questionnaire.dao.abstr.SubcategoryDao;
import ru.itfbgroup.questionnaire.models.Answer;
import ru.itfbgroup.questionnaire.models.util.JSONParse;
import ru.itfbgroup.questionnaire.service.abstr.AnswerService;

import java.util.*;

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
//		List<AnswerOption> answerOptions = new ArrayList<>();
//		for (JSONParse jsonParse : jsonParses) {
//			answerOptions.add(new AnswerOption(optionDao.getByKey(Long.parseLong(jsonParse.getId())),
//					possibleAnswerDao.getByKey(Long.parseLong(jsonParse.getValue()))));
//		}
//		Collections.sort(answerOptions);
////		answer.setAnswerOptions(answerOptions);
//		Calendar currentTime = Calendar.getInstance();
//		answer.setTimestamp(new Date(currentTime.getTime().getTime()));
//		answerDao.update(answer);
	}

	@Override
	public void saveOptionsUserAnswer(Answer answer, List<JSONParse> jsonParses) {
		Long answerId = answer.getId();
		for (JSONParse jsonParse : jsonParses) {
			answerDao.saveOptionsAnswer(answerId, Long.parseLong(jsonParse.getId()),Long.parseLong(jsonParse.getValue()));
		}
		answerDao.updateAnswerDate(answerId);
	}

	@Override
	public void saveAdditionalInfo(Answer answer, List<JSONParse> jsonParses) {
		Long answerId = answer.getId();
		for (JSONParse jsonParse : jsonParses) {
			answerDao.saveAdditionalAnswer(answerId, Long.parseLong(jsonParse.getId()), jsonParse.getValue());
		}

		answerDao.update(answer);
	}

	@Override
	public Answer getAnswerById(Long id) {
		return answerDao.getByKey(id);
	}

	@Override
	public void saveAnswer(Answer answer) {
		answerDao.persist(answer);
	}

	@Override
	public List<JSONParse> getUserAnswerForJSON(Long userId) {
		return answerDao.getUserAnswerForShow(userId);
	}
}

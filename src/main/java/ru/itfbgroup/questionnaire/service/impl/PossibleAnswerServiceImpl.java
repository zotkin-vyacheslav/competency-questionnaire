package ru.itfbgroup.questionnaire.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itfbgroup.questionnaire.dao.abstr.PossibleAnswerDao;
import ru.itfbgroup.questionnaire.models.PossibleAnswer;
import ru.itfbgroup.questionnaire.service.abstr.PossibleAnswerService;

@Service
public class PossibleAnswerServiceImpl implements PossibleAnswerService {

	@Autowired
	private PossibleAnswerDao possibleAnswerDao;

	@Override
	public void savePossibleAnswer(PossibleAnswer possibleAnswer) {
		possibleAnswerDao.persist(possibleAnswer);
	}
}

package ru.itfbgroup.survey.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itfbgroup.survey.dao.abstr.PossibleAnswerDao;
import ru.itfbgroup.survey.models.PossibleAnswer;
import ru.itfbgroup.survey.service.abstr.PossibleAnswerService;

@Service
public class PossibleAnswerServiceImpl implements PossibleAnswerService {

	@Autowired
	private PossibleAnswerDao possibleAnswerDao;

	@Override
	public void savePossibleAnswer(PossibleAnswer possibleAnswer) {
		possibleAnswerDao.persist(possibleAnswer);
	}
}

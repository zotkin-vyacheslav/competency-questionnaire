package ru.itfbgroup.questionnaire.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itfbgroup.questionnaire.dao.abstr.AnswerDao;
import ru.itfbgroup.questionnaire.models.Answer;

@Repository
@Transactional
public class AnswerDaoImpl extends AbstractDao<Long, Answer> implements AnswerDao {
}

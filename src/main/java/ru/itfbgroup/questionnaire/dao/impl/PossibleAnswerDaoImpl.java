package ru.itfbgroup.questionnaire.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itfbgroup.questionnaire.dao.abstr.PossibleAnswerDao;
import ru.itfbgroup.questionnaire.models.PossibleAnswer;

@Repository
@Transactional
public class PossibleAnswerDaoImpl extends AbstractDao<Long,PossibleAnswer> implements PossibleAnswerDao {
}

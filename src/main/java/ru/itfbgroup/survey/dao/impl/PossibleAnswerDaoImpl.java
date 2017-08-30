package ru.itfbgroup.survey.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itfbgroup.survey.dao.abstr.PossibleAnswerDao;
import ru.itfbgroup.survey.models.PossibleAnswer;

@Repository
@Transactional
public class PossibleAnswerDaoImpl extends AbstractDao<Long,PossibleAnswer> implements PossibleAnswerDao {
}

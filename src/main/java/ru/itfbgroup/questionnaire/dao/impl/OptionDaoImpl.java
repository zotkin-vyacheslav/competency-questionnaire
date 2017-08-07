package ru.itfbgroup.questionnaire.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itfbgroup.questionnaire.dao.abstr.OptionDao;
import ru.itfbgroup.questionnaire.models.Option;

@Repository
@Transactional
public class OptionDaoImpl extends AbstractDao<Long, Option> implements OptionDao {
}

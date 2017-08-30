package ru.itfbgroup.survey.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itfbgroup.survey.dao.abstr.OptionDao;
import ru.itfbgroup.survey.models.Option;

@Repository
@Transactional
public class OptionDaoImpl extends AbstractDao<Long, Option> implements OptionDao {
}

package ru.itfbgroup.questionnaire.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itfbgroup.questionnaire.dao.abstr.SubcategoryDao;
import ru.itfbgroup.questionnaire.models.SubCategory;

@Repository
@Transactional
public class SubcategoryDaoImpl extends AbstractDao<Long, SubCategory> implements SubcategoryDao {
}

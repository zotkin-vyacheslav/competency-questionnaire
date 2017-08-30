package ru.itfbgroup.survey.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itfbgroup.survey.dao.abstr.SubcategoryDao;
import ru.itfbgroup.survey.models.SubCategory;

@Repository
@Transactional
public class SubcategoryDaoImpl extends AbstractDao<Long, SubCategory> implements SubcategoryDao {
}

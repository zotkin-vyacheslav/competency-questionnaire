package ru.itfbgroup.survey.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itfbgroup.survey.dao.abstr.RoleDao;
import ru.itfbgroup.survey.models.Role;

@Repository
@Transactional
public class RoleDaoImpl extends AbstractDao<Long, Role> implements RoleDao{
	@Override
	public Role getRoleByName(String name) {
		return (Role) entityManager.createQuery("select r from Role r where r.authority =:name")
				.setParameter("name", name)
				.getSingleResult();
	}
}

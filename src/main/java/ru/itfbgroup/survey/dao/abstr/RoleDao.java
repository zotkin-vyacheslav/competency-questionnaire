package ru.itfbgroup.survey.dao.abstr;

import ru.itfbgroup.survey.models.Role;

public interface RoleDao extends GenericDao<Long, Role> {

	Role getRoleByName(String name);
}

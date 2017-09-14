package ru.itfbgroup.survey.service.abstr;

import ru.itfbgroup.survey.models.Role;

public interface RoleService {

	void addRole(Role role);

	Role getRoleByName(String name);
}

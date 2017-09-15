package ru.itfbgroup.survey.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itfbgroup.survey.dao.abstr.OptionDao;
import ru.itfbgroup.survey.models.Option;
import ru.itfbgroup.survey.service.abstr.OptionService;

import java.util.List;

@Service
public class OptionServiceImpl implements OptionService {

	@Autowired
	private OptionDao optionDao;

	@Override
	public List<Option> getAllOptions() {
		return optionDao.getAll();
	}
}

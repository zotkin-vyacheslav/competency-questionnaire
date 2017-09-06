package ru.itfbgroup.survey.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.itfbgroup.survey.service.abstr.AnswerService;

import java.util.List;

@RestController
public class StatisticsRestController {

	@Autowired
	private AnswerService answerService;

	@RequestMapping("list")
	public List greeting(@RequestParam long categoryId) {
		List list = answerService.getDataForStatistics(categoryId);
		return list;
	}
}

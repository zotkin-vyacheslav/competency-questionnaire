package ru.itfbgroup.survey.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.itfbgroup.survey.service.abstr.CategoryService;

@Controller
public class StatisticsController {

	@Autowired
	private CategoryService categoryService;

	@RequestMapping(value = "statistics")
	public ModelAndView getStatisticsPage() {

		ModelAndView modelAndView = new ModelAndView("statistics");
		modelAndView.addObject("categoriesId", categoryService.getAllCategoriesId());
		return modelAndView;
	}
}

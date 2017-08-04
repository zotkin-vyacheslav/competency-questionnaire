package ru.itfbgroup.questionnaire.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.itfbgroup.questionnaire.models.Category;
import ru.itfbgroup.questionnaire.service.abstr.CategoryService;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "rest")
public class RestController {

	@Autowired
	private CategoryService categoryService;

	@RequestMapping(path="/cat", method= RequestMethod.GET)
	public List<Category> getAllCategories(){
		return categoryService.getAllCategories();
	}

	@RequestMapping(value = "/cat/{id}", method = RequestMethod.GET)
	public Category getCategoryById(@PathVariable("id") long id){
		return categoryService.getCategoryById(id);
	}
}

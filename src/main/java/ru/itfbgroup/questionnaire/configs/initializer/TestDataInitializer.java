package ru.itfbgroup.questionnaire.configs.initializer;

import org.springframework.beans.factory.annotation.Autowired;
import ru.itfbgroup.questionnaire.models.Category;
import ru.itfbgroup.questionnaire.models.Option;
import ru.itfbgroup.questionnaire.models.SubCategory;
import ru.itfbgroup.questionnaire.models.enums.AnswerOptionsEnum;
import ru.itfbgroup.questionnaire.service.abstr.CategoryService;

import java.util.HashSet;
import java.util.Set;

public class TestDataInitializer {

	@Autowired
	private CategoryService categoryService;

	public void startInit() {
		Option option1 = new Option(AnswerOptionsEnum.NO, "java", "");
		Option option2 = new Option(AnswerOptionsEnum.NO, "c", "");
		Option option3 = new Option(AnswerOptionsEnum.NO, "js", "");

		Option option4 = new Option(AnswerOptionsEnum.NO, "Oracle", "");
		Option option5 = new Option(AnswerOptionsEnum.NO, "MySQL", "");
		Option option6 = new Option(AnswerOptionsEnum.NO, "PostgreSQL", "");

		Set<Option> options = new HashSet<>();
		options.add(option1);
		options.add(option2);
		options.add(option3);

		Set<Option> dbOptions = new HashSet<>();
		dbOptions.add(option4);
		dbOptions.add(option5);
		dbOptions.add(option6);

		SubCategory subCategory = new SubCategory("langs", options);
		SubCategory dbDev = new SubCategory("develop", dbOptions);
		SubCategory dbAdmin = new SubCategory("admin", dbOptions);

		Set<SubCategory> subCategories = new HashSet<>();
		subCategories.add(subCategory);

		Set<SubCategory> dbSubCategories = new HashSet<>();
		dbSubCategories.add(dbDev);
//		dbSubCategories.add(dbAdmin);

		Category category = new Category("PROG", subCategories);
		Category dbCategory = new Category("DB", dbSubCategories);

		categoryService.addCategory(category);
		categoryService.addCategory(dbCategory);

	}

	private void init() {
		startInit();
	}
 }

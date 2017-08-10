package ru.itfbgroup.questionnaire.configs.initializer;

import org.springframework.beans.factory.annotation.Autowired;
import ru.itfbgroup.questionnaire.dao.abstr.PossibleAnswerDao;
import ru.itfbgroup.questionnaire.models.Category;
import ru.itfbgroup.questionnaire.models.Option;
import ru.itfbgroup.questionnaire.models.SubCategory;
import ru.itfbgroup.questionnaire.models.util.PossibleAnswer;
import ru.itfbgroup.questionnaire.service.abstr.CategoryService;

import java.util.*;

public class TestDataInitializer {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private PossibleAnswerDao possibleAnswerDao;

	private void addMiddleware() {
		Option option1 = new Option("Oracle Weblogic Server");
		Option option2 = new Option("IBM WebSphere Application Server");
		Option option3 = new Option("WildFly / JBoss");
		Option option4 = new Option("TomEE");
		Option option5 = new Option("Glassfish");
		Option option6 = new Option("SAP NetWeaver Application Server");
		Option option7 = new Option("Microsoft IIS");
		Option option8 = new Option("Oracle Tuxedo");

		Option[] middlewaresApplicationServersOptions = {option1, option2, option3, option4, option5, option6, option7, option8};
		List<Option> mwASOptions = new LinkedList<>(Arrays.asList(middlewaresApplicationServersOptions));

		SubCategory middlewareSC = new SubCategory("Application Servers", mwASOptions, "Понимается администрирование: установка, конфигурирование (кластеризация, высокая доступность и т.п.), обслуживание и автоматизация задач администрирования.");

		List<SubCategory> middlewareSubCategories = new LinkedList<>();
		middlewareSubCategories.add(middlewareSC);

		Category middlewareCategory = new Category("Middleware", middlewareSubCategories);
		categoryService.addCategory(middlewareCategory);
	}

	private void addDb() {
		Option option4 = new Option("Oracle Database");
		Option option5 = new Option("Oracle TimesTen");
		Option option6 = new Option("MS SQL Server");
		Option option7 = new Option("IBM DB2");
		Option option8 = new Option("IBM Informix");
		Option option9 = new Option("PostgreSQL");
		Option option10 = new Option("MySQL / MariaDB");
		Option option11= new Option("Interbase / Firebird");

		Option[] dbs = {option4, option5, option6, option7, option8, option9, option10, option11};

		Option option12 = new Option("Cassandra");
		Option option13 = new Option("HBase");
		Option option14 = new Option("Druid");
		Option option15 = new Option("MongoDB");
		Option option16 = new Option("CouchDB");
		Option option17 = new Option("ArangoDB");
		Option option18 = new Option("Oracle NoSQL Database / Berkley DB");
		Option option19 = new Option("MarkLogic");
		Option option20 = new Option("Couchbase");

		Option[] addevOptions = {option12, option13, option14, option15, option16, option17, option18, option19, option20};

		List<Option> dbOptions = new LinkedList<>();
		dbOptions.addAll(Arrays.asList(dbs));

		List<Option> adddevOptionsSet = new LinkedList<>();
		adddevOptionsSet.addAll(Arrays.asList(addevOptions));

		SubCategory dbDevSC = new SubCategory("Разработка", dbOptions, "Реляционные базы данных.");
		SubCategory dbAdminSC = new SubCategory("Администрирование", dbOptions, "Реляционные базы данных.");
		SubCategory addevSC = new SubCategory("Разработка и администрирование", adddevOptionsSet, "NoSQL и нереляционные базы данных.");

		List<SubCategory> dbSubCategories = new LinkedList<>();
		dbSubCategories.add(dbDevSC);
		dbSubCategories.add(addevSC);
//		dbSubCategories.add(dbAdminSC);

		Category dbCategory = new Category("Базы данных", dbSubCategories);

		categoryService.addCategory(dbCategory);
	}

	private void startInit() {
		Option option1 = new Option("Java");
		Option option2 = new Option("Python");
		Option option3 = new Option("Java Script");
		Option option11 = new Option("С/С++");
		Option option12 = new Option("С#");
		Option option13 = new Option("Objective-C");

		Option[] langs = {option1, option2, option3, option11, option12, option13};

		List<Option> options = new LinkedList<>();
		options.addAll(Arrays.asList(langs));

		SubCategory subCategory = new SubCategory(" ", options);

		List<SubCategory> subCategories = new LinkedList<>();
		subCategories.add(subCategory);

		Category category = new Category("Языки программирования", subCategories);

		categoryService.addCategory(category);
	}

	private void addPossibleAnswers() {
		PossibleAnswer possibleAnswer1 = new PossibleAnswer("Нет");
		PossibleAnswer possibleAnswer2 = new PossibleAnswer("Начальные знания");
		PossibleAnswer possibleAnswer3 = new PossibleAnswer("Базовые знания");
		PossibleAnswer possibleAnswer4 = new PossibleAnswer("Продвинутые знания");

		possibleAnswerDao.persist(possibleAnswer1);
		possibleAnswerDao.persist(possibleAnswer2);
		possibleAnswerDao.persist(possibleAnswer3);
		possibleAnswerDao.persist(possibleAnswer4);

	}

	private void init() {
		addPossibleAnswers();
		startInit();
		addDb();
		addMiddleware();

	}
 }

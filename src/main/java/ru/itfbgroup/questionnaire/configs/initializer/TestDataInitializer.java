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

		Option option9 = new Option("Oracle SOA Suite");
		Option option10 = new Option("Oracle Service Bus");
		Option option11 = new Option("IBM Integration Bus");
		Option option12 = new Option("Tibco BusinessWorks");
		Option option13 = new Option("Software AG webMethods");
		Option option14 = new Option("Mule ESB");
		Option option15 = new Option("WSO2 Integration");
		Option option16 = new Option("SAP NetWeaver Process Integration");
		Option option17 = new Option("Red Hat FUSE");
		Option option18 = new Option("Talend ESB");
		Option option19 = new Option("ServiceMix");

		Option[] sysIntegration = {option9, option10, option11, option12, option13, option14, option15, option16,
				option17, option18, option19};
		List<Option> mwSysIntegr = new LinkedList<>(Arrays.asList(sysIntegration));

		Option option20 = new Option("Oracle Data Integrator");
		Option option21 = new Option("Oracle GoldenGate");
		Option option22 = new Option("IBM DataStage");
		Option option23 = new Option("Informatica PowerCenter");
		Option option24 = new Option("Microsoft SQL Server Integration Services (SSIS)");
		Option option25 = new Option("SAP Data Services");
		Option option26 = new Option("SAS Data Managemen");
		Option option27 = new Option("Pentaho Data Integration");
		Option option28 = new Option("Talend Data Integration");

		Option[] dataIntegration = {option20, option21, option22, option23, option24, option25, option26,
				option27, option28};
		List<Option> mwDataIntegr = new LinkedList<>(Arrays.asList(dataIntegration));

		Option option208 = new Option("Pegasystems BPM Suite");
		Option option29 = new Option("Appian Platform");
		Option option30 = new Option("Software AG webMethods BPM Platform");
		Option option31 = new Option("Oracle BPM Suite");
		Option option32 = new Option("IBM BPM");
		Option option33 = new Option("OpenText BPM Suite");
		Option option34 = new Option("TIBCO ActiveMatrix BPM");
		Option option35 = new Option("Bonita BPM");
		Option option36 = new Option("Alfresco Process Services");
		Option option37 = new Option("Bizagi BPM Suite");
		Option option38 = new Option("JBoss BPM Suite");
		Option option39 = new Option("Activiti BPMN Platform");

		Option[] bpm = {option208, option29, option30, option31, option32, option33, option34,
				option35, option36, option37, option38, option39};
		List<Option> mwBPM = new LinkedList<>(Arrays.asList(bpm));

		Option option40 = new Option("TIBCO Mashery");
		Option option41 = new Option("Oracle API Management");
		Option option42 = new Option("Mule Anypoint");
		Option option43 = new Option("Apigee Edge (куплена Google)");
		Option option44 = new Option("Apiary (куплена Oracle");
		Option option45 = new Option("WSO2 API Manager");
		Option option46 = new Option("IBM API Connect");
		Option option47 = new Option("CA API Management");
		Option option48 = new Option("Red Hat 3scale API Management");

		Option[] apiManag = {option40, option41, option42, option43, option44, option45, option46,
				option47, option48};
		List<Option> mwApiManag= new LinkedList<>(Arrays.asList(apiManag));

		Option option49 = new Option("IBM WebSphere MQ");
		Option option50 = new Option("RabbitMQ");
		Option option51 = new Option("StormMQ");
		Option option52 = new Option("ActiveMQ");

		Option[] apiMOM = {option49, option50, option51, option52};
		List<Option> mwMom= new LinkedList<>(Arrays.asList(apiMOM));

		Option option53 = new Option("Oracle Identity Governance Suite");
		Option option54 = new Option("Oracle Access Management Suite");
		Option option55 = new Option("SailPoint IdentityIQ");
		Option option56 = new Option("CA Identity Suite");
		Option option57 = new Option("CA Single Sign-On (Siteminder)");
		Option option58 = new Option("SAP Identity Management & Access Control");
		Option option59 = new Option("Microsoft Identity Manager (FIM)");
		Option option60 = new Option("IBM Security Identity Governance and Intelligence");
		Option option61 = new Option("IBM Security Access Manager");
		Option option62 = new Option("Dell One Identity Manager & RSA Identity Governance and Lifecycle");
		Option option63 = new Option("NetIQ Identity Manager & Identity Governance");
		Option option64 = new Option("Saviynt Security Manager");
		Option option65 = new Option("Evolveum MidPoint");
		Option option66 = new Option("Apache Syncope");

		Option[] apiIdentity = {option53, option54, option55, option56, option57, option58, option59, option60, option61, option62, option63, option64, option65, option66};
		List<Option> mwIdentity= new LinkedList<>(Arrays.asList(apiIdentity));

		Option option606 = new Option("Oracle WebCenter Content");
		Option option67 = new Option("IBM Content Foundation (FileNet)");
		Option option68 = new Option("Alfresco Content Services");
		Option option69 = new Option("OpenText Documentum");
		Option option70 = new Option("OpenText Content Suite");
		Option option71 = new Option("Microsoft Sharepoint");
		Option option72 = new Option("SAP ECM");
		Option option73 = new Option("Kofax ECM");
		Option option74 = new Option("Directum");

		Option[] enterpriseCont = {option606, option67, option68, option69, option70, option71, option72, option73, option74};
		List<Option> mwEneterprise= new LinkedList<>(Arrays.asList(enterpriseCont));

		Option option75 = new Option("Oracle BI EE");
		Option option76 = new Option("Oracle Hyperion Essbase");
		Option option77 = new Option("QlikView");
		Option option78 = new Option("Qlik Sense");
		Option option79 = new Option("Microsoft Reporting Services & Analysis Services");
		Option option80 = new Option("Microsoft Power BI Suite");
		Option option81 = new Option("IBM Cognos Analytics");
		Option option82 = new Option("IBM Watson Analytics");
		Option option83 = new Option("SAP BusinessObjects");
		Option option84 = new Option("SAS Visual Analytics");
		Option option85 = new Option("Tableau");
		Option option86 = new Option("Pentaho Business Analytics");
		Option option87 = new Option("TIBCO JasperSoft BI");
		Option option88 = new Option("SpagoBI");

		Option[] businessIntellegence = {option75, option76, option77, option78, option79, option80, option81, option82,
				option83, option84, option85, option86, option87, option88};
		List<Option> mwBusiness= new LinkedList<>(Arrays.asList(businessIntellegence));

		Option option89 = new Option("ABBYY FlexiCapture");
		Option option90 = new Option("ABBYY Recognition Server");
		Option option91 = new Option("OpenText Capture Center");
		Option option92 = new Option("OpenText Captiva");
		Option option93 = new Option("Ephesoft Transact");
		Option option94 = new Option("Kofax Capture");
		Option option95 = new Option("Smart Engines (Smart IDReader)");

		Option[] ocp = {option89, option90, option91, option92, option93, option94, option95};
		List<Option> mwOcp= new LinkedList<>(Arrays.asList(ocp));

		SubCategory middlewareSC = new SubCategory("Application Servers", mwASOptions, "Понимается администрирование: установка, конфигурирование (кластеризация, высокая доступность и т.п.), обслуживание и автоматизация задач администрирования.");
		SubCategory middlewareSysIntegr = new SubCategory("System integration", mwSysIntegr, " ");
		SubCategory middlewareDataIntegr = new SubCategory("Data integration (ETL)", mwDataIntegr, " ");
		SubCategory middlewareBPM = new SubCategory("Business Process Management & Adaptive Case Management", mwBPM, " ");
		SubCategory middlewareAPIManag = new SubCategory("API Management", mwApiManag, " ");
		SubCategory middlewareMOM = new SubCategory("Message Oriented Middleware (MOM)", mwMom, " ");
		SubCategory middlewareIdentyty = new SubCategory("Identity & Access Management", mwIdentity, " ");
		SubCategory middlewareEneterPriseContent = new SubCategory("Enterprise Content Management", mwEneterprise, " ");
		SubCategory middlewareBusiness = new SubCategory("Business Intelligence", mwBusiness, " ");
		SubCategory middlewareOCR = new SubCategory("OCR & ICR & IDR", mwOcp, "Optical character recognition (OCR), Intelligent character recognition (ICR), Intelligent document recognition (IDR).");

		List<SubCategory> middlewareSubCategories = new LinkedList<>();
		middlewareSubCategories.add(middlewareSC);
		middlewareSubCategories.add(middlewareSysIntegr);
		middlewareSubCategories.add(middlewareDataIntegr);
		middlewareSubCategories.add(middlewareBPM);
		middlewareSubCategories.add(middlewareAPIManag);
		middlewareSubCategories.add(middlewareMOM);
		middlewareSubCategories.add(middlewareIdentyty);
		middlewareSubCategories.add(middlewareEneterPriseContent);
		middlewareSubCategories.add(middlewareBusiness);
		middlewareSubCategories.add(middlewareOCR);

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
		Option option13 = new Option("Swift");
		Option option14 = new Option("Go");
		Option option15 = new Option("Kotlin");
		Option option16 = new Option("Objective-C");
		Option option17 = new Option("D");
		Option option18 = new Option("R");
		Option option19 = new Option("ABAP");
		Option option20 = new Option("Perl");
		Option option21 = new Option("Haskell");
		Option option22 = new Option("Erlang");
		Option option23 = new Option("Rust");
		Option option24 = new Option("Lua");
		Option option25 = new Option("Scala");
		Option option26 = new Option("Clojure");
		Option option27 = new Option("Object Pascal / Pascal");
		Option option28 = new Option("ColdFusion");
		Option option29 = new Option("Ada");
		Option option30 = new Option("Assembler");

		Option[] langs = {option1, option2, option3, option11, option12, option13, option14, option15, option16,
				option17, option18, option19, option20, option21, option22, option23, option24, option25, option26,
				option27, option28, option29, option30};

		List<Option> options = new LinkedList<>();
		options.addAll(Arrays.asList(langs));

		SubCategory subCategory = new SubCategory(" ", options, "ПОЯСНЕНИЕ: в случае невыбранного значения в строке подразумевается значение \"Нет\". Поэтому можно заполнять только строки в которых у Вас будет значение отличается от \"Нет\". Данное пояснение касается не только этого вопроса, но и всех последующих.");

		List<SubCategory> subCategories = new LinkedList<>();
		subCategories.add(subCategory);

		Category category = new Category("Языки программирования", subCategories, "");

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

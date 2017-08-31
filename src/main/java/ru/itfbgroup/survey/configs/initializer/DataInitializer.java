package ru.itfbgroup.survey.configs.initializer;

import org.springframework.beans.factory.annotation.Autowired;
import ru.itfbgroup.survey.models.Category;
import ru.itfbgroup.survey.models.Option;
import ru.itfbgroup.survey.models.SubCategory;
import ru.itfbgroup.survey.models.PossibleAnswer;
import ru.itfbgroup.survey.service.abstr.CategoryService;
import ru.itfbgroup.survey.service.abstr.PossibleAnswerService;

import java.util.*;

public class DataInitializer {

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private PossibleAnswerService possibleAnswerService;

	private void init() {
		addPossibleAnswers();
		addProgramLanguages();
		addDb();
		addMiddleware();
		addFrontend();
		addBackendTech();
		addMobile();
		addCloud();
		addAnotherTech();
	}

	private void addAnotherTech() {

		Option option1 = new Option("DataStax Enterprise");
		Option option2 = new Option("Amazon DynamoDB and ElastiCache");
		Option option3 = new Option("Couchbase");
		Option option4 = new Option("DataStax Enterprise");
		Option option5 = new Option("Google Cloud Datastore");
		Option option6 = new Option("IBM Cloudant");
		Option option7 = new Option("MapR-DB");
		Option option8 = new Option("MarkLogic");
		Option option9 = new Option("Azure DocumentDB");
		Option option10 = new Option("Neo4j");
		Option option11 = new Option("Redis Labs Enterprise Cluster");
		Option option12 = new Option("Oracle NoSQL Database");

		Option[] bigData = {option1, option2, option3, option4, option5, option6, option7, option8, option9,
				option10, option11, option12};
		List<Option> bigDataList = new LinkedList<>(Arrays.asList(bigData));

		Option option13 = new Option("UIPath");
		Option option14 = new Option("Automation Anywhere");
		Option option16 = new Option("Blue Prism");
		Option option17 = new Option("NICE Robotic Automation");
		Option option18 = new Option("Pega Robotic Automation & Intelligence");
		Option option19 = new Option("Kofax Kapow");

		Option[] robotic = {option13, option14, option16, option17, option18, option19};
		List<Option> roboticList = new LinkedList<>(Arrays.asList(robotic));

		Option option20 = new Option("Azure BaaS (Blockchain as a Service)");
		Option option21 = new Option("IBM Bluemix Blockchain");
		Option option22 = new Option("Ethereum");
		Option option23 = new Option("Eris");
		Option option24 = new Option("Multichain");
		Option option25 = new Option("Hyperledger");
		Option option26 = new Option("Chain");
		Option option28 = new Option("Openchain");
		Option option29 = new Option("HydraChain");

		Option[] blockChain = {option20, option21, option22, option23, option24, option25, option26, option28, option29};
		List<Option> blockChainList = new LinkedList<>(Arrays.asList(blockChain));

		Option option30 = new Option("IBM Watson IoT Platform");
		Option option31 = new Option("AWS IoT");
		Option option32 = new Option("SAP Hana Cloud Platform IoT Service");
		Option option33 = new Option("PTC ThingWorx");
		Option option34 = new Option("Azure IoT Suite");
		Option option35 = new Option("GE Predix");
		Option option36 = new Option("Oracle IoT Cloud Service");

		Option[] iot = {option30, option31, option32, option33, option34, option35, option36};
		List<Option> iotList = new LinkedList<>(Arrays.asList(iot));

		SubCategory bigDataSC = new SubCategory("Big Data NoSQL", bigDataList, "ПОЯСНЕНИЕ: в случае невыбранного значения в строке подразумевается значение \"Нет\".");
		SubCategory roboticSC = new SubCategory("Robotic Process Automation (RPA)", roboticList, "ПОЯСНЕНИЕ: в случае невыбранного значения в строке подразумевается значение \"Нет\".");
		SubCategory blockChainSC = new SubCategory("Block chain technology", blockChainList, "ПОЯСНЕНИЕ: в случае невыбранного значения в строке подразумевается значение \"Нет\".");
		SubCategory iotSC = new SubCategory("IoT software and technology", iotList, "ПОЯСНЕНИЕ: в случае невыбранного значения в строке подразумевается значение \"Нет\".");
		SubCategory anotherSC = new SubCategory("Другие технологии", null, null);

		List<SubCategory> anotherCategories = new LinkedList<>();
		anotherCategories.add(bigDataSC);
		anotherCategories.add(roboticSC);
		anotherCategories.add(blockChainSC);
		anotherCategories.add(iotSC);
		anotherCategories.add(anotherSC);

		Category anotherCategory = new Category("Другие технологии", anotherCategories);
		anotherCategory.setImage("resources/static/images/bigstock-Big-data.jpg");
		categoryService.addCategory(anotherCategory);
	}

	private void addCloud() {

		Option option1 = new Option("Amazon Web Services");
		Option option2 = new Option("Microsoft Azure");
		Option option3 = new Option("Google Cloud Platform (GCP)");
		Option option4 = new Option("IBM (SoftLayer)");
		Option option5 = new Option("Oracle Cloud");
		Option option6 = new Option("Rackspace");
		Option option7 = new Option("DigitalOcean");

		Option[] infrastruct = {option1, option2, option3, option4, option5, option6, option7};
		List<Option> infrastructList = new LinkedList<>(Arrays.asList(infrastruct));

		Option option8 = new Option("Dell Boomi");
		Option option9 = new Option("Informatica Cloud Integration");
		Option option10 = new Option("Oracle Cloud Platform");
		Option option11 = new Option("IBM Application Integration Suite on Cloud");
		Option option12 = new Option("Microsoft Logic Apps");
		Option option13 = new Option("MuleSoft AnyPoint");
		Option option14 = new Option("SAP Cloud Platform");
		Option option15 = new Option("SnapLogic Enterprise Integration Cloud");

		Option[] eneterpiseIntegr = {option8, option9, option10, option11, option12, option13, option14, option15};
		List<Option> enterpriseList = new LinkedList<>(Arrays.asList(eneterpiseIntegr));

		SubCategory infractructSC = new SubCategory("Infrastructure As a Service (IaaS)", infrastructList, "ПОЯСНЕНИЕ: в случае невыбранного значения в строке подразумевается значение \"Нет\".");
		SubCategory enterpriseSC = new SubCategory("Enterprise Integration Platform as a Service", enterpriseList, "ПОЯСНЕНИЕ: в случае невыбранного значения в строке подразумевается значение \"Нет\".");

		List<SubCategory> cloudSubCategories = new LinkedList<>();
		cloudSubCategories.add(infractructSC);
		cloudSubCategories.add(enterpriseSC);

		Category cloudCategory = new Category("Облачные платформы", cloudSubCategories);
		cloudCategory.setImage("resources/static/images/CloudComputing.jpg");
		categoryService.addCategory(cloudCategory);
	}

	private void addMobile() {
		Option option1 = new Option("iOS SDK - Swift");
		Option option2 = new Option("iOS SDK - Object C");
		Option option3 = new Option("Android SDK");
		Option option4 = new Option("Xamarin");
		Option option5 = new Option("Windows 10 SDK");
		Option option6 = new Option("Kony Mobility Platform");
		Option option7 = new Option("Corona SDK");
		Option option8 = new Option("Oracle Mobile Application Framework (MAF)");
		Option option9 = new Option("SAP Mobile Platform");
		Option option10 = new Option("IBM MobileFirst Platform Foundation");
		Option option11 = new Option("Red Hat Mobile Application Platform");

		Option[] mobileTech = {option1, option2, option3, option4, option5, option6, option7, option8, option9,
				option10, option11};
		List<Option> mobileList = new LinkedList<>(Arrays.asList(mobileTech));

		SubCategory mobileSC = new SubCategory("JavaScript-фреймворки", mobileList, "ПОЯСНЕНИЕ: в случае невыбранного значения в строке подразумевается значение \"Нет\".");

		List<SubCategory> mobileSubCategories = new LinkedList<>();
		mobileSubCategories.add(mobileSC);

		Category mobileCategory = new Category("Разработка под мобильные устройства", mobileSubCategories);
		mobileCategory.setImage("resources/static/images/mobile.jpg");
		categoryService.addCategory(mobileCategory);
	}

	private void addFrontend() {
		Option option1 = new Option("JQuery");
		Option option2 = new Option("Angular 2");
		Option option3 = new Option("Angular 4");
		Option option4 = new Option("React");
		Option option5 = new Option("Vue");
		Option option6 = new Option("Backbone");
		Option option7 = new Option("Knockout");
		Option option8 = new Option("Ember");
		Option option9 = new Option("Express");
		Option option10 = new Option("D3");
		Option option11 = new Option("Meteor");
		Option option12 = new Option("Polymer");
		Option option13 = new Option("Dojo");
		Option option14 = new Option("ExtJS");
		Option option15 = new Option("Socket");

		Option[] jsFrameworks = {option1, option2, option3, option4, option5, option6, option7, option8, option9,
				option10, option11, option12, option13, option14, option15};
		List<Option> jsFrameworksList = new LinkedList<>(Arrays.asList(jsFrameworks));

		Option option16 = new Option("Boilerplate");
		Option option17 = new Option("Foundation");
		Option option18 = new Option("Bootstrap");
		Option option19 = new Option("Skeleton");
		Option option20 = new Option("Jade");
		Option option21 = new Option("Hogan");
		Option option22 = new Option("Handlebars");
		Option option23 = new Option("mustache.js");
		Option option24 = new Option("MNunjucks");
		Option option25 = new Option("Dust.js");
		Option option26 = new Option("Swig");
		Option option27 = new Option("Jinja2");

		Option[] tempEnines = {option16, option17, option18, option19, option20, option21, option22, option23, option24, option25, option26,
				option27};
		List<Option> tempEnginesLIst = new LinkedList<>(Arrays.asList(tempEnines));

		Option option28 = new Option("Gulp");
		Option option29 = new Option("Grunt");
		Option option30 = new Option("Prepros");
		Option option31 = new Option("Codekit");
		Option option32 = new Option("Webpack");

		Option[] collectors = {option28, option29, option30, option31, option32};
		List<Option> collectorsLIst = new LinkedList<>(Arrays.asList(collectors));

		SubCategory jsFrameworksSC = new SubCategory("JavaScript-фреймворки", jsFrameworksList, "ПОЯСНЕНИЕ: в случае невыбранного значения в строке подразумевается значение \"Нет\".");
		SubCategory tempEnginesSC = new SubCategory("Шаблонизаторы", tempEnginesLIst, "ПОЯСНЕНИЕ: в случае невыбранного значения в строке подразумевается значение \"Нет\".");
		SubCategory collectorsSC = new SubCategory("Сборщики", collectorsLIst, "ПОЯСНЕНИЕ: в случае невыбранного значения в строке подразумевается значение \"Нет\".");

		List<SubCategory> frontendSubCategories = new LinkedList<>();
		frontendSubCategories.add(jsFrameworksSC);
		frontendSubCategories.add(tempEnginesSC);
		frontendSubCategories.add(collectorsSC);

		Category frontendCategory = new Category("Frontend-технологии", frontendSubCategories);
		frontendCategory.setImage("resources/static/images/front-end-skills.png");
		categoryService.addCategory(frontendCategory);
	}

	private void addBackendTech() {
		Option option1 = new Option("Servlet / JSP");
		Option option2 = new Option("JSF");
		Option option3 = new Option("JAX-RS");
		Option option4 = new Option("JAX-WS");
		Option option5 = new Option("WebSocket");
		Option option6 = new Option("JSON-P");
		Option option7 = new Option("EJB");
		Option option8 = new Option("JTA");
		Option option9 = new Option("JPA");
		Option option10 = new Option("JMS");
		Option option11 = new Option("JCA");
		Option option12 = new Option("CDI");
		Option option13 = new Option("StAX");
		Option option14 = new Option("JAAS");

		Option[] javaEE = {option1, option2, option3, option4, option5, option6, option7, option8, option9,
				option10, option11, option12, option13, option14};
		List<Option> javaEEList = new LinkedList<>(Arrays.asList(javaEE));

		Option option16 = new Option("Spring Core Container");
		Option option17 = new Option("IoC");
		Option option18 = new Option("AOP");
		Option option19 = new Option("Instrumentation");
		Option option20 = new Option("Messaging");
		Option option21 = new Option("JDBC");
		Option option22 = new Option("ORM");
		Option option23 = new Option("OXM");
		Option option24 = new Option("Transaction management");
		Option option25 = new Option("Web (mvc, websocket, portlet)");
		Option option26 = new Option("Testing");

		Option[] spring = {option16, option17, option18, option19, option20, option21, option22, option23, option24, option25, option26};
		List<Option> springLIst = new LinkedList<>(Arrays.asList(spring));

		Option option28 = new Option("WCF");
		Option option29 = new Option("WPF");
		Option option30 = new Option("WWF");
		Option option31 = new Option("WIF");
		Option option32 = new Option("Entity Framework");
		Option option33 = new Option("ASP.NET MVC 3");
		Option option34 = new Option("LINQ to SQL");

		Option[] dotNet = {option28, option29, option30, option31, option32, option33, option34};
		List<Option> dotNetLIst = new LinkedList<>(Arrays.asList(dotNet));

		Option option35 = new Option("Oracle ADF");
		Option option36 = new Option("Grails");
		Option option37 = new Option("Spring Boot");
		Option option38 = new Option("Play / Play 2 Framework");
		Option option39 = new Option("Vaadin");
		Option option40 = new Option("Struts / Struts 2");
		Option option41 = new Option("GWT");
		Option option42 = new Option("Oracle Tuxedo");
		Option option43 = new Option("Node.js");
		Option option44 = new Option("Ruby on Rails");
		Option option45 = new Option("Django");
		Option option47 = new Option("Zend Framework");
		Option option48 = new Option("Yii Framework");
		Option option49 = new Option("N20");
		Option option50 = new Option("Flask");
		Option option51 = new Option("Laravel");

		Option[] another = {option35, option36, option37, option38, option39, option40, option41, option42, option43,
				option44, option45, option47, option48, option49, option50, option51};
		List<Option> anotherList = new LinkedList<>(Arrays.asList(another));

		SubCategory javaEESC = new SubCategory("Java Enterprise Edition", javaEEList, "ПОЯСНЕНИЕ: в случае невыбранного значения в строке подразумевается значение \"Нет\".");
		SubCategory springSC = new SubCategory("Spring Framework", springLIst, "ПОЯСНЕНИЕ: в случае невыбранного значения в строке подразумевается значение \"Нет\".");
		SubCategory dotNetSC = new SubCategory(".NET", dotNetLIst, "ПОЯСНЕНИЕ: в случае невыбранного значения в строке подразумевается значение \"Нет\".");
		SubCategory anotherSC = new SubCategory("Другие фреймворки", anotherList, "ПОЯСНЕНИЕ: в случае невыбранного значения в строке подразумевается значение \"Нет\".");

		List<SubCategory> fameworkSubCategories = new LinkedList<>();
		fameworkSubCategories.add(javaEESC);
		fameworkSubCategories.add(springSC);
		fameworkSubCategories.add(dotNetSC);
		fameworkSubCategories.add(anotherSC);

		Category backendCategory = new Category("Backend-технологии", fameworkSubCategories);
		backendCategory.setImage("resources/static/images/backend.png");
		categoryService.addCategory(backendCategory);
	}

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
		List<Option> mwApiManag = new LinkedList<>(Arrays.asList(apiManag));

		Option option49 = new Option("IBM WebSphere MQ");
		Option option50 = new Option("RabbitMQ");
		Option option51 = new Option("StormMQ");
		Option option52 = new Option("ActiveMQ");

		Option[] apiMOM = {option49, option50, option51, option52};
		List<Option> mwMom = new LinkedList<>(Arrays.asList(apiMOM));

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
		List<Option> mwIdentity = new LinkedList<>(Arrays.asList(apiIdentity));

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
		List<Option> mwEneterprise = new LinkedList<>(Arrays.asList(enterpriseCont));

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
		List<Option> mwBusiness = new LinkedList<>(Arrays.asList(businessIntellegence));

		Option option89 = new Option("ABBYY FlexiCapture");
		Option option90 = new Option("ABBYY Recognition Server");
		Option option91 = new Option("OpenText Capture Center");
		Option option92 = new Option("OpenText Captiva");
		Option option93 = new Option("Ephesoft Transact");
		Option option94 = new Option("Kofax Capture");
		Option option95 = new Option("Smart Engines (Smart IDReader)");

		Option[] ocp = {option89, option90, option91, option92, option93, option94, option95};
		List<Option> mwOcp = new LinkedList<>(Arrays.asList(ocp));

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
		middlewareCategory.setImage("resources/static/images/Middleware.jpg");
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
		Option option11 = new Option("Interbase / Firebird");

		Option[] dbs = {option4, option5, option6, option7, option8, option9, option10, option11};

		Option option41 = new Option("Oracle Database");
		Option option51 = new Option("Oracle TimesTen");
		Option option61 = new Option("MS SQL Server");
		Option option71 = new Option("IBM DB2");
		Option option81 = new Option("IBM Informix");
		Option option91 = new Option("PostgreSQL");
		Option option101 = new Option("MySQL / MariaDB");
		Option option111 = new Option("Interbase / Firebird");

		Option[] dbs2 = {option41, option51, option61, option71, option81, option91, option101, option111};

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

		List<Option> dbOptions2 = new LinkedList<>();
		dbOptions2.addAll(Arrays.asList(dbs2));

		List<Option> adddevOptionsSet = new LinkedList<>();
		adddevOptionsSet.addAll(Arrays.asList(addevOptions));

		SubCategory dbDevSC = new SubCategory("Разработка", dbOptions, "Реляционные базы данных.");
		SubCategory dbAdminSC = new SubCategory("Администрирование", dbOptions2, "Реляционные базы данных.");
		SubCategory addevSC = new SubCategory("Разработка и администрирование", adddevOptionsSet, "NoSQL и нереляционные базы данных.");

		List<SubCategory> dbSubCategories = new LinkedList<>();
		dbSubCategories.add(dbDevSC);
		dbSubCategories.add(dbAdminSC);
		dbSubCategories.add(addevSC);

		Category dbCategory = new Category("Базы данных", dbSubCategories);
		dbCategory.setImage("resources/static/images/db.jpg");
		categoryService.addCategory(dbCategory);
	}

	private void addProgramLanguages() {
		Option option1 = new Option("Java");
		Option option2 = new Option("Java Script");
		Option option3 = new Option("С#");
		Option option4 = new Option("С/С++");
		Option option5 = new Option("PHP");
		Option option6 = new Option("Ruby");
		Option option7 = new Option("Python");
		Option option8 = new Option("Objective-C");
		Option option9 = new Option("Swift");
		Option option10 = new Option("Go");
		Option option11 = new Option("Kotlin");
		Option option12 = new Option("D");
		Option option13 = new Option("R");
		Option option14 = new Option("ABAP");
		Option option15 = new Option("Perl");
		Option option16 = new Option("Haskell");
		Option option17 = new Option("Erlang");
		Option option18 = new Option("Rust");
		Option option19 = new Option("Lua");
		Option option20 = new Option("Scala");
		Option option21 = new Option("Clojure");
		Option option22 = new Option("Object Pascal / Pascal");
		Option option23 = new Option("ColdFusion");
		Option option24 = new Option("Ada");
		Option option25 = new Option("Assembler");

		Option[] langs = {option1, option2, option3, option4, option5, option6, option7, option8, option9,
				option10, option11, option12, option13, option14, option15, option16, option17, option18, option19, option20, option21, option22, option23, option24, option25};

		List<Option> options = new LinkedList<>();
		options.addAll(Arrays.asList(langs));

		SubCategory subCategory = new SubCategory(" ", options, "ПОЯСНЕНИЕ: в случае невыбранного значения в строке подразумевается значение \"Нет\". Поэтому можно заполнять только строки в которых у Вас будет значение отличается от \"Нет\". Данное пояснение касается не только этого вопроса, но и всех последующих.");

		List<SubCategory> subCategories = new LinkedList<>();
		subCategories.add(subCategory);

		Category category = new Category("Языки программирования", subCategories, "");
		category.setImage("resources/static/images/programming-languages.jpg");
		categoryService.addCategory(category);
	}

	private void addPossibleAnswers() {
		PossibleAnswer possibleAnswer1 = new PossibleAnswer("Нет");
		PossibleAnswer possibleAnswer2 = new PossibleAnswer("Начальные знания");
		PossibleAnswer possibleAnswer3 = new PossibleAnswer("Базовые знания");
		PossibleAnswer possibleAnswer4 = new PossibleAnswer("Продвинутые знания");

		possibleAnswerService.savePossibleAnswer(possibleAnswer1);
		possibleAnswerService.savePossibleAnswer(possibleAnswer2);
		possibleAnswerService.savePossibleAnswer(possibleAnswer3);
		possibleAnswerService.savePossibleAnswer(possibleAnswer4);
	}
}

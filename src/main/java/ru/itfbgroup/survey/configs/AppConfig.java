package ru.itfbgroup.survey.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import ru.itfbgroup.survey.configs.initializer.DataInitializer;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@EnableWebMvc
@EnableTransactionManagement
@Configuration
@PropertySource(value = {"classpath:hibernate.properties", "classpath:application.properties"})
@ComponentScan({"ru.itfbgroup.survey"})
public class AppConfig {

	@Autowired
	private Environment environment;

//	@Bean
//	public TaskScheduler taskScheduler() {
//		return new ConcurrentTaskScheduler();
//	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = new LocalContainerEntityManagerFactoryBean();

		entityManagerFactory.setDataSource(dataSource);
		entityManagerFactory.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		entityManagerFactory.setJpaDialect(new HibernateJpaDialect());
		entityManagerFactory.setPackagesToScan("ru.itfbgroup.survey");

		entityManagerFactory.setJpaPropertyMap(hibernateJpaProperties());
		return entityManagerFactory;
	}

	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory emf) {

		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
		jpaTransactionManager.setEntityManagerFactory(emf);
		return jpaTransactionManager;
	}

	private Map<String, ?> hibernateJpaProperties() {
		HashMap<String, String> properties = new HashMap<>();
		properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
		properties.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
		properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
		return properties;
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(environment.getRequiredProperty("hibernate.connection.driver_class"));
		dataSource.setUrl(environment.getRequiredProperty("hibernate.connection.url"));
		dataSource.setUsername(environment.getRequiredProperty("hibernate.connection.username"));
		dataSource.setPassword(environment.getRequiredProperty("hibernate.connection.password"));
		return dataSource;
	}

	@Bean
	public JavaMailSender getMailSender() {
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		//Using gmail
		mailSender.setHost(environment.getRequiredProperty("mail.host"));
		mailSender.setPort(587);
		mailSender.setUsername(environment.getRequiredProperty("mail.username"));
		mailSender.setPassword(environment.getRequiredProperty("mail.password"));

		Properties javaMailProperties = new Properties();
		javaMailProperties.put("mail.smtp.starttls.enable", "true");
		javaMailProperties.put("mail.smtp.auth", "true");
		javaMailProperties.put("mail.transport.protocol", "smtp");
		javaMailProperties.put("mail.debug", "true");//Prints out everything on screen

		mailSender.setJavaMailProperties(javaMailProperties);
		return mailSender;
	}

	@Bean(initMethod = "init")
	public DataInitializer initTestData() {
		return new DataInitializer();
	}
}

package com.cardmanagementsystem.configuration;

import java.util.Properties;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class DBConfiguration {
	@Value("${db.driver}")
	private String driver;

	@Value("${db.password}")
	private String password;

	@Value("${db.url}")
	private String url;

	@Value("${db.username}")
	private String userName;

	@Value("${hibernate.dialect}")
	private String diallect;

	@Value("${hibernate.show_sql}")
	private String showSql;

	@Value("${hibernate.hbm2ddl.auto}")
	private String hbm2ddlauto;

	@Value("${entitymanager.packagesToScan}")
	private String packagesToScan;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driver);
		dataSource.setUrl(url);
		dataSource.setUsername(userName);
		dataSource.setPassword(password);
		return dataSource;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(packagesToScan);
		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.dialect", diallect);
		hibernateProperties.put("hibernate.show_sql", showSql);
		hibernateProperties.put("hibernate.hbm2ddl.auto", hbm2ddlauto);
		sessionFactory.setHibernateProperties(hibernateProperties);

		return sessionFactory;
	}

	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}
}

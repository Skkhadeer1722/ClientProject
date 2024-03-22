package com.dataextract.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@EnableJpaRepositories(basePackages = {"com.dataextract.cas.repository","com.dataextract.rtgs.repository","com.dataextract.sgs.repository"}, entityManagerFactoryRef = "entityManager", transactionManagerRef = "transactionManager")
public class DBConfig {
	private final Logger logger = LogManager.getLogger(DBConfig.class);

	@Autowired
	private Environment env;

	@Primary
	@Bean
	@ConfigurationProperties(prefix = "spring.cas.datasource")
	public DataSource casAccountDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Primary
	@Bean
	@ConfigurationProperties(prefix = "spring.rtgs.datasource")
	public DataSource rtgsDataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Primary
	@Bean
	@ConfigurationProperties(prefix = "spring.sgs.datasource")
	public DataSource sgsDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	@Primary
	public LocalContainerEntityManagerFactoryBean entityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		if(env.getProperty("extract.db.name") == null || env.getProperty("extract.db.name").trim().isEmpty() )
		 logger.error("please enter extract.db.name property in application.properties");
		else if(env.getProperty("extract.db.name").equalsIgnoreCase("cas")) {
			em.setDataSource(casAccountDataSource());
		}
		else if(env.getProperty("extract.db.name").equalsIgnoreCase("rtgs"))  {
			em.setDataSource(rtgsDataSource());	
		}
		else if(env.getProperty("extract.db.name").equalsIgnoreCase("sgs"))  {
			em.setDataSource(sgsDataSource());
		}
		em.setPackagesToScan(new String[] { "com.dataextract.cas.model","com.dataextract.rtgs.model","com.dataextract.sgs.model" });

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		Map<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		em.setJpaPropertyMap(properties);

		return em;
	}

	@Bean
	@Primary
	public PlatformTransactionManager transactionManager() {

		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManager().getObject());
		return transactionManager;
	}
}
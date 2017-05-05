package org.itnavigator.test.task.config;

import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.*;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@ComponentScan({"org.itnavigator.test.task.service", "org.itnavigator.test.task.domain", "org.itnavigator.test.task.web"})
public class ApplicationConfig {
	
	@Primary
	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.build();
	}
	
	@Bean(initMethod = "migrate")
	public Flyway flyway(DataSource dataSource) {
		Flyway flyway = new Flyway();
		flyway.setBaselineOnMigrate(false);
		flyway.setLocations("migration/h2");
		flyway.setDataSource(dataSource);
		return flyway;
	}
	
	@Bean
	public JpaVendorAdapter jpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setDatabase(Database.H2);
		adapter.setGenerateDdl(true);
		adapter.setShowSql(true);
		return adapter;
	}
	
	@Bean
	@DependsOn("flyway")
	@Primary
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
		LocalContainerEntityManagerFactoryBean managerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		managerFactoryBean.setDataSource(dataSource);
		managerFactoryBean.setJpaVendorAdapter(jpaVendorAdapter);
		managerFactoryBean.setPackagesToScan("org.itnavigator.test.task.domain.model");
		managerFactoryBean.setPersistenceUnitName("entityManager");
		return managerFactoryBean;
	}
	
	@Bean
	public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
	
	@Bean
	public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
		return new PersistenceExceptionTranslationPostProcessor();
	}
}

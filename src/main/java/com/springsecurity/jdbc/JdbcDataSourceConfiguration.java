package com.springsecurity.jdbc;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

/**
 * The configuration class for JDBC datasource.
 */
@Configuration
@ConditionalOnProperty(name = "authentication-type", havingValue = "jdbc-authentication")
@EnableJpaRepositories(basePackages = "com.springsecurity.jdbc", entityManagerFactoryRef = "jdbcEntityManager")
public class JdbcDataSourceConfiguration {

	@Bean("jdbcEntityManager")
	public LocalContainerEntityManagerFactoryBean jdbcEntityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(jdbcDataSource());
		em.setPackagesToScan("com.springsecurity.jdbc");

		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(adapter);
		return em;
	}

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource jdbcDataSource() {
		return DataSourceBuilder.create().build();
	}
	
}

package com.springsecurity.jpa;

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
 * The configuration class for JPA data source.
 */
@Configuration
@ConditionalOnProperty(name = "authentication-type", havingValue = "jpa-authentication")
@EnableJpaRepositories(basePackages = "com.springsecurity.jpa", entityManagerFactoryRef = "jpaEntityManager")
public class JpaDataSourceConfiguration {
	
	@Bean("jpaEntityManager")
	public LocalContainerEntityManagerFactoryBean jpaEntityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(postgresDataSource());
		em.setPackagesToScan("com.springsecurity.jpa");
		
		// Persistence provider for our entity manager
		em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		return em;
	}

	@Bean
	@ConfigurationProperties(prefix = "spring.postgres-datasource")
	public DataSource postgresDataSource() {
		return DataSourceBuilder.create().build();
	}

}

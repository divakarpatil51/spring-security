package com.springsecurity.datasourceconfig;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSourceConfiguration {

	@Bean
	@Conditional(value = H2AuthenticationCondition.class)
	public DataSource getH2DataSource() {
		DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName("org.h2.Driver");
        dataSourceBuilder.url("jdbc:h2:mem:test");
		return dataSourceBuilder.build();
	}
	
	@Bean
	@ConditionalOnProperty(name = "authentication-type", havingValue = "db-authentication")
	public DataSource getPostgresDataSource() {
		DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.driverClassName("org.postgresql.Driver");
        dataSourceBuilder.url("jdbc:postgresql://localhost/Springsecurity");
        dataSourceBuilder.username("postgres");
        dataSourceBuilder.password("password");
		return dataSourceBuilder.build();
	
	}
	
}

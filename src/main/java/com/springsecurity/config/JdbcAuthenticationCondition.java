package com.springsecurity.config;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class JdbcAuthenticationCondition implements Condition{

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		String authenticationType = context.getEnvironment().getProperty("authentication-type");
		return authenticationType.startsWith("h2-");
	}

}

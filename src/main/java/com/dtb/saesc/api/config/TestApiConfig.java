package com.dtb.saesc.api.config;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestApiConfig {
	private static final Log log = LogFactory.getLog(TestApiConfig.class);
	
	@PostConstruct
	public void init() {
		log.info("Init test configs");
	}
}

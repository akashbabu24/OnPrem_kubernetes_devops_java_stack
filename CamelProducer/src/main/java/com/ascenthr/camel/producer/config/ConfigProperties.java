package com.ascenthr.camel.producer.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@ConfigurationProperties(prefix = "ascenthr")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConfigProperties {

	private int messagesPerQueue;
	
	private String camelRabbitmqUri;
	
}

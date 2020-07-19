package com.ascenthr.camel.consumer.route;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ascenthr.camel.consumer.config.ConfigProperties;
import com.ascenthr.camel.consumer.processor.QueueMessageProcessor;

/**
 * RabbitMQ route to read messages from the RabbitMQ queue and store it into database
 * @author sureshd
 *
 */
@Component
public class RabbitMQRoute extends RouteBuilder {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQRoute.class);
	
	@Autowired
	private ConfigProperties config;
	@Autowired
	private QueueMessageProcessor processor;
	
	@Override
	public void configure() throws Exception {

		from(config.getCamelRabbitmqUri())
			.id("rabbitEmployeeRoute")
			.log(LoggingLevel.INFO, LOGGER, "Read message from RabbitMQ queue to store in the database")
			.process(processor)
			.log(LoggingLevel.INFO, LOGGER, "Saved message into database")
			.end();
	}
}

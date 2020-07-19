package com.ascenthr.camel.producer.route;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ascenthr.camel.producer.config.ConfigProperties;
import com.ascenthr.camel.producer.message.QueueMessage;

/**
 * RabbitMQ route to split and transform incoming message containing tenantId and employee list into JSON format
 * and forward it to RabbitMQ queue
 * @author sureshd
 *
 */
@Component
public class RabbitMQRoute extends RouteBuilder {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RabbitMQRoute.class);
	
	@Autowired
	private ConfigProperties config;
	
	@Override
	public void configure() throws Exception {

		JacksonDataFormat jsonDataFormat = new JacksonDataFormat(QueueMessage.class);

		from("direct:rabbitEmployeePoint")
			.id("rabbitEmployeeRoute")
			.log(LoggingLevel.INFO, LOGGER, "Received message to split and transform into JSON and forward it to RabbitMQ queue")
			.split().method("employeeSplitter", "splitBody")
			.marshal(jsonDataFormat)
			.log(LoggingLevel.INFO, LOGGER, "Transformed into JSON and sending to RabbitMQ queue")
			.to(config.getCamelRabbitmqUri())
			.end();
	}
}

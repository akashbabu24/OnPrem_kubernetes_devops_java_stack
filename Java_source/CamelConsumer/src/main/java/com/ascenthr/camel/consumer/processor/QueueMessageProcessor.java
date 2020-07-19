package com.ascenthr.camel.consumer.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ascenthr.camel.consumer.model.QueueMessage;
import com.ascenthr.camel.consumer.service.QueueMessageService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Processor to process incoming queue message to convert it to JSON and save it to database
 * @author sureshd
 *
 */
@Component
public class QueueMessageProcessor implements Processor{

	private static final Logger LOGGER = LoggerFactory.getLogger(QueueMessageProcessor.class);
	private final static ObjectMapper objectMapper = new ObjectMapper();
	
	@Autowired
	private QueueMessageService service;
	
	@Override
	public void process(Exchange exchange) throws Exception {
		
		LOGGER.info("Processing queue message to store into database");
		QueueMessage message = objectMapper.readValue(exchange.getIn().getBody(String.class), QueueMessage.class);
		service.save(message);
		LOGGER.info("Completed processing by storing queue message into database for TENANTID=" + message.getTenantId());
	}

}

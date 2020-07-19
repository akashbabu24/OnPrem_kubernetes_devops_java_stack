package com.ascenthr.camel.consumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ascenthr.camel.consumer.model.QueueMessage;
import com.ascenthr.camel.consumer.repository.QueueMessageRepository;

/**
 * Service to store queue message into database
 * @author sureshd
 *
 */
@Service
public class QueueMessageService {

	private static final Logger LOGGER = LoggerFactory.getLogger(QueueMessageService.class);
	
	@Autowired
	QueueMessageRepository repository;
	
	public void save(QueueMessage message) {
		LOGGER.debug("Saving queue message i.e. employees into database for TENANTID=" + message.getTenantId());
		repository.save(message);
		LOGGER.debug("Saved queue message i.e. employees into database for TENANTID=" + message.getTenantId());
	}
}

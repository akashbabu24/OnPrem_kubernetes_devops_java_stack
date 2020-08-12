package com.ascenthr.camel.producer.splitter;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ascenthr.camel.producer.config.ConfigProperties;
import com.ascenthr.camel.producer.message.QueueMessage;
import com.ascenthr.camel.producer.model.Employee;
import com.ascenthr.camel.producer.service.MetricsService;

/**
 * Employee splitter bean used to split incoming employees list into chunks before sending to message queue
 * @author sureshd
 *
 */
@Component
public class EmployeeSplitter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeSplitter.class);
	
	@Autowired
	private MetricsService metricsService;
	
	@Autowired
	private ConfigProperties config;

	/**
	 * Method to split body based on the configuration
	 * @param body - camel context body which holds actual message sent through tunnel
	 * @return
	 */
	public List<QueueMessage> splitBody(QueueMessage body) {
		
		final int chunkSize = config.getMessagesPerQueue(); // split size - no. of employees should be sent per message to queue
		final AtomicInteger counter = new AtomicInteger();
		
		String tenantId = body.getTenantId();
		List<Employee> allEmployees = body.getEmployees();
		
		LOGGER.info("TENANTID=" + tenantId + ". Splitting employees into specified chunks of count " + chunkSize + ". Total incoming employee count= " + allEmployees.size());
		
		final Collection<List<Employee>> splittedEmployees = allEmployees.stream()
			    .collect(Collectors.groupingBy(it -> counter.getAndIncrement() / chunkSize))
			    .values();

		List<QueueMessage> messages = splittedEmployees.stream()
				.map( employees -> new QueueMessage(tenantId, employees))
				.collect(Collectors.toList());
		
		LOGGER.info("Incrementing total no. of message sending to queue counter for TENANTID=" + tenantId);
		metricsService.incrementMessageSentToQueueCounter((double)messages.size());
		
		LOGGER.info("Incrementing total no. of employees sending to queue counter for TENANTID=" + tenantId);
		metricsService.incrementEmployeeSentToQueueCounter((double)allEmployees.size());
		
		LOGGER.info("TENANTID=" + tenantId + ". Splitted employees into chunks. Total messages= " + messages.size());
		
		return messages;
	}
}

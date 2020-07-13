package com.ascenthr.camel.producer.message;

import java.util.List;

import com.ascenthr.camel.producer.model.Employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * POJO class to hold message details to be sent to Queue
 * @author sureshd
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueueMessage {
	
	private String tenantId;
	
	private List<Employee> employees;
	
}

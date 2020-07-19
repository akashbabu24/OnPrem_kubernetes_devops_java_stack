package com.ascenthr.camel.consumer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * POJO class to hold employee details
 * @author sureshd
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
	
	private long id;
	
	private String name;
	
	private String designation;
	
	private String department;
	
}

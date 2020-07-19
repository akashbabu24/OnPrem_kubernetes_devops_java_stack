package com.ascenthr.camel.consumer.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.ascenthr.camel.consumer.converter.EmployeeListConverter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Used as POJO to send message to queue and as Entity class to perform database operations
 * @author sureshd
 *
 */
@Entity
@Table(name="queue_message")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class QueueMessage {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = true)
	private int id;
	
	@Column(name = "tenant_id", nullable = false)
	private String tenantId;
	
	@Column(name = "employee_json", nullable = false)
	@Convert(converter = EmployeeListConverter.class)
	private List<Employee> employees;
	
}

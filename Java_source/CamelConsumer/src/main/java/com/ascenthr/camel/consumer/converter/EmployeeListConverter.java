package com.ascenthr.camel.consumer.converter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ascenthr.camel.consumer.model.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Converter class to convert the list of objects to and FROM JSON string for storing and retrieving from the database
 * @author sureshd
 *
 */
@Component
@Converter
public class EmployeeListConverter implements AttributeConverter<List<Employee>, String> {

	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeListConverter.class);
	ObjectMapper mapper = new ObjectMapper();
	
	/**
	 * convert list of objects to JSON string
	 */
	@Override
	public String convertToDatabaseColumn(List<Employee> attribute) {
		try {
			return mapper.writeValueAsString(attribute);
		} catch (JsonProcessingException e) {
			LOGGER.error("Error occurred while converting list of employees into string to store in the db column: " + e.getMessage());
		}
		return null;
	}

	/**
	 * Convert JSON string from database into list of objects
	 */
	@Override
	public List<Employee> convertToEntityAttribute(String dbData) {
		try {
			return Arrays.asList(mapper.readValue(dbData, Employee[].class));
		} catch (IOException e) {
			LOGGER.error("Error occurred while converting db data into list of employees: " + e.getMessage());
		}
		return null;
	}

}

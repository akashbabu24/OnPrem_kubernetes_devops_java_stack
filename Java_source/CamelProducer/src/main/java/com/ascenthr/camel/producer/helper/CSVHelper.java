package com.ascenthr.camel.producer.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.ascenthr.camel.producer.model.Employee;

/**
 * CSV Helper class to validate input CSV file format and transform CSV into POJO
 * @author sureshd
 *
 */
public class CSVHelper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CSVHelper.class);
	public static String TYPE = "text/csv";

	/**
	 * Method to validate whether the file type is CSV or not
	 * @param file - multipart file with type as "text/csv"
	 * @return boolean - returns true if file type is "text/csv", otherwise false
	 */
	public static boolean hasCSVFormat(MultipartFile file) {
		LOGGER.debug("Verifying whether file type is '" + TYPE + "'");
		if (!TYPE.equals(file.getContentType())) {
			LOGGER.debug("File is not of type '" + TYPE + "'");
			return false;
		}
		LOGGER.debug("File is of type '" + TYPE + "'");
		return true;
	}

	/**
	 * Method to convert employee csv file into list of employee object
	 * @param is - inputstream containing file object which is of type CSV
	 * @return List<Employee> - returns list of employees converted from CSV
	 */
	public static List<Employee> csvToEmployees(InputStream is) {
		String note;
		try (
				BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				CSVParser csvParser = new CSVParser(fileReader,	CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
			) {
			LOGGER.debug("Parsing csv file into employee list..");
			List<Employee> employees = new ArrayList<Employee>();
			Iterable<CSVRecord> csvRecords = csvParser.getRecords();
			for (CSVRecord csvRecord : csvRecords) {
				Employee employee = new Employee(Long.parseLong(csvRecord.get(0)), csvRecord.get(1), csvRecord.get(2), csvRecord.get(3));
				employees.add(employee);
			}
			LOGGER.debug("Parsed csv file into employee list : " + employees);
			return employees;
		} catch (IOException e) {
			note = "Failed to parse CSV file : " + e.getMessage();
			LOGGER.error(note);
			throw new RuntimeException(note);
		}
	}

}

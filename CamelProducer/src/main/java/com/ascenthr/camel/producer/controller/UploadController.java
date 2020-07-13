package com.ascenthr.camel.producer.controller;

import java.util.List;

import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ascenthr.camel.producer.helper.CSVHelper;
import com.ascenthr.camel.producer.message.QueueMessage;
import com.ascenthr.camel.producer.message.ResponseMessage;
import com.ascenthr.camel.producer.model.Employee;

/**
 * Rest Controller to handle file upload and process it. 
 * CSV file upload handle process the incoming employees list csv 
 * and converts it to POJO class before sending it to Camel route to 
 * split and transform messages into chunks to JSON format and send it 
 * RabbitMQ queue
 * @author sureshd
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/producer/api/v1/upload/")
public class UploadController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UploadController.class);
	
	@Produce(uri = "direct:rabbitEmployeePoint")
	private ProducerTemplate template;
	
	@PostMapping("/csv")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("tenantId") String tenantId, @RequestParam("file") MultipartFile file) {
		
		LOGGER.info("Received Employee CSV upload request submitted by TENANTID=" + tenantId);
		
		String note;
		if (CSVHelper.hasCSVFormat(file)) {
			
			try {
				List<Employee> employees = CSVHelper.csvToEmployees(file.getInputStream());
				
				employees.stream().forEach(System.out::println);
				QueueMessage message = new QueueMessage(tenantId, employees);
				
				// send message to camel route to split and transform it to JSON and send resultant JSON to rabbitmq queue
				template.asyncSendBody(template.getDefaultEndpoint(), message);
				
				note = "Uploaded file successfully: " + file.getOriginalFilename() + " request submitted by TENANTID=" + tenantId;
				LOGGER.info(note);
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(note));
			} catch (Exception e) {
				note = "Error occurred while uploading the file: " + file.getOriginalFilename() + " request submitted by TENANTID=" + tenantId;
				LOGGER.error(note);
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(note));
			}
		}
		
		note = "Invalid upload input file request submitted by TENANTID=" + tenantId + ". Please upload a valid csv file.";
		LOGGER.error(note);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(note));
	}

}

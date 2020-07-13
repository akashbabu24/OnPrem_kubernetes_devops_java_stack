package com.ascenthr.camel.producer.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.ascenthr.camel.producer.message.ResponseMessage;

/**
 * Exception advice class to throw exception when upload file size crosses the configure limit
 * @author sureshd
 *
 */
@ControllerAdvice
public class FileUploadExceptionAdvice extends ResponseEntityExceptionHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileUploadExceptionAdvice.class);
	
	@Value("${spring.servlet.multipart.max-file-size}")
	private String maxFileUploadSize;

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public ResponseEntity<ResponseMessage> handleMaxSizeException(MaxUploadSizeExceededException exc) {
		String note = "File size is too large. Reached maximum limit of " + maxFileUploadSize + ".";
		LOGGER.error(note);
		return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(note));
	}
}
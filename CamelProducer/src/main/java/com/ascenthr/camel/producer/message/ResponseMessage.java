package com.ascenthr.camel.producer.message;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * POJO class to hold response message to be sent to request
 * @author sureshd
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage {
	
  private String message;
  
}

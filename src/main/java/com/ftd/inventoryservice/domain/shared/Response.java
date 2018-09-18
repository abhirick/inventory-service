/**
 * 
 */
package com.ftd.inventoryservice.domain.shared;

import lombok.Builder;
import lombok.Data;

/**
 * Generic response for rest calls.
 * 
 * @author Abhishek Mallick
 *
 */
@Data
@Builder
public class Response {

	private String message;

	private boolean status;

	private int statusCode;

	private Object response;

}

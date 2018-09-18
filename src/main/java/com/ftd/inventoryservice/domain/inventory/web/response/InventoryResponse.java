/**
 * 
 */
package com.ftd.inventoryservice.domain.inventory.web.response;

import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * Inventory Mongo Response Object
 * </p>
 * 
 * @author Abhishek Mallick
 *
 */
public class InventoryResponse {
	
	@Getter
	@Setter
	private String id;
	
	@Getter
	@Setter
	private long stockLevel;
	
	@Getter
	@Setter
	private String skuAvailabilityDate;
	
	@Getter
	@Setter
	private String skuId;

	@Getter
	@Setter
	private String inventoryCreationDate;

	@Getter
	@Setter
	private String inventoryEndDate;
	
	@Getter
	@Setter
	private String inventoryStartDate;
	
	@Getter
	@Setter
	private String locationId;

}

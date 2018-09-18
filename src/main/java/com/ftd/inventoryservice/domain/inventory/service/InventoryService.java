/**
 * 
 */
package com.ftd.inventoryservice.domain.inventory.service;

import java.util.List;

import com.ftd.inventoryservice.domain.inventory.Inventory;
import com.ftd.inventoryservice.handling.NoRecordsFoundException;

/**
 * <p>
 * Service interface for querying Inventory data.This interface has methods
 * which would consult the Mongo Repository to get the inventory data based on
 * certain parameters..
 * </p>
 * 
 * @author Abhishek Mallick
 *
 */
public interface InventoryService {

	/**
	 * <p>
	 * Method that consults Mongo Repository to fetch the Inventory Details for the
	 * passed in SkuId.
	 * </p>
	 * 
	 * @param <code>skuId</code>
	 * @return <code>List<Inventory></code>
	 * @throws NoRecordsFoundException
	 */
	List<Inventory> getInventoryDetailsForSku(String skuId) throws NoRecordsFoundException;

	/**
	 * <p>
	 * Method that consults Mongo Repository to fetch the Inventory Details for the
	 * passed in inventoryId.
	 * </p>
	 * 
	 * @param <code>inventoryId</code>
	 * @return <code>Inventory</code>
	 * @throws NoRecordsFoundException
	 */
	Inventory getInventoryDetailsById(String inventoryId) throws NoRecordsFoundException;

	/**
	 * <p>
	 * Method that consults Mongo Repository to fetch the Inventory Details for the
	 * passed in Location Id.
	 * </p>
	 * 
	 * @param <code>locationId</code>
	 * @return <code>List<Inventory></code>
	 * @throws NoRecordsFoundException
	 */
	List<Inventory> getInventoryDetailsByLocationId(String locationId) throws NoRecordsFoundException;

}

/**
 * 
 */
package com.ftd.inventoryservice.domain.inventory.repository.mongo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ftd.inventoryservice.domain.inventory.Inventory;

/**
 * @author Abhishek Mallick
 *
 */
public interface InventoryRepository extends JpaRepository<Inventory, String> {

	/**
	 * 
	 * <p>
	 * Method that queries the H2DB for a particular inventoryId.
	 * </p>
	 * 
	 * @return <code>Inventory</code>
	 * 
	 */
	public Inventory findOne(String id);

	/**
	 * <p>
	 * Method that queries the H2DB to get all the inventory documents that matches
	 * passed in skuId.
	 * </p>
	 * 
	 * @param skuid
	 * @return <code>List<Inventory></code>
	 * 
	 */
	public List<Inventory> findBySkuId(String skuid);

	/**
	 * <p>
	 * Method that queries the H2DB to get all the inventory documents that matches
	 * passed in locationid.
	 * </p>
	 * 
	 * @param locationid
	 * @return <code>List<Inventory></code>
	 * 
	 */
	public List<Inventory> findByLocationId(String locationid);

}

/**
 * 
 */
package com.ftd.inventoryservice.domain.inventory.repository.redis;

import java.util.Map;
import com.ftd.inventoryservice.domain.inventory.Inventory;


/**
 * <p>
 * Unlike other Spring Data Repository interfaces, this is just a standard
 * interface to define a supported method. It doesn’t enable any Spring-related
 * features.
 * 
 * And this is certainly unusual for a Spring Data project. Most of the other
 * Spring Data projects are capable of building repositories based on the common
 * Spring Data interfaces.
 * 
 * For example, Spring Data JPA provides several base repository interfaces that
 * you can extend to get base features such basic CRUD operations, the ability
 * to generate queries based on method names, etc. In most cases, there’s no
 * need to write an implementation of the repository interface at all.
 * 
 * Spring Data Redis, however, does not have base repository interfaces to
 * extend, nor does it have method name-based query generation.
 * </p>
 * 
 * @author Abhishek Mallick
 *
 */

public interface InventoryRedisRepository {

	void saveInventory(Inventory inventory);

	void updateInventory(Inventory inventory);

	Inventory findInventory(String id);

	void deleteInventory(String id);

	Map<Object, Object> findAllInventory();

}

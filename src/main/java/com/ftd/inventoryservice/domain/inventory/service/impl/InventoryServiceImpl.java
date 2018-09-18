/**
 * 
 */
package com.ftd.inventoryservice.domain.inventory.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftd.inventoryservice.domain.inventory.Inventory;
import com.ftd.inventoryservice.domain.inventory.repository.mongo.InventoryRepository;
import com.ftd.inventoryservice.domain.inventory.service.InventoryService;
import com.ftd.inventoryservice.handling.NoRecordsFoundException;

/**
 * @author Abhishek Mallick
 *
 */
@Service
public class InventoryServiceImpl implements InventoryService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private InventoryRepository inventoryMongoRepository;

	@Override
	public List<Inventory> getInventoryDetailsForSku(String skuId) throws NoRecordsFoundException {

		logger.info("[InventoryServiceImpl.getInventoryDetailsForSku()] : Sku Id Passed is :: " + skuId);
		List<Inventory> skuInventoryDocs = inventoryMongoRepository.findBySkuId(skuId);
		if (null == skuInventoryDocs || skuInventoryDocs.isEmpty())
			throw new NoRecordsFoundException("No Inventory Found in Mongo repository for Passed in Sku id :: " + skuId);

		return skuInventoryDocs;
	}

	@Override
	public Inventory getInventoryDetailsById(String inventoryId) throws NoRecordsFoundException {

		logger.info("[InventoryServiceImpl.getInventoryDetailsById()] : Inventory Id Passed is :: " + inventoryId);
		Inventory inventoryDoc = inventoryMongoRepository.findOne(inventoryId);
		if (null == inventoryDoc)
			throw new NoRecordsFoundException("No Inventory Found in Mongo repository for Passed in inventory id :: " + inventoryId);

		return inventoryDoc;
	}

	@Override
	public List<Inventory> getInventoryDetailsByLocationId(String locationId) throws NoRecordsFoundException {

		logger.info("[InventoryServiceImpl.getInventoryDetailsByLocationId()] : location Id Passed is :: " + locationId);
		List<Inventory> inventoryDocs = inventoryMongoRepository.findByLocationId(locationId);
		if (inventoryDocs.isEmpty())
			throw new NoRecordsFoundException("No Inventory Found in Mongo repository for Passed in location id :: " + locationId);

		return inventoryDocs;
	}

}

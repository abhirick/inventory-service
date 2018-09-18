package com.ftd.inventoryservice.domain.inventory.web.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ftd.inventoryservice.domain.inventory.Inventory;
import com.ftd.inventoryservice.domain.inventory.service.InventoryService;
import com.ftd.inventoryservice.domain.inventory.web.response.InventoryResponse;
import com.ftd.inventoryservice.domain.shared.CommonConstants;
import com.ftd.inventoryservice.domain.shared.CommonUtils;
import com.ftd.inventoryservice.handling.NoRecordsFoundException;

/**
 * <p>
 * Controller that exposes API related to Inventory data. It is responsible for
 * invoking Inventory Services which in-turn talks to Mongo Repository .The API
 * exposed will be invoked by the composite service which will re-process or
 * re-format the response returned by the Inventory service.
 * </p>
 * 
 * @author Abhishek Mallick
 *
 */
@RefreshScope
@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CommonUtils commonUtils;

	@Autowired
	private InventoryService inventoryService;
	
	/**
	 * <p>
	 * API created for returning Inventory details for an requested Sku Id.
	 * </p>
	 * 
	 * @param <code>skuId</code>
	 * @return <code>List<InventoryResponse></code> upon success else
	 *         <code>BadRequest</code> response will be returned.
	 * @throws NoRecordsFoundException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@RequestMapping(value = "/skuId/{skuId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<InventoryResponse> getInventoryForSku(@PathVariable(CommonConstants.SKU_ID) String skuId)
			throws NoRecordsFoundException, IllegalAccessException, InvocationTargetException {

		logger.info("[InventoryController.getInventoryForSku()] : Sku Id passed in is " + skuId);
		List<InventoryResponse> inventoryResponse = new ArrayList<>();

		List<Inventory> inventoryResult = inventoryService.getInventoryDetailsForSku(skuId);
		logger.debug("Inventory Result is : " + inventoryResult);

		for (Inventory inventoryDocument : inventoryResult) {
			
			InventoryResponse response = new InventoryResponse();
			BeanUtils.copyProperties(response, inventoryDocument);
			inventoryResponse.add(populateDateInInventoryResponse(inventoryDocument, response));
		}
		return inventoryResponse;
	}

	/**
	 * @param inventoryDocument
	 * @param response
	 */
	private InventoryResponse populateDateInInventoryResponse(Inventory inventoryDocument, InventoryResponse response) {

		logger.info("[InventoryController.populateDateInInventoryResponse()]");

		response.setSkuAvailabilityDate(commonUtils.convertDateToString(inventoryDocument.getAvailabilityDate()));
		response.setInventoryCreationDate(commonUtils.convertDateToString(inventoryDocument.getCreationDate()));
		response.setInventoryStartDate(commonUtils.convertDateToString(inventoryDocument.getStartDate()));
		response.setInventoryEndDate(commonUtils.convertDateToString(inventoryDocument.getEndDate()));

		return response;
	}

	/**
	 * <p>
	 * API created for returning Inventory details for an requested inventory Id.
	 * </p>
	 * 
	 * @param <code>inventoryId</code>
	 * @return <code>InventoryResponse</code> upon success else
	 *         <code>BadRequest</code> response will be returned.
	 * @throws NoRecordsFoundException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@RequestMapping(value = "/{inventoryId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public InventoryResponse getInventoryById(@PathVariable(CommonConstants.INVENTORY_ID) String inventoryId)
			throws NoRecordsFoundException, IllegalAccessException, InvocationTargetException {

		logger.debug("[InventoryController.getInventoryById()] : Inventory Id passed in is " + inventoryId);
		InventoryResponse response = new InventoryResponse();

		Inventory inventoryResult = inventoryService.getInventoryDetailsById(inventoryId);
		logger.debug("Inventory Result is : " + inventoryResult);
		BeanUtils.copyProperties(response, inventoryResult);
		return populateDateInInventoryResponse(inventoryResult, response);
	}

	/**
	 * <p>
	 * API created for returning Inventory details for an requested location Id.
	 * </p>
	 * 
	 * @param <code>skuId</code>
	 * @return <code>List<InventoryResponse></code> upon success else
	 *         <code>BadRequest</code> response will be returned.
	 * @throws NoRecordsFoundException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@RequestMapping(value = "/location/{locationId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<InventoryResponse> getInventoryByLocationId(@PathVariable(CommonConstants.LOCATION_ID) String locationId)
			throws NoRecordsFoundException, IllegalAccessException, InvocationTargetException {

		logger.debug("[InventoryController.getInventoryByLocationId()] : location Id passed in is " + locationId);
		List<InventoryResponse> inventoryResponse = new ArrayList<>();

		List<Inventory> inventoryResult = inventoryService.getInventoryDetailsByLocationId(locationId);
		logger.debug("Inventory Result is : " + inventoryResult);
		
		for (Inventory inventoryDocument : inventoryResult) {
			InventoryResponse response = new InventoryResponse();
			BeanUtils.copyProperties(response, inventoryDocument);
			inventoryResponse.add(populateDateInInventoryResponse(inventoryDocument, response));
		}
		return inventoryResponse;
	}

}

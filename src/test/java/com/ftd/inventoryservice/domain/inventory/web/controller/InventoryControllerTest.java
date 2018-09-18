/**
 * 
 *//*
package com.ftd.inventoryservice.domain.inventory.web.controller;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.sleuth.metric.SpanMetricReporter;
import org.springframework.cloud.sleuth.zipkin.ZipkinProperties;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.ftd.inventoryservice.domain.inventory.Inventory;
import com.ftd.inventoryservice.domain.inventory.service.InventoryService;
import com.ftd.inventoryservice.domain.shared.CommonUtils;
import com.netflix.discovery.EurekaClient;

*//**
 * The Test Class InventoryControllerTest.
 *
 * @author Abhishek Mallick
 *//*
@RunWith(SpringRunner.class)
@WebMvcTest(InventoryController.class)
public class InventoryControllerTest {

	private Inventory inv;

	private List<Inventory> invList;

	@Autowired
	private MockMvc mvc;

	@MockBean
	private CommonUtils commonUtils;

	@MockBean
	private InventoryService inventoryService;

	@MockBean
	private EurekaClient eurekaClient;

	@MockBean
	private SpanMetricReporter spanMetricReporter;

	@MockBean
	private ZipkinProperties zipkinProperties;

	@Before
	public void initialDataSetUp() {
		// Initializing a list;
		invList = new ArrayList<>();

		// Creating Inventory Object.
		inv = new Inventory();
		inv.setId(UUID.randomUUID().toString());
		inv.setLocationId("ftd");
		inv.setSkuId("sku1");
		inv.setStockLevel(100);
		inv.setStockLevelWithFraction(100.0);
		inv.setStockThreshold(100);
		inv.setBackorderLevel(400);
		inv.setBackorderLevelWithFraction(400.00);
		inv.setPreOrderLevel(1);
		inv.setBackorderThreshold(40);
        inv.setCreationDate(new Date());
        inv.setAvailabilityDate(new Date());
        inv.setDescription("New Inventory");
        inv.setDisplayName("Display Inventory");
        inv.setStartDate(new Date());
        inv.setEndDate(new Date());
        inv.setPreOrderLevelWithFraction(10);
        inv.setPreOrderThreshold(40);
		invList.add(inv);
	}

	
	@After
	public void tearDown() throws Exception {
		shutDownInventoryServiceTest();
	}

	*//**
	 * Shut down inventory service test.
	 *//*
	private void shutDownInventoryServiceTest() {
		inv = null;
		invList = null;
	}

	
	*//**
	 * Test get inventory for sku.
	 *
	 * @throws Exception
	 *             the exception
	 *//*
	@Test
	public void test_getInventoryForSku() throws Exception {

		given(inventoryService.getInventoryDetailsForSku("sku1")).willReturn(invList);

		mvc.perform(get("/api/v1/inventory/skuId/sku1").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("[*].id").isNotEmpty())
				.andExpect(jsonPath("[*].skuId").value("sku1"));
	}
	
	
	@Test
	public void test_getInventoryById() throws Exception {

		given(inventoryService.getInventoryDetailsById(inv.getId())).willReturn(inv);

		mvc.perform(get("/api/v1/inventory/{inventoryId}", inv.getId())
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("id").isNotEmpty())
				.andExpect(jsonPath("stockLevel").value(100))
				.andExpect(jsonPath("locationId").value("ftd"));
	}
	
	
	@Test
	public void test_getInventoryByLocationId() throws Exception {

		given(inventoryService.getInventoryDetailsByLocationId("ftd")).willReturn(invList);

		mvc.perform(get("/api/v1/inventory/location/{locationId}/" , inv.getLocationId())
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("[*].id").isNotEmpty())
				.andExpect(jsonPath("[*].locationId").value("ftd"));
	}
}
*/
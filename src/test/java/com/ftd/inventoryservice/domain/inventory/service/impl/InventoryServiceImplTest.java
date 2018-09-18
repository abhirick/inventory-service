/**
 * 
 */
package com.ftd.inventoryservice.domain.inventory.service.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.ftd.inventoryservice.domain.inventory.Inventory;
import com.ftd.inventoryservice.domain.inventory.repository.mongo.InventoryRepository;
import com.ftd.inventoryservice.handling.NoRecordsFoundException;


/**
 * <p>
 * The Test Class InventoryServiceImplTest for corresponding
 * InventoryServiceImpl.
 * </p>
 *
 * @author Abhishek Mallick
 */
public class InventoryServiceImplTest {

	/** The inventory mongo repository. */
	@Mock
	private InventoryRepository inventoryMongoRepository;

	/** The inventory service. */
	@InjectMocks
	private InventoryServiceImpl inventoryService;

	/** The thrown. */
	@Rule
	public ExpectedException thrown = ExpectedException.none();

	/** The inv. */
	private Inventory inv;

	/** The inv list. */
	private List<Inventory> invList;

	/**
	 * Sets the up.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@Before
	public void setUp() throws Exception {
		setupInventoryServiceTest();
	}

	/**
	 * Tear down.
	 *
	 * @throws Exception
	 *             the exception
	 */
	@After
	public void tearDown() throws Exception {
		shutDownInventoryServiceTest();
	}

	/**
	 * Shut down inventory service test.
	 */
	private void shutDownInventoryServiceTest() {
		inv = null;
		invList = null;
	}

	/**
	 * Setup inventory service test.
	 */
	private void setupInventoryServiceTest() {

		MockitoAnnotations.initMocks(this);

		// Initializing a list;
		invList = new ArrayList<>();

		// Creating Inventory Object.
		inv = new Inventory();
		inv.setId(UUID.randomUUID().toString());
		inv.setLocationId("ftd");
		inv.setSkuId("sku1");
		inv.setStockLevel(100);
		inv.setStockLevelWithFraction(100.0);
		invList.add(inv);

	}

	/**
	 * Test get inventory details for sku when no sku found.
	 *
	 * @throws NoRecordsFoundException
	 *             the no records found exception
	 */
	@Test
	public void test_getInventoryDetailsForSku_whenNoSkuFound() throws NoRecordsFoundException {
		thrown.expect(NoRecordsFoundException.class);
		thrown.expectMessage(is("No Inventory Found in Mongo repository for Passed in Sku id :: " + inv.getSkuId()));
		Mockito.when(inventoryMongoRepository.findBySkuId(inv.getSkuId())).thenReturn(null);
		inventoryService.getInventoryDetailsForSku(inv.getSkuId());
	}

	@Test
	public void test_getInventoryDetailsForSku_whenSkuListEmpty() throws NoRecordsFoundException {
		thrown.expect(NoRecordsFoundException.class);
		thrown.expectMessage(is("No Inventory Found in Mongo repository for Passed in Sku id :: " + inv.getSkuId()));
		Mockito.when(inventoryMongoRepository.findBySkuId(inv.getSkuId())).thenReturn(new ArrayList<>());
		inventoryService.getInventoryDetailsForSku(inv.getSkuId());
	}
	/**
	 * Test get inventory details for sku.
	 *
	 * @throws NoRecordsFoundException
	 *             the no records found exception
	 */
	@Test
	public void test_getInventoryDetailsForSku() throws NoRecordsFoundException {
		// Enables stubbing methods. Use it when you want the mock to return particular
		// value when particular method is called.
		Mockito.when(inventoryMongoRepository.findBySkuId(inv.getSkuId())).thenReturn(invList);
		assertNotNull(inventoryService.getInventoryDetailsForSku(inv.getSkuId()));
	}

	/**
	 * Test get inventory details by id when no inventory found.
	 *
	 * @throws NoRecordsFoundException
	 *             the no records found exception
	 */
	@Test
	public void test_getInventoryDetailsById_whenNoInventoryFound() throws NoRecordsFoundException{
		thrown.expect(NoRecordsFoundException.class);
		thrown.expectMessage(is("No Inventory Found in Mongo repository for Passed in inventory id :: " + inv.getId()));
		Mockito.when(inventoryMongoRepository.findOne(inv.getId())).thenReturn(null);
		inventoryService.getInventoryDetailsById(inv.getId());
	}

	/**
	 * Test get inventory details by id.
	 *
	 * @throws NoRecordsFoundException
	 *             the no records found exception
	 */
	@Test
	public void test_getInventoryDetailsById() throws NoRecordsFoundException {
		Mockito.when(inventoryMongoRepository.findOne(inv.getId())).thenReturn(inv);
		assertNotNull(inventoryService.getInventoryDetailsById(inv.getId()));
	}

	/**
	 * Test get inventory details by location id when no inventory found.
	 *
	 * @throws NoRecordsFoundException
	 *             the no records found exception
	 */
	@Test
	public void test_getInventoryDetailsByLocationId_whenNoInventoryFound() throws NoRecordsFoundException {
		thrown.expect(NoRecordsFoundException.class);
		thrown.expectMessage(is("No Inventory Found in Mongo repository for Passed in location id :: " + inv.getLocationId()));
		Mockito.when(inventoryMongoRepository.findByLocationId(inv.getLocationId())).thenReturn(new ArrayList<>());
		inventoryService.getInventoryDetailsByLocationId(inv.getLocationId());
	}

	/**
	 * Test get inventory details by location id.
	 *
	 * @throws NoRecordsFoundException
	 *             the no records found exception
	 */
	@Test
	public void test_getInventoryDetailsByLocationId() throws NoRecordsFoundException {
		Mockito.when(inventoryMongoRepository.findByLocationId(inv.getLocationId())).thenReturn(invList);
		assertNotNull(inventoryService.getInventoryDetailsByLocationId(inv.getLocationId()));
	}

}

/**
 * 
 */
package com.ftd.inventoryservice.domain.inventory;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 * Inventory Document
 * </p>
 * 
 * @author Abhishek Mallick
 *
 */
@Entity
@Data
@NoArgsConstructor
public class Inventory implements Serializable {

	private static final Logger log = LoggerFactory.getLogger(Inventory.class);

	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	@Column(name = "stock_level")
	private long stockLevel;

	@Column(name = "stock_level_with_Fraction")
	private double stockLevelWithFraction;

	@Column(name = "stock_threshold")
	private long stockThreshold;

	@Column(name = "availability_date")
	private Date availabilityDate;

	@Column(name = "back_order_level")
	private long backorderLevel;

	@Column(name = "back_order_level_with_fraction")
	private double backorderLevelWithFraction;

	@Column(name = "back_order_threshold")
	private long backorderThreshold;

	@Column(name = "sku_id")
	private String skuId;

	@Column(name = "creation_date")
	private Date creationDate;

	private String description;

	@Column(name = "display_name")
	private String displayName;

	@Column(name = "end_date")
	private Date endDate;

	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "location_id")
	private String locationId;

	@Column(name = "pre_order_level")
	private long preOrderLevel;

	@Column(name = "pre_order_level_with_fraction")
	private double preOrderLevelWithFraction;

	@Column(name = "pre_order_threshold")
	private long preOrderThreshold;

	@Override
	public String toString() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			log.error("The JSON could not be processed correctly for the inventory string", e);
		}
		return "";
	}

}

package com.ftd.inventoryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * The boot application class that defines API's for Inventory.This boot
 * application uses H2 DB to store the inventory details. <br>
 * <br>
 * 
 * <ol>
 * <li>Act as a Eureka client -This behavior is provided by the
 * {@link @EnableDiscoveryClient} annotation.</li>
 * 
 * <li>@EnableDiscoveryClient makes the app into both a Eureka "instance" (i.e.
 * it registers itself) and a "client" (i.e. it can query the registry to locate
 * other services).</li>
 * 
 * <li>@EnableCircuitBreaker -Annotation will scan the classpath for any
 * compatible Circuit Breaker implementation.</li>
 * </ol>
 * 
 * @author Abhishek Mallick
 *
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableHystrixDashboard
public class InventoryserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryserviceApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}

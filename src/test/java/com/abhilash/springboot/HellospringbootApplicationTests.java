package com.abhilash.springboot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.abhilash.springboot.entities.Product;

@SpringBootTest
class HellospringbootApplicationTests {

	@Value("${hellospringboot.services.url}")
	private String baseURL;

	@Test
	void testGetProduct() {
		System.out.print(baseURL);
		RestTemplate restTemplate = new RestTemplate();
		Product product = restTemplate.getForObject(baseURL + "/products/2", Product.class);
		assertNotNull(product);
		assertEquals("Java", product.getName());
	}
	
	@Test
	public void testCreateProduct() {
		RestTemplate restTemplate = new RestTemplate();
		Product product = new Product();
		product.setName("Samsung");
		product.setDescription("New Phone");
		product.setPrice(1000);
		Product newProduct = restTemplate.postForObject(baseURL + "/products", product, Product.class);
		assertNotNull(newProduct);
		assertNotNull(newProduct.getId());
		assertEquals("Samsung", newProduct.getName());
	}
	
	@Test
	public void testUpdateProduct() {
		RestTemplate restTemplate = new RestTemplate();
		Product product = restTemplate.getForObject(baseURL + "/products/2", Product.class);
		product.setPrice(1000);
		restTemplate.put(baseURL + "/products", product);
	}

}

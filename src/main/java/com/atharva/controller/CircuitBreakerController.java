package com.atharva.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;

@RestController
public class CircuitBreakerController {
	
	private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
	
	@GetMapping("/sample-api")
	@Retry(name = "someName", fallbackMethod = "anyMethodCall")
	public String sampleApi() {
		logger.warn("sample api call received");
//		here a false call is made to get an idea of what happens when api is down
		ResponseEntity<String> entity = new RestTemplate().getForEntity("http://localhost:8080/someTest", String.class);
		return entity.getBody();
	}
	
	@GetMapping("/sample-api2")
	@CircuitBreaker(name = "anyNameCB", fallbackMethod = "anyMethodCall")
	public String sampleApi2() {
		logger.warn("sample api call received");
//		here a false call is made to get an idea of what happens when api is down
		ResponseEntity<String> entity = new RestTemplate().getForEntity("http://localhost:8080/someTest", String.class);
		return entity.getBody();
	}
	
	public String anyMethodCall(Exception ex) {
		return "fall back response";
		
	}
}

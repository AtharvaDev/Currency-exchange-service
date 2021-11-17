package com.atharva.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.atharva.dto.CurrencyExchange;
import com.atharva.repo.CurrencyExchangeRepo;

@RestController
public class CurrencyController {
	
	@Autowired
	private CurrencyExchangeRepo currencyExchangeRepo;
	
	@Autowired
	private Environment environment;

	@GetMapping("currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange getvalue(@PathVariable String from, @PathVariable String to) {
//		CurrencyExchange currencyExchange = new CurrencyExchange(100L, from, to, BigDecimal.valueOf(50));
		
		CurrencyExchange currencyExchange = currencyExchangeRepo.findByFromAndTo(from, to);
		if (currencyExchange ==  null) {
			throw new RuntimeException("no data found");
		}
		
		String port = environment.getProperty("local.server.port");
		currencyExchange.setEnvironment(port);
				
		return currencyExchange;
		
	}
}

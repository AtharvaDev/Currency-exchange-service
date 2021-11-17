package com.atharva.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atharva.dto.CurrencyExchange;

public interface CurrencyExchangeRepo extends JpaRepository<CurrencyExchange, Long> {
	
	CurrencyExchange findByFromAndTo(String from, String to);

}

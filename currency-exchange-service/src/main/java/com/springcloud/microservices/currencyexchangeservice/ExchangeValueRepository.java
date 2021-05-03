package com.springcloud.microservices.currencyexchangeservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ExchangeValueRepository extends JpaRepository<ExchangeValue, Long> {
	
	//@Query("select ex from ExchangeValue ex  where ex.from=(:from) and ex.to=(:to)")
	//Optional<ExchangeValue> findByFromAndTo(@Param("from") String from, @Param("to") String to);
	
	ExchangeValue findByFromAndTo(String from, String to);
	ExchangeValue findByFrom(String from);
}

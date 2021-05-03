package com.springcloud.microservices.currencyexchangeservice;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class CurrencyExchangeConroller {
	
	@Autowired
	private Environment environment;
	
	@Autowired
	private ExchangeValueRepository repository;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	/*
	 * @GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue currencyConverter(@PathVariable String from, @PathVariable String to) {
		
		//ExchangeValue exval =  new ExchangeValue(100L, from, to, BigDecimal.valueOf(70));
		
		Optional<ExchangeValue> exchangeValue =  repository.findByFromAndTo(from, to);
		//This port value will be passed to the other microService CurrencyCalculationService.
		//It will understand from which instance of ms he is getting the response back.
		//it's not a good design, but helps you in understanding.
		
		if(!exchangeValue.isPresent()) {
			System.out.println("It's an excpetion");
		} else {
		
		exchangeValue.get().setPort(Integer.parseInt
				(environment.getProperty("local.server.port")) );
		return exchangeValue.get();
		}
		
		return null;
	}*/
	
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public ExchangeValue retrieveExchangeValue
		(@PathVariable String from, @PathVariable String to){
		
		System.out.println("From val "+from);
		System.out.println("To val "+to);
		
		ExchangeValue exchangeValue = repository.findByFrom(from);
		
		exchangeValue.setPort(
				Integer.parseInt(environment.getProperty("local.server.port")));
		
		logger.info("CurrencyExchangeConroller retrieveExchangeValue -> {}", exchangeValue);
		
		return exchangeValue;
	}
	
	
	
	@GetMapping("/currency-exchange/id/{id}")
	public ExchangeValue retreive(@PathVariable Long id) {
		
		//ExchangeValue exval =  new ExchangeValue(100L, from, to, BigDecimal.valueOf(70));
		
		Optional<ExchangeValue> exval =  repository.findById(id);
		
		
		return exval.get();
	}
}

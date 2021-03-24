package com.example.demo.service;



import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.example.demo.Country;
import com.example.demo.exception.CountryNotFoundException;




@Service
public class CountryService {
	
	public static Logger LOGGER = LoggerFactory.getLogger(CountryService.class);
	ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
	List<Country> countryList = (List<Country>) context.getBean("countryList");

	public Country getCountry(String code) throws CountryNotFoundException {

		LOGGER.info("Inside CountryService getCountry");
		Country country = countryList.stream().filter(cont -> cont.getCode().equalsIgnoreCase(code)).findAny()
				.orElse(null);
		if (country == null) {
			throw new CountryNotFoundException();
		}

		return country;
	}

	

}

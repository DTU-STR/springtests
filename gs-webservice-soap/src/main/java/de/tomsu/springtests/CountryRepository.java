package de.tomsu.springtests;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import io.spring.guides.gs_producing_web_service.Country;
import io.spring.guides.gs_producing_web_service.Currency;

@Repository
public class CountryRepository {

	private static final List<Country> COUNTRIES = new ArrayList<>();
	
	@PostConstruct
	public void initData() {
		Country spain = new Country();
		spain.setName("Spain");
		spain.setCapital("Madrid");
		spain.setCurrency(Currency.EUR);
		spain.setPopulation(46704314);

		COUNTRIES.add(spain);

		Country poland = new Country();
		poland.setName("Poland");
		poland.setCapital("Warsaw");
		poland.setCurrency(Currency.PLN);
		poland.setPopulation(38186860);

		COUNTRIES.add(poland);

		Country uk = new Country();
		uk.setName("United Kingdom");
		uk.setCapital("London");
		uk.setCurrency(Currency.GBP);
		uk.setPopulation(63705000);
		
		COUNTRIES.add(uk);
	}
	
	public Country findCountry(String name) {
		Assert.notNull(name);

		Country result = null;

		for (Country country : COUNTRIES) {
			if (name.equals(country.getName())) {
				result = country;
			}
		}

		return result;
	}
}

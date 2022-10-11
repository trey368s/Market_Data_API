package com.investmenttracker.enterprise;

import com.investmenttracker.enterprise.dto.investment;
import com.investmenttracker.enterprise.service.IInvestmentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class EnterpriseApplicationTests {

	@Autowired
	IInvestmentService investmentService;

	@Test
	void contextLoads() {
	}

	@Test
	void verifyInvestmentProperties() {
		int id = 1;
		String symbol = "AAPL";
		int shares = 30;
		double priceOpened = 145.75;

		investment Investment = new investment();
		Investment.setId(id);
		assertEquals(id, Investment.getId());
		Investment.setSymbol(symbol);
		assertEquals(symbol, Investment.getSymbol());
		Investment.setShares(shares);
		assertEquals(shares, Investment.getShares());
		Investment.setPriceOpened(priceOpened);
		assertEquals(priceOpened, Investment.getPriceOpened());
	}

	@Test
	void verifyAddAndRemoveInvestmentEntries() {
		int id = 2;
		String symbol = "MSFT";
		int shares = 15;
		double priceOpened = 245.33;

		investment Investment = new investment();
		Investment.setId(id);
		Investment.setSymbol(symbol);
		Investment.setShares(shares);
		Investment.setPriceOpened(priceOpened);

		investmentService.save(Investment);

		List<investment> InvestmentEntries = investmentService.fetchAll();
		boolean investmentPresent = false;
		for (investment je : InvestmentEntries) {
			if (je.getId()==(id) && je.getSymbol().equals(symbol) && je.getShares()==(shares) && je.getPriceOpened()==(priceOpened)) {
				investmentPresent = true;
				break;
			}
		}
		assertTrue(investmentPresent);
	}
}

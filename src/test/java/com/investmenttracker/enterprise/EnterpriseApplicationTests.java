package com.investmenttracker.enterprise;

import com.investmenttracker.enterprise.dao.IInvestmentDAO;
import com.investmenttracker.enterprise.dto.Investment;
import com.investmenttracker.enterprise.service.IInvestmentService;
import com.investmenttracker.enterprise.service.InvestmentServiceStub;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class EnterpriseApplicationTests {
	@Autowired
	IInvestmentService investmentService;
	com.investmenttracker.enterprise.dto.Investment Investment = new Investment();

	@MockBean
	private IInvestmentDAO investmentDAO;

	@Test
	void contextLoads() {
	}

	@Test
	void verifyInvestmentProperties() {
		int id = 1;
		String symbol = "AAPL";
		int shares = 30;
		double priceOpened = 145.75;

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

		com.investmenttracker.enterprise.dto.Investment Investment = new Investment();
		Investment.setId(id);
		Investment.setSymbol(symbol);
		Investment.setShares(shares);
		Investment.setPriceOpened(priceOpened);

		investmentService.save(Investment);

		List<com.investmenttracker.enterprise.dto.Investment> InvestmentEntries = investmentService.fetchAll();
		boolean investmentPresent = false;
		for (com.investmenttracker.enterprise.dto.Investment je : InvestmentEntries) {
			if (je.getId()==(id) && je.getSymbol().equals(symbol) && je.getShares()==(shares) && je.getPriceOpened()==(priceOpened)) {
				investmentPresent = true;
				break;
			}
		}
		assertTrue(investmentPresent);
	}

	@Test
	void fetchInvestmentByID_returnsMSFTForID333() {
		givenInvestmentDataAreAvailable();
		whenInvestment333IsMSFT();
		whenInvestment333AddedIsMSFT();
		whenSearchInvestmentWithID333();
		thenReturnOneMSFTInvestmentForID333();
	}

	private void whenInvestment333AddedIsMSFT() {
		com.investmenttracker.enterprise.dto.Investment Investment = new Investment();
		Investment.setId(333);
		Investment.setSymbol("MSFT");
		Mockito.when(investmentDAO.fetch(333)).thenReturn(Investment);
	}

	private void givenInvestmentDataAreAvailable() {
		Mockito.when(investmentDAO.saveInvestment(Investment)).thenReturn(Investment);
		investmentService = new InvestmentServiceStub(investmentDAO);
	}

	private void whenSearchInvestmentWithID333() {
		Investment = investmentService.fetchById(333);
	}

	private void thenReturnOneMSFTInvestmentForID333() {
		String symbol = Investment.getSymbol();
		assertEquals("MSFT", symbol);
	}

	private void whenInvestment333IsMSFT(){
		com.investmenttracker.enterprise.dto.Investment inv = new Investment();
		inv.setId(333);
		inv.setSymbol("MSFT");
		Mockito.when(investmentDAO.fetchId(333)).thenReturn(inv);
	}
}

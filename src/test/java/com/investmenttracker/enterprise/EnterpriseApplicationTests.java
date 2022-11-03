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
	private IInvestmentService investmentService;
	private Investment investment = new Investment();

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

		investment.setId(id);
		assertEquals(id, investment.getId());
		investment.setSymbol(symbol);
		assertEquals(symbol, investment.getSymbol());
		investment.setShares(shares);
		assertEquals(shares, investment.getShares());
		investment.setPriceOpened(priceOpened);
		assertEquals(priceOpened, investment.getPriceOpened());
	}

	@Test
	void verifyAddAndRemoveInvestmentEntries(){
		int id = 2;
		String symbol = "MSFT";
		int shares = 15;
		double priceOpened = 245.33;

		Investment investment = new Investment();
		investment.setId(id);
		investment.setSymbol(symbol);
		investment.setShares(shares);
		investment.setPriceOpened(priceOpened);

		investmentService.save(investment);

		List<Investment> investmentEntries = investmentService.fetchAll();
		boolean investmentPresent = false;
		for (Investment je : investmentEntries) {
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
		Investment investment = new Investment();
		investment.setId(333);
		investment.setSymbol("MSFT");
		Mockito.when(investmentDAO.fetch(333)).thenReturn(investment);
	}

	private void givenInvestmentDataAreAvailable() {
		Mockito.when(investmentDAO.saveInvestment(investment)).thenReturn(investment);
		investmentService = new InvestmentServiceStub(investmentDAO);
	}

	private void whenSearchInvestmentWithID333() {
		investment = investmentService.fetchById(333);
	}

	private void thenReturnOneMSFTInvestmentForID333() {
		String symbol = investment.getSymbol();
		assertEquals("MSFT", symbol);
	}

	private void whenInvestment333IsMSFT(){
		Investment investment = new Investment();
		investment.setId(333);
		investment.setSymbol("MSFT");
		Mockito.when(investmentDAO.fetchId(333)).thenReturn(investment);
	}
}

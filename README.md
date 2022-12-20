# Market Data API
## Introduction
This application will be able to display real time and historical price information for an entered stock. The user will be able to enter a ticker symbol into the application where it will then use the [Alpaca.markets](https://alpaca.markets/docs/api-references/market-data-api/stock-pricing-data/) API to retrieve stock information. The application will then display live trade data of the stock and using the [Lightweight charts](https://www.tradingview.com/lightweight-charts/) library it will graph the information. 
## Video
[![Watch the video](https://img.youtube.com/vi/PhvSioUWOkQ/default.jpg)](https://youtu.be/PhvSioUWOkQ)
## Functional Requirements
Given a correct ticker symbol when the stock market is open then the application will display real time trade data and graph it.  
Given a correct ticker symbol when the stock market is closed then the application will display historical trade data and graph it.  
Given an incorrect ticker symbol while the stock market is either open or closed, the application will prompt the user to enter a correct symbol.  
## Class Diagram
![Market Data Class Diagram](https://github.com/trey368s/Market_Data_API/blob/master/Class%20Diagram.drawio.png)
## JSON Schema
>{
  "bars": [
    {
      "time": "2022-04-11T08:00:00Z",
      "open": 168.99,
      "high": 169.81,
      "low": 168.99,
      "close": 169.01
    }
  ],
  "symbol": "AAPL"
}
## Scrum Roles
Product Owner/Github Admin- Trey Stegeman  
Business Logic-  Brendan Payne  
UI Specialist-  Riley Setser  
Quality Assurance- Max De Santiago  
## Scrum Board
https://github.com/trey368s/Market_Data_API/projects
## Scrum Meetings
Weekly Standup, Tuesdays @ 6:00-6:30PM

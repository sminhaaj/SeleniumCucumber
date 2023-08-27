@mortgage_calculator_test
Feature: Mortgage Calculator

  Scenario: Calculate Monthly Payment
    Given User is in the mortgage calculator home page
    When  User enters data and clicks calculate button
      |HomePrice|DownPmt|LoanAmt|IntRate|LoanTermYrs|PptyTax|Pmi|HOI |Hoa|LoanType|BuyRefi|
      |300000   |60000  |240000 |3      |30         |5000   |0.5|1000|100|FHA     |Buy    |
    Then  Monthly Payment rate is "1,611.85"

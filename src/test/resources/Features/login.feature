@smoke_test
Feature: Test Login functionalities

  Background:
    Given User is on the login page

  @positive_test
  Scenario Outline: Check login is successful with valid credentials
    When  User enters username "<username>" and password "<password>"
    And   User clicks the login button
    Then  User is navigated to home page

    Examples:
    |username| password  |
    |student |Password123|

  @negative_test
  Scenario Outline: Check login is unsuccessful with invalid username
    When  User enters username "<username>" and password "<password>"
    And   User clicks the login button
    Then  User is failed to login for invalid username

    Examples:
      |username | password  |
      |student1 |Password123|
      |studenT  |Password123|

  @negative_test
  Scenario Outline: Check login is unsuccessful with invalid password
    When  User enters username "<username>" and password "<password>"
    And   User clicks the login button
    Then  User is failed to login for invalid password

    Examples:
      |username | password  |
      |student  |Password12 |

  @empty_test @negative_test
  Scenario Outline: Check login is unsuccessful with empty password
    When  User enters username "<username>" and password empty
    And   User clicks the login button
    Then  User is failed to login for invalid password

    Examples:
      |username |
      |student  |

  @data_table_test
  Scenario: Check login is unsuccessful with invalid username with data table
    When  User enters username and password
      |username | password  |
      |student1 |Password123|
    And   User clicks the login button
    Then  User is failed to login for invalid username

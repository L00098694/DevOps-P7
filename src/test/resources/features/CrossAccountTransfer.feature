Feature: Cross-account Transfer
  This feature describes various scenarios for users transferring money between their Revolut accounts.

  #As a user, I can send funds to another account

  # Uses a data-table for the given step
  Scenario: Successful transfer of money between accounts
    Given the following users
      | name   | balance |
      | Claire |     200 |
      | Bob    |       0 |
    And Claire selects 50 euro as the transfer amount
    When Claire transfers to Bob
    Then Claire should now have 150 euro in her euro Revolut account
    And Bob should now have 50 euro in his euro Revolut account

  # Not using a datatable here to illustrate the difference between the previous scenario
  Scenario: Failed transfer of money between accounts
    Given Danny has 0 euro in his euro Revolut account
    And Bob has 0 euro in his euro Revolut account
    And Danny selects 50 euro as the transfer amount
    When Danny transfers to Bob
    Then Danny should now have 0 euro in his euro Revolut account
    And Bob should now have 0 euro in his euro Revolut account

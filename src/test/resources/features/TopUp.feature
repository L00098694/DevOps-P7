Feature: TopUp Account
  This feature describes various scenarios for users adding funds to their revolut account(s)

  #As a user, I can topup my Revolut account using my debit card

  Scenario: Add money to Revolut account using debit card
    Given Danny has 10 euro in his euro Revolut account
    And Danny selects 100 euro as the topUp amount
    And  Danny selects his DebitCard as his topUp method
    And Danny has 100 euro available in his topUp method
    When Danny tops up
    Then Danny should now have 110 euro in his euro Revolut account


  Scenario: Add money to Revolut account using bank account
    Given Danny has 20 euro in his euro Revolut account
    And Danny selects 230 euro as the topUp amount
    And  Danny selects his BankAccount as his topUp method
    And Danny has 230 euro available in his topUp method
    When Danny tops up
    Then Danny should now have 250 euro in his euro Revolut account



  #ToDo implement the remaining scenarios listed below

  Scenario Outline: Add various amounts to Revolut account
    Given Danny has <startBalance> euro in his euro Revolut account
    And Danny selects his DebitCard as his topUp method
    And Danny has <topUpAmount> euro available in his topUp method
    And Danny selects <topUpAmount> euro as the topUp amount
    When Danny tops up
    Then Danny should now have <newBalance> euro in his euro Revolut account
    Examples:
      | startBalance| topUpAmount | newBalance  |
      | 0           | 100         | 100         |
      | 14          | 20          | 34          |
      | 23          | 30          | 53          |

  Rule: The account balance shouldn't change if the topup payment request is rejected by the payment service

    Scenario: Payment service rejects the request
      Given Danny has 0 euro in his euro Revolut account
      And Danny selects his BankAccount as his topUp method
      And Danny has 0 euro available in his topUp method
      And Danny selects 1000 euro as the topUp amount
      When Danny tops up
      Then Danny should now have 0 euro in his euro Revolut account


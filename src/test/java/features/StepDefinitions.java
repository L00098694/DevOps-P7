package features;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import revolut.*;

public class StepDefinitions {

    private double topUpAmount;
    private double transferAmount;

    PaymentService topUpMethod;

    // Test users
    Person danny;
    Person bob;

    @Before//Before hooks run before the first step in each scenario
    public void setUp(){
        //We can use this to setup test data for each scenario
        danny = new Person("Danny");
        bob = new Person("Bob");
    }

    @Given("Danny has {double} euro in his euro Revolut account")
    public void dannyHasEuroInHisEuroRevolutAccount(double startingBalance) {
        danny.setAccountBalance(startingBalance);
    }

    @And("Bob has {double} euro in his euro Revolut account")
    public void bobHasEuroInHisEuroRevolutAccount(double startingBalance) {
        bob.setAccountBalance(startingBalance);
    }

    @Given("Danny selects {double} euro as the topUp amount")
    public void danny_selects_euro_as_the_top_up_amount(double topUpAmount) {
        this.topUpAmount = topUpAmount;
    }

    @And("Danny selects {double} euro as the transfer amount")
    public void dannySelectsEuroAsTheTransferAmount(double transferAmount) {
        this.transferAmount = transferAmount;
    }

    @Given("Danny selects his {paymentService} as his topUp method")
    public void danny_selects_his_debit_card_as_his_top_up_method(PaymentService topUpSource) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("The selected payment service type was " + topUpSource.getType());
        topUpMethod = topUpSource;
    }

    @Given("Danny has {} euro available in his topUp method")
    public void danny_has_euro_available_in_his_topUp_method(double euro) {
        topUpMethod.setAvailableBalance(euro);
    }

    @When("Danny tops up")
    public void danny_tops_up() {
        // Write code here that turns the phrase above into concrete actions
        Account dannysAccount = danny.getAccount("EUR");
        topUpMethod.withdraw(dannysAccount, topUpAmount);
    }

    @When("Danny transfers to Bob")
    public void dannyTransfersToBob() {
        // Write code here that turns the phrase above into concrete actions
        Account dannysAccount = danny.getAccount("EUR");
        Account bobsAccount = bob.getAccount("EUR");
        dannysAccount.transfer(transferAmount, bobsAccount);
    }

    @Then("Danny should now have {double} euro in his euro Revolut account")
    public void dannyShouldNowHaveEuroInHisEuroRevolutAccount(double newBalance) {
        //Arrange
        double expectedResult = newBalance;
        //Act
        double actualResult = danny.getAccount("EUR").getBalance();
        //Assert
        Assert.assertEquals(expectedResult, actualResult,0);
        System.out.println("The new final balance is: " + actualResult);
    }

    @And("Bob should now have {double} euro in his euro Revolut account")
    public void bobShouldNowHaveEuroInHisEuroRevolutAccount(double newBalance) {
        //Arrange
        double expectedResult = newBalance;
        //Act
        double actualResult = bob.getAccount("EUR").getBalance();
        //Assert
        Assert.assertEquals(expectedResult, actualResult,0);
        System.out.println("The new final balance is: " + actualResult);
    }
}

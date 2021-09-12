package features;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import revolut.*;

import java.util.HashMap;
import java.util.Map;

public class StepDefinitions {

    private double topUpAmount;
    private double transferAmount;

    PaymentService topUpMethod;

    // Test users
    HashMap<String, Person> people = new HashMap<>();

    @Before//Before hooks run before the first step in each scenario
    public void setUp(){
        //We can use this to setup test data for each scenario
        Person danny = new Person("Danny");
        Person bob = new Person("Bob");

        this.people.put("Danny", danny);
        this.people.put("Bob", bob);
    }

    @Given("{word} has {double} euro in his/her/their euro Revolut account")
    public void dannyHasEuroInHisEuroRevolutAccount(String name, double startingBalance) {
        Person person = this.people.get(name);
        person.setAccountBalance(startingBalance);
    }

    @Given("{word} selects {double} euro as the topUp amount")
    public void danny_selects_euro_as_the_top_up_amount(String name, double topUpAmount) {
        System.out.println(name + " has selected " + topUpAmount + " euro as the top-up amount.");
        this.topUpAmount = topUpAmount;
    }

    @And("{word} selects {double} euro as the transfer amount")
    public void dannySelectsEuroAsTheTransferAmount(String name, double transferAmount) {
        System.out.println(name + " has selected " + transferAmount + " euro as the transfer amount.");
        this.transferAmount = transferAmount;
    }

    @Given("{word} selects his/her/their {paymentService} as his/her/their topUp method")
    public void danny_selects_his_debit_card_as_his_top_up_method(String name, PaymentService topUpSource) {
        System.out.println(name + " selected payment type: " + topUpSource.getType());
        topUpMethod = topUpSource;
    }

    @Given("{word} has {} euro available in his/her/their topUp method")
    public void danny_has_euro_available_in_his_topUp_method(String name, double euro) {
        System.out.println(name + "'s available " + topUpMethod.getType() + " balance is: " + euro + " euro.");
        topUpMethod.setAvailableBalance(euro);
    }

    @When("{word} tops up")
    public void danny_tops_up(String name) {
        // Write code here that turns the phrase above into concrete actions
        Person person = this.people.get(name);
        Account personsAccount = person.getAccount("EUR");
        topUpMethod.withdraw(personsAccount, topUpAmount);
    }

    @When("{word} transfers to {word}")
    public void dannyTransfersToBob(String sourceName, String targetName) {
        // Write code here that turns the phrase above into concrete actions
        Account sourceAccount = this.people.get(sourceName).getAccount("EUR");
        Account targetAccount = this.people.get(targetName).getAccount("EUR");
        sourceAccount.transfer(transferAmount, targetAccount);
    }

    @Then("{word} should now have {double} euro in his/her/their euro Revolut account")
    public void dannyShouldNowHaveEuroInHisEuroRevolutAccount(String name, double newBalance) {
        //Arrange
        double expectedResult = newBalance;
        //Act
        double actualResult = this.people.get(name).getAccount("EUR").getBalance();
        //Assert
        Assert.assertEquals(expectedResult, actualResult,0);
        System.out.println("The new final balance is: " + actualResult);
    }

    @Given("the following users")
    public void theFollowingUsers(DataTable dataTable) {
        for (Map<String, String> personData: dataTable.asMaps()) {
            String name = personData.get("name");
            double startBalance = Double.parseDouble(personData.get("balance"));

            Person person = new Person(name);
            person.setAccountBalance(startBalance);

            this.people.put(name, person);
        }
    }
}

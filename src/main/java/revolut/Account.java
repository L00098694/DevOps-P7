package revolut;

import java.util.Currency;

public class Account {
    private Currency accCurrency;
    private double balance;

    public Account(Currency currency, double balance){
        this.balance = balance;
        this.accCurrency = currency;
    }

    public void setBalance(double newBalance) {
        this.balance = newBalance;
    }

    public double getBalance() {
        return this.balance;
    }

    public void addFunds(double topUpAmount) {
        this.balance += topUpAmount;
    }

    public void transfer(double transferAmount, Account targetAccount) {
        if (transferAmount <= this.balance) {
            // Subtract the funds
            this.balance -= transferAmount;
            // Add funds to the target account
            targetAccount.addFunds(transferAmount);
        }
    }
}

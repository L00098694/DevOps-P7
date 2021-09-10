package revolut;

public class PaymentService {
    private final String serviceName;
    private double availableBalance;

    public PaymentService(String name){
        this.serviceName = name;
    }

    public String getType() {
        return serviceName;
    }

    public void withdraw(Account targetAccount, double amountRequested) {
        if (availableBalance >= amountRequested) {
            // Reduce available balance for payment method
            availableBalance -= amountRequested;

            // Top up the account
            targetAccount.addFunds(amountRequested);
        }
    }

    public void setAvailableBalance(double availableBalance) {
        this.availableBalance = availableBalance;
    }
}

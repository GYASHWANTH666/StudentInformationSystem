import java.util.HashMap;

public class Bank {
    private HashMap<String, BankAccount> accounts;

    public Bank() {
        accounts = new HashMap<>();
    }

    public void createAccount(String accountNumber) {
        if (!accounts.containsKey(accountNumber)) {
            accounts.put(accountNumber, new BankAccount(accountNumber));
            System.out.println("Account created: " + accountNumber);
        } else {
            System.out.println("Account already exists.");
        }
    }

    public BankAccount getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }
}

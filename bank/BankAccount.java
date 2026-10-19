public class BankAccount {

    public static final double MIN_AMOUNT = 0.01;
    public static final double MAX_AMOUNT = 1_000_000_000.0;

    private static int accountsCount = 0;

    private final String owner;
    private final String accountNumber;
    private final String accountName;
    private double balance;

    public BankAccount(String owner, String accountNumber) {
        this(owner, accountNumber, 0.0);
    }

    public BankAccount(String owner, String accountNumber, double initialBalance) {
        this(owner, accountNumber, "Текущий", initialBalance);
    }

    public BankAccount(String owner, String accountNumber,
                       String accountName, double initialBalance) {
        if (accountNumber == null || !accountNumber.matches("[0-9]{16}")) {
            throw new IllegalArgumentException(
                    "Номер счёта должен содержать ровно 16 цифр."
            );
        }

        if (!Double.isFinite(initialBalance) || initialBalance < 0) {
            throw new IllegalArgumentException(
                    "Начальный баланс должен быть конечным числом не меньше 0."
            );
        }

        this.owner = owner;
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.balance = roundToCents(initialBalance);

        accountsCount++;
    }

    public String getOwner() {
        return owner;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public double getBalance() {
        return balance;
    }

    public static int getAccountsCount() {
        return accountsCount;
    }

    public boolean deposit(double amount) {
        if (!isValidAmount(amount)) {
            return false;
        }

        balance = roundToCents(balance + roundToCents(amount));
        return true;
    }

    public boolean withdraw(double amount) {
        if (!isValidAmount(amount) || roundToCents(amount) > balance) {
            return false;
        }

        balance = roundToCents(balance - roundToCents(amount));
        return true;
    }

    private static boolean isValidAmount(double amount) {
        return Double.isFinite(amount)
                && amount >= MIN_AMOUNT
                && amount <= MAX_AMOUNT;
    }

    private static double roundToCents(double value) {
        return Math.round(value * 100) / 100.0;
    }
}
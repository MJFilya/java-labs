private static final Locale RUSSIAN = Locale.forLanguageTag("ru-RU");
private static final Scanner SCANNER = new Scanner(System.in);

void main() {
    BankAccount[] accounts = {
            new BankAccount("Илья", "4081781000000001", "Текущий счёт", 10000.0),
            new BankAccount("Илья", "4081781000000002", "Кредитный счёт", 5000.0),
            new BankAccount("Илья", "4081781000000003", "Зарплатный счёт", 0.0)
    };

    BankAccount selected = accounts[0];
    boolean running = true;

    IO.println("""
            ════════════════════════════════════════
                              БАНК
                        Добро пожаловать!
            ════════════════════════════════════════""");

    while (running) {
        printMenu(selected);

        String choice = readLine("Ваш выбор: ");
        if (choice == null) {
            break;
        }

        IO.println();

        switch (choice) {
            case "1" -> printAccounts(accounts, selected);
            case "2" -> selected = chooseAccount(accounts, selected);
            case "3" -> printAccountInfo(selected);
            case "4" -> makeOperation(selected, false);
            case "5" -> makeOperation(selected, true);
            case "0" -> running = false;
            default -> IO.println("✗ Нет такого пункта. Выберите от 0 до 5.");
        }
    }

    IO.println("""
            
            ════════════════════════════════════════
                          До свидания!
            ════════════════════════════════════════""");
}

private static void printMenu(BankAccount account) {
    System.out.printf("""
            
            ────────────────────────────────────────
              Счёт:    %s
              Баланс:  %s
            ────────────────────────────────────────
              1  Мои счета
              2  Сменить счёт
              3  О счёте
              4  Пополнить
              5  Снять
              0  Выход
            ────────────────────────────────────────
            """, account.getAccountName(), formatMoney(account.getBalance()));
}

private static void printAccounts(BankAccount[] accounts, BankAccount selected) {
    int nameWidth = 0;
    int moneyWidth = 0;

    for (BankAccount account : accounts) {
        nameWidth = Math.max(nameWidth, account.getAccountName().length());
        moneyWidth = Math.max(moneyWidth, formatMoney(account.getBalance()).length());
    }

    String rowFormat = "  %d. %-" + nameWidth + "s   %" + moneyWidth + "s%s%n";

    IO.println("Мои счета (всего: " + BankAccount.getAccountsCount() + "):");

    for (int i = 0; i < accounts.length; i++) {
        String mark = accounts[i] == selected ? "  ← выбран" : "";

        System.out.printf(rowFormat, i + 1, accounts[i].getAccountName(),
                formatMoney(accounts[i].getBalance()), mark);
    }
}

private static BankAccount chooseAccount(BankAccount[] accounts, BankAccount selected) {
    printAccounts(accounts, selected);
    IO.println();

    String input = readLine("Номер счёта (1–" + accounts.length + "): ");
    if (input == null) {
        return selected;
    }

    try {
        int position = Integer.parseInt(input);

        if (position >= 1 && position <= accounts.length) {
            BankAccount account = accounts[position - 1];
            IO.println("✓ Выбран счёт «" + account.getAccountName() + "»");
            return account;
        }

        IO.println("✗ Такого счёта нет в списке.");
    } catch (NumberFormatException e) {
        IO.println("✗ Введите номер из списка, например 1.");
    }

    return selected;
}

private static void printAccountInfo(BankAccount account) {
    String formattedNumber = account.getAccountNumber().replaceAll("(\\d{4})(?=\\d)", "$1 ");

    System.out.printf("""
                    О счёте:
                      Название:  %s
                      Владелец:  %s
                      Номер:     %s
                      Баланс:    %s
                    """, account.getAccountName(), account.getOwner(),
            formattedNumber, formatMoney(account.getBalance()));
}

private static void makeOperation(BankAccount account, boolean isWithdraw) {
    String input = readLine(isWithdraw ? "Сколько снять, ₽: " : "Сколько внести, ₽: ");
    if (input == null) {
        return;
    }

    double amount;
    try {
        amount = Double.parseDouble(input.replace(',', '.'));
    } catch (NumberFormatException e) {
        amount = Double.NaN;
    }

    boolean success = isWithdraw ? account.withdraw(amount) : account.deposit(amount);

    if (success) {
        IO.println((isWithdraw ? "✓ Снято " : "✓ Внесено ") + formatMoney(amount)
                + ". Баланс: " + formatMoney(account.getBalance()));
    } else {
        printAmountError(amount, account);
    }
}

private static void printAmountError(double amount, BankAccount account) {
    if (!Double.isFinite(amount)) {
        IO.println("✗ Это не число. Пример: 500 или 125,50");
    } else if (amount < BankAccount.MIN_AMOUNT) {
        IO.println("✗ Сумма должна быть больше нуля (от "
                + formatMoney(BankAccount.MIN_AMOUNT) + ").");
    } else if (amount > BankAccount.MAX_AMOUNT) {
        IO.println("✗ За один раз — не больше "
                + formatMoney(BankAccount.MAX_AMOUNT) + ".");
    } else {
        IO.println("✗ Не хватает денег: на счёте " + formatMoney(account.getBalance())
                + ", а нужно " + formatMoney(amount) + ".");
    }
}

private static String readLine(String prompt) {
    IO.print(prompt);
    return SCANNER.hasNextLine() ? SCANNER.nextLine().trim() : null;
}

private static String formatMoney(double amount) {
    return String.format(RUSSIAN, "%,.2f ₽", amount);
}
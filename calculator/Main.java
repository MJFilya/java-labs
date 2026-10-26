void main() {
    IO.println("""
            ════════════════════════════════════════
                   УНИВЕРСАЛЬНЫЙ КАЛЬКУЛЯТОР
            ════════════════════════════════════════""");

    Calculator<Integer> integers = new Calculator<>(5, 2);
    printResults("Целые числа: 5 и 2", integers);

    Calculator<Double> doubles = new Calculator<>(7.5, -2.5);
    printResults("Дробные числа: 7.5 и -2.5", doubles);

    Calculator<Integer> negatives = new Calculator<>(-12, -5);
    printResults("Отрицательные числа: -12 и -5", negatives);

    IO.println("\nДеление на ноль: 10 и 0");
    Calculator<Integer> zero = new Calculator<>(10, 0);
    try {
        IO.println("  Частное: " + zero.divide());
    } catch (ArithmeticException e) {
        IO.println("  Частное: " + e.getMessage());
    }

    IO.println("\nКвадрат числа");
    IO.println("  Квадрат 4: " + Calculator.square(4));
    IO.println("  Квадрат -1.5: " + Calculator.square(-1.5));
}

private static void printResults(String title, Calculator<?> calculator) {
    IO.println("\n" + title);
    IO.println("  Сумма: " + calculator.add());
    IO.println("  Разность: " + calculator.subtract());
    IO.println("  Произведение: " + calculator.multiply());
    IO.println("  Частное: " + calculator.divide());
    IO.println("  Среднее: " + calculator.average());
}

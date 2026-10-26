public class Calculator<T extends Number> {

    private final T first;
    private final T second;

    public Calculator(T first, T second) {
        if (first == null || second == null) {
            throw new IllegalArgumentException("Числа не могут быть null");
        }
        this.first = first;
        this.second = second;
    }

    public double add() {
        return first.doubleValue() + second.doubleValue();
    }

    public double subtract() {
        return first.doubleValue() - second.doubleValue();
    }

    public double multiply() {
        return first.doubleValue() * second.doubleValue();
    }

    public double divide() {
        if (second.doubleValue() == 0) {
            throw new ArithmeticException("Деление на ноль невозможно");
        }
        return first.doubleValue() / second.doubleValue();
    }

    public double average() {
        return add() / 2;
    }

    public static <N extends Number> double square(N value) {
        return value.doubleValue() * value.doubleValue();
    }
}

void main() {
    IO.println("""
            ════════════════════════════════════════
                    УНИВЕРСАЛЬНОЕ ХРАНИЛИЩЕ
            ════════════════════════════════════════""");

    IO.println("\nХранилище строк");
    Storage<String> names = new Storage<>(new String[3]);
    printInfo(names);

    names.add("Анна");
    names.add("Борис");
    names.add("Виктор");
    IO.println("\nДобавлены: Анна, Борис, Виктор");
    printInfo(names);

    IO.println("\nЭлемент 1: " + names.get(1));

    names.set(1, "Богдан");
    IO.println("Элемент 1 заменён на Богдан");
    printInfo(names);

    IO.println("\nУдалён элемент 0: " + names.remove(0));
    printInfo(names);

    IO.println("\nХранилище чисел");
    Storage<Integer> numbers = new Storage<>(new Integer[2]);
    printInfo(numbers);

    numbers.add(10);
    numbers.add(25);
    IO.println("\nДобавлены: 10, 25");
    printInfo(numbers);

    IO.println("\nЭлемент 0: " + numbers.get(0));

    numbers.set(0, 15);
    IO.println("Элемент 0 заменён на 15");
    printInfo(numbers);

    IO.println("\nУдалён элемент 1: " + numbers.remove(1));
    printInfo(numbers);

    IO.println("\nПроверка ошибок");
    names.add("Дмитрий");
    IO.println("Хранилище строк заполнено: " + names);

    try {
        names.add("Елена");
    } catch (IllegalStateException e) {
        IO.println("Добавление Елены: " + e.getMessage());
    }

    try {
        IO.println("Элемент 5: " + names.get(5));
    } catch (IndexOutOfBoundsException e) {
        IO.println("Элемент 5: " + e.getMessage());
    }

    try {
        IO.println("Элемент -1: " + names.get(-1));
    } catch (IndexOutOfBoundsException e) {
        IO.println("Элемент -1: " + e.getMessage());
    }
}

private static void printInfo(Storage<?> storage) {
    IO.println("  Ячейки: " + storage);
    IO.println("  Элементов: " + storage.size() + ", вместимость: " + storage.capacity()
            + ", пусто: " + (storage.isEmpty() ? "да" : "нет"));
}

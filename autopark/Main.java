void main() {
    Vehicle[] vehicles = createVehicles();
    boolean running = true;

    IO.println("""
            ════════════════════════════════════════
                            АВТОПАРК
                       Добро пожаловать!
            ════════════════════════════════════════""");

    while (running) {
        IO.println("""

                ────────────────────────────────────────
                  1  Список транспорта
                  2  Все в путь
                  3  Особые действия
                  0  Выход
                ────────────────────────────────────────""");

        String choice = IO.readln("Ваш выбор: ");
        if (choice == null) {
            break;
        }

        IO.println();

        switch (choice.strip()) {
            case "1" -> printVehicles(vehicles);
            case "2" -> moveAll(vehicles);
            case "3" -> doSpecialActions(vehicles);
            case "0" -> running = false;
            default -> IO.println("Нет такого пункта, выберите от 0 до 3");
        }
    }

    IO.println("""

            ════════════════════════════════════════
                          До свидания!
            ════════════════════════════════════════""");
}

private static Vehicle[] createVehicles() {
    Vehicle car = new Car("Lada", "Vesta", 2022, new Vehicle.Engine(106, "бензиновый"), 4);
    Vehicle motorcycle = new Motorcycle("Yamaha", "MT-07", 2023, new Vehicle.Engine(73, "бензиновый"), false);
    Vehicle truck = new Truck("КАМАЗ", "65115", 2020, new Vehicle.Engine(300, "дизельный"), 15.0);

    Vehicle bus = new Vehicle("ЛиАЗ", "5292", 2019, new Vehicle.Engine(290, "дизельный")) {
        @Override
        public void move() {
            IO.println(getName() + " едет по маршруту");
        }
    };

    return new Vehicle[] {car, motorcycle, truck, bus};
}

private static void printVehicles(Vehicle[] vehicles) {
    IO.println("Список транспорта:");
    for (Vehicle vehicle : vehicles) {
        vehicle.printInfo();
    }
}

private static void moveAll(Vehicle[] vehicles) {
    IO.println("Все в путь:");
    for (Vehicle vehicle : vehicles) {
        vehicle.move();
    }
}

private static void doSpecialActions(Vehicle[] vehicles) {
    IO.println("Особые действия:");
    for (Vehicle vehicle : vehicles) {
        doSpecialAction(vehicle);
    }
}

private static void doSpecialAction(Vehicle vehicle) {
    if (vehicle instanceof Car car) {
        car.openTrunk();
        return;
    }

    if (vehicle instanceof Motorcycle motorcycle) {
        motorcycle.doWheelie();
        return;
    }

    if (vehicle instanceof Truck truck) {
        truck.loadCargo(8.5);
        truck.loadCargo(20.0);
        return;
    }

    IO.println(vehicle.getName() + ": особых действий нет");
}

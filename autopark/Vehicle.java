public abstract class Vehicle {

    private final String brand;
    private final String model;
    private final int year;
    private final Engine engine;

    protected Vehicle(String brand, String model, int year, Engine engine) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.engine = engine;
    }

    public String getName() {
        return brand + " " + model;
    }

    public final void printInfo() {
        IO.println(getName() + ", " + year + " год");
        engine.printEngineInfo();
    }

    public abstract void move();

    public static class Engine {

        private final int power;
        private final String type;

        public Engine(int power, String type) {
            this.power = power;
            this.type = type;
        }

        public void printEngineInfo() {
            IO.println("  Двигатель: " + power + " л.с., " + type);
        }
    }
}

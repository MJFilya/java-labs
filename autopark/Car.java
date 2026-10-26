public class Car extends Vehicle {

    private final int passengerCount;

    public Car(String brand, String model, int year, Engine engine, int passengerCount) {
        super(brand, model, year, engine);
        this.passengerCount = passengerCount;
    }

    public void openTrunk() {
        IO.println(getName() + ": багажник открыт");
    }

    @Override
    public void move() {
        IO.println(getName() + " едет по городу, пассажиров: " + passengerCount);
    }
}

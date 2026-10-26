public class Truck extends Vehicle {

    private final double maxLoad;

    public Truck(String brand, String model, int year, Engine engine, double maxLoad) {
        super(brand, model, year, engine);
        this.maxLoad = maxLoad;
    }

    public void loadCargo(double weight) {
        if (weight > 0 && weight <= maxLoad) {
            IO.println(getName() + ": погружено " + weight + " т");
        } else {
            IO.println(getName() + ": груз " + weight + " т не принят, грузоподъёмность " + maxLoad + " т");
        }
    }

    @Override
    public void move() {
        IO.println(getName() + " едет на погрузку");
    }
}

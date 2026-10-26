public class Motorcycle extends Vehicle {

    private final boolean hasSidecar;

    public Motorcycle(String brand, String model, int year, Engine engine, boolean hasSidecar) {
        super(brand, model, year, engine);
        this.hasSidecar = hasSidecar;
    }

    public void doWheelie() {
        if (hasSidecar) {
            IO.println(getName() + ": с коляской трюк невозможен");
        } else {
            IO.println(getName() + " встаёт на заднее колесо");
        }
    }

    @Override
    public void move() {
        if (hasSidecar) {
            IO.println(getName() + " едет с коляской");
        } else {
            IO.println(getName() + " мчится по трассе");
        }
    }
}

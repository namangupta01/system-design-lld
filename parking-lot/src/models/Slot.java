package models;

public class Slot {
    private final Integer slotNumber;
    private Car car;


    public Slot(Integer slotNumber) {
        this.slotNumber = slotNumber;
        this.car = null;
    }

    public Integer getSlotNumber() {
        return slotNumber;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void removeCar(Car car) {
        this.car = null;
    }
}

package service.strategy;

import exceptions.ParkingLotAlreadyExistException;

import java.util.NoSuchElementException;
import java.util.TreeSet;

public class NaturalOrderingParkingStrategy implements ParkingStrategy {

    TreeSet<Integer> slots;

    public NaturalOrderingParkingStrategy() {
        this.slots = new TreeSet<>();
    }

    @Override
    public void createParkingLot(Integer capacity) {
        for (int i = 1; i <= capacity; i++) {
            this.slots.add(i);
        }
    }

    @Override
    public void reserveSlot(Integer slot) {
        slots.remove(slot);
    }

    @Override
    public void freeSlot(Integer slot) {
        slots.add(slot);
    }

    @Override
    public Integer getFreeSlot() throws NoSuchElementException {
        return slots.first();
    }
}

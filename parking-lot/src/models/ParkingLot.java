package models;

import java.util.HashMap;
import java.util.List;

public class ParkingLot {
    private final HashMap<Integer, Slot> numberToSlotMap;

    public ParkingLot() {
        this.numberToSlotMap = new HashMap<>();
    }

    public Slot getSlot(Integer number) {
        return numberToSlotMap.getOrDefault(number, null);
    }

    public List<Slot> getSlots() {
        return this.numberToSlotMap.values().stream().toList();
    }

    public Integer getParkingLotSize() {
        return this.numberToSlotMap.size();
    }

    public void setSlot(Integer number, Slot slot) {
        this.numberToSlotMap.put(number, slot);
    }
}

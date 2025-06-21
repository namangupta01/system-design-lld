package models;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ParkingLotFloor {
    private final Integer floorNumber;
    private final List<ParkingLotFloorSlot> parkingLotFloorSlotList;

    public ParkingLotFloor(Integer floorNumber) {
        this.floorNumber = floorNumber;
        this.parkingLotFloorSlotList = new CopyOnWriteArrayList<>();
    }

    public List<ParkingLotFloorSlot> getParkingLotFloorSlotList() {
        return parkingLotFloorSlotList;
    }

    public void addSlot(ParkingLotFloorSlot slot) {
        this.parkingLotFloorSlotList.add(slot);
    }

    public Integer getFloorNumber() {
        return floorNumber;
    }
}

package models;

import java.util.List;

public class ParkingLotFloor {
    private final Integer floorNumber;
    private List<ParkingLotFloorSlot> parkingLotFloorSlotList;

    public ParkingLotFloor(Integer floorNumber) {
        this.floorNumber = floorNumber;
    }

    public List<ParkingLotFloorSlot> getParkingLotFloorSlotList() {
        return parkingLotFloorSlotList;
    }

    public void setParkingLotFloorSlotList(List<ParkingLotFloorSlot> parkingLotFloorSlotList) {
        this.parkingLotFloorSlotList = parkingLotFloorSlotList;
    }

    public Integer getFloorNumber() {
        return floorNumber;
    }
}

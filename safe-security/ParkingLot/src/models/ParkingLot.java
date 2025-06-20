package models;

import java.util.List;

public class ParkingLot {

    List<ParkingLotFloor> parkingLotFloors;

    public List<ParkingLotFloor> getParkingLotFloors() {
        return parkingLotFloors;
    }

    public void setParkingLotFloors(List<ParkingLotFloor> parkingLotFloors) {
        this.parkingLotFloors = parkingLotFloors;
    }
}

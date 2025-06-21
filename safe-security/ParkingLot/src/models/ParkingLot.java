package models;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ParkingLot {

    private static volatile ParkingLot INSTANCE;
    private final List<ParkingLotFloor> parkingLotFloors;

    private ParkingLot() {
        this.parkingLotFloors = new CopyOnWriteArrayList<>();
    }

    public static ParkingLot getInstance() {
        if (INSTANCE == null) {
            synchronized (ParkingLot.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ParkingLot();
                }
            }
        }
        return INSTANCE;
    }

    public List<ParkingLotFloor> getParkingLotFloors() {
        return parkingLotFloors;
    }

    public void addFloor(ParkingLotFloor floor) {
        this.parkingLotFloors.add(floor);
    }
}

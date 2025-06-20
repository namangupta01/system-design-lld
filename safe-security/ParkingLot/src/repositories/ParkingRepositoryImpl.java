package repositories;

import models.ParkingLot;

public class ParkingRepositoryImpl implements IParkingRepository {
    private ParkingLot parkingLot = null;

    @Override
    public ParkingLot save(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
        return parkingLot;
    }

    @Override
    public ParkingLot get() {
        return this.parkingLot;
    }
}

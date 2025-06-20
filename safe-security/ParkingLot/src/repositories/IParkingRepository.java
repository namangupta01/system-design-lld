package repositories;

import models.ParkingLot;

public interface IParkingRepository {
    ParkingLot save(ParkingLot parkingLot);

    ParkingLot get();
}

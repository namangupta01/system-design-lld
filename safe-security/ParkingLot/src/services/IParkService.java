package services;

import models.ParkingLotFloorSlot;
import models.Vehicle;

public interface IParkService {
    ParkingLotFloorSlot parkVehicle(Vehicle vehicle);
}

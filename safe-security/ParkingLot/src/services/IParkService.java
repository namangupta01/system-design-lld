package services;

import enums.VehicleTypes;
import models.ParkingLotFloorSlot;

public interface IParkService {
    ParkingLotFloorSlot park(String vehicleNumber, VehicleTypes vehicleType);
}

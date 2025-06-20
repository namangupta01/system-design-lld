package services;

import enums.VehicleTypes;
import models.ParkingLotFloorSlot;

public interface IUnParkService {
    ParkingLotFloorSlot unPark(String vehicleNumber);
}

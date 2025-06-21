package services;

import models.ParkingLotFloorSlot;
import models.Vehicle;

import java.util.Optional;

public interface IParkingRule {
    Optional<ParkingLotFloorSlot> findSlot(Vehicle vehicle);
    void park(ParkingLotFloorSlot slot, Vehicle vehicle);
} 
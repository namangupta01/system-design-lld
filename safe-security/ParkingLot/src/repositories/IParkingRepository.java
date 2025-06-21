package repositories;

import enums.VehicleTypes;
import models.ParkingLot;
import models.ParkingLotFloorSlot;
import models.Vehicle;
import services.specifications.SlotSpecification;

import java.util.Optional;

public interface IParkingRepository {
    ParkingLot save(int numFloors, int numSlotsPerFloor);

    Optional<ParkingLotFloorSlot> findSlot(SlotSpecification spec);

    void parkVehicle(ParkingLotFloorSlot slot, Vehicle vehicle);

    Optional<ParkingLotFloorSlot> findParkedVehicle(String vehicleNumber);

    void unParkVehicle(ParkingLotFloorSlot slot);
}

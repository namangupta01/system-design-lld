package services;

import enums.VehicleTypes;
import models.ParkingLotFloorSlot;
import models.Vehicle;
import repositories.IParkingRepository;
import services.specifications.OrSpecification;
import services.specifications.SlotSpecification;
import services.specifications.VehicleTypeSpecification;

import java.util.Optional;

public class WeekendParkingRule implements IParkingRule {
    private final IParkingRepository parkingRepository;

    public WeekendParkingRule(IParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    @Override
    public Optional<ParkingLotFloorSlot> findSlot(Vehicle vehicle) {
        SlotSpecification spec;
        if (vehicle.getVehicleType() == VehicleTypes.TWO_WHEELER) {
            // For a two-wheeler on the weekend, the spec is:
            // (Slot is for a TWO_WHEELER OR slot is for a FOUR_WHEELER)
            spec = new OrSpecification(
                    new VehicleTypeSpecification(VehicleTypes.TWO_WHEELER),
                    new VehicleTypeSpecification(VehicleTypes.FOUR_WHEELER)
            );
        } else {
            // For any other vehicle, it's the standard rule.
            spec = new VehicleTypeSpecification(vehicle.getVehicleType());
        }
        return parkingRepository.findSlot(spec);
    }

    @Override
    public void park(ParkingLotFloorSlot slot, Vehicle vehicle) {
        parkingRepository.parkVehicle(slot, vehicle);
    }
} 
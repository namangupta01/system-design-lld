package services;

import enums.VehicleTypes;
import models.ParkingLotFloorSlot;
import models.Vehicle;
import repositories.IParkingRepository;
import services.specifications.VehicleTypeSpecification;

import java.util.Optional;

public class WeekdayParkingRule implements IParkingRule {
    private final IParkingRepository parkingRepository;

    public WeekdayParkingRule(IParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    @Override
    public Optional<ParkingLotFloorSlot> findSlot(Vehicle vehicle) {
        return parkingRepository.findSlot(new VehicleTypeSpecification(vehicle.getVehicleType()));
    }

    @Override
    public void park(ParkingLotFloorSlot slot, Vehicle vehicle) {
        parkingRepository.parkVehicle(slot, vehicle);
    }
} 
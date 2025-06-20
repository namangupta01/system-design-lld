package services;

import enums.VehicleTypes;
import models.ParkingLotFloorSlot;
import repositories.IParkingRepository;

public class WeekendParkService implements IParkService {
    private final IParkingRepository parkingRepository;

    public WeekendParkService(IParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    @Override
    public ParkingLotFloorSlot park(String vehicleNumber, VehicleTypes vehicleType) {
        return null;
    }
}

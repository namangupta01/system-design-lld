package services;

import exceptions.VehicleNotFoundException;
import models.ParkingLotFloorSlot;
import repositories.IParkingRepository;

import java.util.Optional;

public class SearchVehicleService implements ISearchVehicleService {

    private final IParkingRepository parkingRepository;

    public SearchVehicleService(IParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    @Override
    public void search(String vehicleNumber) {
        Optional<ParkingLotFloorSlot> optionalSlot = parkingRepository.findParkedVehicle(vehicleNumber);
        if (optionalSlot.isPresent()) {
            ParkingLotFloorSlot slot = optionalSlot.get();
            System.out.println("Vehicle " + vehicleNumber + " found in slot " + slot.getSlotNumber() + " on floor " + slot.getFloor().getFloorNumber() + ".");
        } else {
            throw new VehicleNotFoundException("Vehicle with number " + vehicleNumber + " not found.");
        }
    }
}

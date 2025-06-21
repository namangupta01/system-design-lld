package services;

import enums.SlotStatus;
import exceptions.VehicleNotFoundException;
import models.ParkingLotFloorSlot;
import repositories.IParkingRepository;

import java.util.Optional;

public class UnparkService implements IUnParkService {
    private final IParkingRepository parkingRepository;

    public UnparkService(IParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    @Override
    public ParkingLotFloorSlot unPark(String vehicleNumber) {
        Optional<ParkingLotFloorSlot> optionalSlot = parkingRepository.findParkedVehicle(vehicleNumber);
        if (optionalSlot.isEmpty()) {
            throw new VehicleNotFoundException("Vehicle with number " + vehicleNumber + " not found.");
        }

        ParkingLotFloorSlot slot = optionalSlot.get();
        synchronized (slot) {
            // Double-check: Ensure the vehicle is still parked here before un-parking.
            if (slot.getStatus() == SlotStatus.OCCUPIED && slot.getVehicle().getVehicleNumber().equals(vehicleNumber)) {
                parkingRepository.unParkVehicle(slot);
                System.out.println("Vehicle " + vehicleNumber + " un-parked from slot " + slot.getSlotNumber() + " on floor " + slot.getFloor().getFloorNumber() + ".");
            } else {
                // This could happen if another thread un-parked it in the meantime.
                throw new VehicleNotFoundException("Vehicle with number " + vehicleNumber + " was not found in the expected slot. It may have been un-parked by another process.");
            }
        }
        return slot;
    }
}

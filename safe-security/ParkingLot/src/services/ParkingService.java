package services;

import enums.SlotStatus;
import exceptions.ParkingLotFullException;
import models.ParkingLotFloorSlot;
import models.Vehicle;

import java.util.Optional;

public class ParkingService implements IParkService {

    private final IParkingRule parkingRule;

    public ParkingService(IParkingRule parkingRule) {
        this.parkingRule = parkingRule;
    }

    @Override
    public ParkingLotFloorSlot parkVehicle(Vehicle vehicle) {
        // For simplicity in this demo, we'll retry a few times if a slot is taken concurrently.
        for (int i = 0; i < 3; i++) {
            Optional<ParkingLotFloorSlot> optionalSlot = parkingRule.findSlot(vehicle);
            if (optionalSlot.isEmpty()) {
                throw new ParkingLotFullException("No available slot for vehicle type: " + vehicle.getVehicleType());
            }

            ParkingLotFloorSlot slot = optionalSlot.get();
            synchronized (slot) {
                // Double-check: The slot might have been taken while we were waiting for the lock.
                if (slot.getStatus() == SlotStatus.UN_OCCUPIED) {
                    parkingRule.park(slot, vehicle);
                    System.out.println("Vehicle " + vehicle.getVehicleNumber() + " parked in slot " + slot.getSlotNumber() + " on floor " + slot.getFloor().getFloorNumber() + ".");
                    return slot;
                }
                // If the slot is now occupied, the loop will continue and we'll try to find another one.
            }
        }
        throw new ParkingLotFullException("Could not park vehicle due to high contention. Please try again.");
    }
} 
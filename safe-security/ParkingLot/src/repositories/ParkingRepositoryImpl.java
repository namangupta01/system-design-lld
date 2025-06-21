package repositories;

import enums.SlotStatus;
import enums.VehicleTypes;
import models.ParkingLot;
import models.ParkingLotFloor;
import models.ParkingLotFloorSlot;
import models.Vehicle;
import models.SlotSpecification;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

public class ParkingRepositoryImpl implements IParkingRepository {
    private final ParkingLot parkingLot = ParkingLot.getInstance();

    @Override
    public ParkingLot save(int numFloors, int numSlotsPerFloor) {
        for (int i = 1; i <= numFloors; i++) {
            ParkingLotFloor floor = new ParkingLotFloor(i);
            AtomicInteger twoWheelerSlotCount = new AtomicInteger(0);
            for (int j = 1; j <= numSlotsPerFloor; j++) {
                VehicleTypes slotType = (twoWheelerSlotCount.getAndIncrement() < 2) ? VehicleTypes.TWO_WHEELER : VehicleTypes.FOUR_WHEELER;
                floor.addSlot(new ParkingLotFloorSlot(j, slotType, floor));
            }
            parkingLot.addFloor(floor);
        }
        return parkingLot;
    }

    @Override
    public Optional<ParkingLotFloorSlot> findSlot(SlotSpecification spec) {
        for (ParkingLotFloor floor : parkingLot.getParkingLotFloors()) {
            for (ParkingLotFloorSlot slot : floor.getParkingLotFloorSlotList()) {
                if (spec.isSatisfiedBy(slot)) {
                    return Optional.of(slot);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public void parkVehicle(ParkingLotFloorSlot slot, Vehicle vehicle) {
        slot.setVehicle(vehicle);
        slot.setStatus(SlotStatus.OCCUPIED);
    }

    @Override
    public Optional<ParkingLotFloorSlot> findParkedVehicle(String vehicleNumber) {
        for (ParkingLotFloor floor : parkingLot.getParkingLotFloors()) {
            for (ParkingLotFloorSlot slot : floor.getParkingLotFloorSlotList()) {
                if (slot.getStatus() == SlotStatus.OCCUPIED && slot.getVehicle().getVehicleNumber().equals(vehicleNumber)) {
                    return Optional.of(slot);
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public void unParkVehicle(ParkingLotFloorSlot slot) {
        slot.setVehicle(null);
        slot.setStatus(SlotStatus.UN_OCCUPIED);
    }
}

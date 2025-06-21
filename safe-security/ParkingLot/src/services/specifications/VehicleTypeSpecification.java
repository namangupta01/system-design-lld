package services.specifications;

import enums.SlotStatus;
import enums.VehicleTypes;
import models.ParkingLotFloorSlot;

public class VehicleTypeSpecification implements SlotSpecification {
    private final VehicleTypes vehicleType;

    public VehicleTypeSpecification(VehicleTypes vehicleType) {
        this.vehicleType = vehicleType;
    }

    @Override
    public boolean isSatisfiedBy(ParkingLotFloorSlot slot) {
        return slot.getStatus() == SlotStatus.UN_OCCUPIED && slot.getVehicleTypes() == vehicleType;
    }
} 
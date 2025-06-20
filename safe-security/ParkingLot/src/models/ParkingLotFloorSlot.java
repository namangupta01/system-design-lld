package models;

import enums.SlotStatus;
import enums.VehicleTypes;

public class ParkingLotFloorSlot {
    private final Integer slotNumber;
    private VehicleTypes vehicleTypes;
    private SlotStatus status;
    private Vehicle vehicle;

    public ParkingLotFloorSlot(Integer slotNumber, VehicleTypes vehicleType) {
        this.slotNumber = slotNumber;
        this.vehicleTypes = vehicleType;
        this.status = SlotStatus.UN_OCCUPIED;
    }

    public Integer getSlotNumber() {
        return slotNumber;
    }

    public SlotStatus getStatus() {
        return status;
    }

    public Vehicle getVehicle() {
        return this.vehicle;
    }

    public VehicleTypes getVehicleTypes() {
        return vehicleTypes;
    }

    public void setStatus(SlotStatus status) {
        this.status = status;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}

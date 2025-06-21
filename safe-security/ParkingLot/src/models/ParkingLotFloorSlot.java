package models;

import enums.SlotStatus;
import enums.VehicleTypes;

public class ParkingLotFloorSlot {
    private final Integer slotNumber;
    private final VehicleTypes vehicleTypes;
    private final ParkingLotFloor floor;
    private SlotStatus status;
    private Vehicle vehicle;

    public ParkingLotFloorSlot(Integer slotNumber, VehicleTypes vehicleType, ParkingLotFloor floor) {
        this.slotNumber = slotNumber;
        this.vehicleTypes = vehicleType;
        this.status = SlotStatus.UN_OCCUPIED;
        this.floor = floor;
    }

    public Integer getSlotNumber() {
        return slotNumber;
    }

    public SlotStatus getStatus() {
        return status;
    }

    public void setStatus(SlotStatus status) {
        this.status = status;
    }

    public Vehicle getVehicle() {
        return this.vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public VehicleTypes getVehicleTypes() {
        return vehicleTypes;
    }

    public ParkingLotFloor getFloor() {
        return floor;
    }
}

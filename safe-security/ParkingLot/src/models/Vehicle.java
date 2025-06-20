package models;

import enums.VehicleTypes;

public class Vehicle {
    private String vehicleNumber;
    private VehicleTypes vehicleType;

    public Vehicle(String vehicleNumber, VehicleTypes vehicleType) {
        this.vehicleNumber = vehicleNumber;
        this.vehicleType = vehicleType;
    }

    public VehicleTypes getVehicleType() {
        return vehicleType;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }
}

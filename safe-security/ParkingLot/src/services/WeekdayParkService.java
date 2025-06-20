package services;

import enums.SlotStatus;
import enums.VehicleTypes;
import models.ParkingLot;
import models.ParkingLotFloor;
import models.ParkingLotFloorSlot;
import models.Vehicle;
import repositories.IParkingRepository;

import java.util.List;

public class WeekdayParkService implements IParkService {

    private final IParkingRepository parkingRepository;

    public WeekdayParkService(IParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    @Override
    public ParkingLotFloorSlot park(String vehicleNumber, VehicleTypes vehicleType) {
        ParkingLotFloorSlot finalSlot = null;
        ParkingLot parkingLot = parkingRepository.get();
        List<ParkingLotFloor> parkingLotFloorList = parkingLot.getParkingLotFloors();
        for(int i=0; i<parkingLotFloorList.size(); i++) {
            ParkingLotFloor parkingLotFloor = parkingLotFloorList.get(i);
            List<ParkingLotFloorSlot> parkingLotFloorSlotList = parkingLotFloor.getParkingLotFloorSlotList();
            for(int j=0; j<parkingLotFloorSlotList.size(); j++) {
                ParkingLotFloorSlot parkingLotFloorSlot = parkingLotFloorSlotList.get(i);
                if(parkingLotFloorSlot.getVehicleTypes().equals(vehicleType) && parkingLotFloorSlot.getStatus().equals(SlotStatus.UN_OCCUPIED)) {
                    Vehicle vehicle = new Vehicle(vehicleNumber, vehicleType);
                    parkingLotFloorSlot.setStatus(SlotStatus.OCCUPIED);
                    parkingLotFloorSlot.setVehicle(vehicle);
                    finalSlot = parkingLotFloorSlot;
                    System.out.println("Vehicle Parked on " + parkingLotFloorSlot.getSlotNumber() + " on floor " + parkingLotFloor.getFloorNumber());
                    return finalSlot;
                }
            }
        }
        System.out.println("No slot available to park this vehicle");
        return finalSlot;
    }
}

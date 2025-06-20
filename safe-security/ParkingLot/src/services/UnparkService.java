package services;

import enums.SlotStatus;
import models.ParkingLot;
import models.ParkingLotFloor;
import models.ParkingLotFloorSlot;
import repositories.IParkingRepository;

import java.util.List;

public class UnparkService implements IUnParkService {

    private final IParkingRepository parkingRepository;

    public UnparkService(IParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    public ParkingLotFloorSlot unPark(String vehicleNumber) {
        ParkingLot parkingLot = parkingRepository.get();
        ParkingLotFloorSlot finalSlot = null;
        List<ParkingLotFloor> parkingLotFloorList = parkingLot.getParkingLotFloors();
        for(int i=0; i<parkingLotFloorList.size(); i++) {
            ParkingLotFloor parkingLotFloor = parkingLotFloorList.get(i);
            List<ParkingLotFloorSlot> parkingLotFloorSlotList = parkingLotFloor.getParkingLotFloorSlotList();
            for(int j=0; j<parkingLotFloorSlotList.size(); j++) {
                ParkingLotFloorSlot parkingLotFloorSlot = parkingLotFloorSlotList.get(i);
                if(parkingLotFloorSlot.getStatus().equals(SlotStatus.OCCUPIED) && parkingLotFloorSlot.getVehicle().getVehicleNumber().equals(vehicleNumber)) {
                    parkingLotFloorSlot.setStatus(SlotStatus.UN_OCCUPIED);
                    parkingLotFloorSlot.setVehicle(null);
                    finalSlot = parkingLotFloorSlot;
                    System.out.println("Vehicle UnParked on " + parkingLotFloorSlot.getSlotNumber() + " on floor " + parkingLotFloor.getFloorNumber());
                    return finalSlot;
                }
            }
        }
        System.out.println("No vehicle of this number is parked");
        return finalSlot;
    }

}

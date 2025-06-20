package services;

import enums.VehicleTypes;
import models.ParkingLot;
import models.ParkingLotFloor;
import models.ParkingLotFloorSlot;
import repositories.IParkingRepository;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotCreationService implements IParkingLotCreationService {
    private final IParkingRepository parkingRepository;

    public ParkingLotCreationService(IParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    @Override
    public ParkingLot createParkingLot(Integer numberOfFloors, Integer numberOfFourWheelerSlotsInEachFloor, Integer numberOfTwoWheelerSlotsInEachFloor) {
        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLotFloor > parkingLotFloorsList = new ArrayList<>();
        for(int i=0; i<numberOfFloors; i++) {
            ParkingLotFloor parkingLotFloor = new ParkingLotFloor(i);
            List<ParkingLotFloorSlot> parkingLotFloorSlotList = new ArrayList<>();
            for(int j=0; j<numberOfFourWheelerSlotsInEachFloor; j++) {
                ParkingLotFloorSlot parkingLotFloorSlot = new ParkingLotFloorSlot(j, VehicleTypes.FOUR_WHEELER);
                parkingLotFloorSlotList.add(parkingLotFloorSlot);
            }

            for(int j=0; j<numberOfTwoWheelerSlotsInEachFloor; j++) {
                ParkingLotFloorSlot parkingLotFloorSlot = new ParkingLotFloorSlot(numberOfFourWheelerSlotsInEachFloor+j, VehicleTypes.TWO_WHEELER);
                parkingLotFloorSlotList.add(parkingLotFloorSlot);
            }
            parkingLotFloor.setParkingLotFloorSlotList(parkingLotFloorSlotList);;
            parkingLotFloorsList.add(parkingLotFloor);
        }
        parkingLot.setParkingLotFloors(parkingLotFloorsList);
        parkingRepository.save(parkingLot);
        return parkingLot;
    }
}

package services;

import models.ParkingLot;

public interface IParkingLotCreationService {
    ParkingLot createParkingLot(Integer numberOfFloors, Integer numberOfFourWheelerSlotsInEachFloor, Integer numberOfTwoWheelerSlotsInEachFloor);
}

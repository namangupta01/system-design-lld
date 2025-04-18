package service.strategy;

import models.ParkingLot;
import models.Slot;

public interface ParkingStrategy {

    public Integer getFreeSlot();

    public void reserveSlot(Integer slot);

    public void freeSlot(Integer slot);

    public void createParkingLot(Integer capacity);

}

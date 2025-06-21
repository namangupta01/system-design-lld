package services.specifications;

import models.ParkingLotFloorSlot;

public interface SlotSpecification {
    boolean isSatisfiedBy(ParkingLotFloorSlot slot);
} 
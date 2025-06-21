package services.specifications;

import models.ParkingLotFloorSlot;

public class OrSpecification implements SlotSpecification {
    private final SlotSpecification first;
    private final SlotSpecification second;

    public OrSpecification(SlotSpecification first, SlotSpecification second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean isSatisfiedBy(ParkingLotFloorSlot slot) {
        return first.isSatisfiedBy(slot) || second.isSatisfiedBy(slot);
    }
} 
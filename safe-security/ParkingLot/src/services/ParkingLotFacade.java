package services;

import models.ParkingLotFloorSlot;
import models.Vehicle;
import repositories.IParkingRepository;
import repositories.ParkingRepositoryImpl;

import java.util.Calendar;

public class ParkingLotFacade {
    private final IParkService parkingService;
    private final IUnParkService unparkService;
    private final ISearchVehicleService searchVehicleService;

    public ParkingLotFacade(int numFloors, int numSlotsPerFloor) {
        IParkingRepository parkingRepository = new ParkingRepositoryImpl();
        parkingRepository.save(numFloors, numSlotsPerFloor);

        IParkingRule parkingRule = getParkingRuleForDay(parkingRepository);
        this.parkingService = new ParkingService(parkingRule);
        this.unparkService = new UnparkService(parkingRepository);
        this.searchVehicleService = new SearchVehicleService(parkingRepository);
    }

    public ParkingLotFloorSlot parkVehicle(Vehicle vehicle) {
        return parkingService.parkVehicle(vehicle);
    }

    public void unparkVehicle(String vehicleNumber) {
        unparkService.unPark(vehicleNumber);
    }

    public void searchVehicle(String vehicleNumber) {
        searchVehicleService.search(vehicleNumber);
    }

    private IParkingRule getParkingRuleForDay(IParkingRepository parkingRepository) {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        if (dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {
            return new WeekendParkingRule(parkingRepository);
        }
        return new WeekdayParkingRule(parkingRepository);
    }
} 
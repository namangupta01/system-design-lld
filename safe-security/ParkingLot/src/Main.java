import enums.VehicleTypes;
import repositories.IParkingRepository;
import repositories.ParkingRepositoryImpl;
import services.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        IParkingRepository parkingRepository = new ParkingRepositoryImpl();

        IParkService parkService = new WeekdayParkService(parkingRepository);
//        IParkService weekendParkService = new WeekendParkService(parkingRepository);
        IUnParkService unParkService = new UnparkService(parkingRepository);
//        ISearchVehicleService searchVehicleService = new SearchVehicleService();
        
        ParkingLotCreationService parkingLotCreationService = new ParkingLotCreationService(parkingRepository);
        parkingLotCreationService.createParkingLot(3, 10, 5);

        parkService.park("tempVehicleNumber", VehicleTypes.FOUR_WHEELER);
        unParkService.unPark("tempVehicleNumber1");
        unParkService.unPark("tempVehicleNumber");

    }
}
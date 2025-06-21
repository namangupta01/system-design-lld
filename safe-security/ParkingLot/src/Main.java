import enums.VehicleTypes;
import exceptions.ParkingLotFullException;
import exceptions.VehicleNotFoundException;
import models.Vehicle;
import services.ParkingLotFacade;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("--- Setting up Parking Lot ---");
        // The facade handles all complex setup internally.
        ParkingLotFacade parkingLotFacade = new ParkingLotFacade(2, 5);
        System.out.println("--- Parking Lot Setup Complete ---\n");

        // --- Sequential Demo ---
        System.out.println("--- Running Sequential Demo ---");
        try {
            Vehicle car1 = new Vehicle("CAR-001", VehicleTypes.FOUR_WHEELER);
            parkingLotFacade.parkVehicle(car1);

            Vehicle bike1 = new Vehicle("BIKE-001", VehicleTypes.TWO_WHEELER);
            parkingLotFacade.parkVehicle(bike1);

            parkingLotFacade.searchVehicle("CAR-001");
            parkingLotFacade.unparkVehicle("CAR-001");
            parkingLotFacade.searchVehicle("CAR-001"); // Should fail
        } catch (ParkingLotFullException | VehicleNotFoundException e) {
            System.err.println("Error during sequential demo: " + e.getMessage());
        }
        System.out.println("--- Sequential Demo Complete ---\n");


        // --- Concurrent Demo ---
        System.out.println("--- Running Concurrent Demo ---");
        // This will simulate many vehicles trying to park at the same time.
        // Our thread-safe design will handle this without race conditions.
        ExecutorService executor = Executors.newFixedThreadPool(10);
        List<Vehicle> vehicles = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            vehicles.add(new Vehicle("CONCURRENT-CAR-" + i, VehicleTypes.FOUR_WHEELER));
        }

        for (Vehicle vehicle : vehicles) {
            executor.submit(() -> {
                try {
                    System.out.println("Thread " + Thread.currentThread().getId() + ": Attempting to park " + vehicle.getVehicleNumber());
                    parkingLotFacade.parkVehicle(vehicle);
                } catch (ParkingLotFullException e) {
                    System.err.println("Thread " + Thread.currentThread().getId() + ": " + e.getMessage() + " for vehicle " + vehicle.getVehicleNumber());
                }
            });
        }

        // Shutdown the executor
        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
        System.out.println("--- Concurrent Demo Complete ---");
    }
}
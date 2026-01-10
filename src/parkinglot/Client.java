package parkinglot;

import parkinglot.entities.*;
import parkinglot.enums.VehicleSize;
import parkinglot.strategies.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Client {
    public static void main(String[] args) throws InterruptedException {
        ParkingLot parkingLot = setupParkingLot();
        testBasicFlow(parkingLot);
        testLotFull(parkingLot);
       // testSizeMismatch(parkingLot);
        testDoubleUnpark(parkingLot);
        testLowerFloorFirst(parkingLot);
        parkingLot.setParkingStrategy(new UpperFloorFirstStrategy());
        testUpperFloorFirst(parkingLot);
        Map<VehicleSize,Double> rateMap = new HashMap<>();
        rateMap.put(VehicleSize.SMALL,5.0);
        rateMap.put(VehicleSize.MEDIUM,10.0);
        rateMap.put(VehicleSize.LARGE,15.0);
        parkingLot.setFeeStrategy(new VariableRateFeeStrategy(rateMap));
        testVariableFeeStrategy(parkingLot);


    }

    public static ParkingLot setupParkingLot() {
        // Create floors
        ParkingFloor floor0 = new ParkingFloor(0);
        ParkingFloor floor1 = new ParkingFloor(1);
        ParkingFloor floor3 = new ParkingFloor(3);
        ParkingFloor floor2 = new ParkingFloor(2);
        // Add spots
        floor0.addParkingSpot(new ParkingSpot("F0-S1", VehicleSize.SMALL));
        floor0.addParkingSpot(new ParkingSpot("F0-S2", VehicleSize.MEDIUM));

        floor1.addParkingSpot(new ParkingSpot("F1-S1", VehicleSize.SMALL));
        floor1.addParkingSpot(new ParkingSpot("F1-S2", VehicleSize.MEDIUM));

        floor2.addParkingSpot(new ParkingSpot("F1-S1", VehicleSize.LARGE));
        floor2.addParkingSpot(new ParkingSpot("F1-S2", VehicleSize.LARGE));

        floor3.addParkingSpot(new ParkingSpot("F1-S1", VehicleSize.SMALL));
        floor3.addParkingSpot(new ParkingSpot("F1-S2", VehicleSize.MEDIUM));


        // Strategy
        ParkingStrategy parkingStrategy = new LowerFloorFirstStrategy();

        // Fee Strategy
        FeeStrategy feeStrategy = new FixedHourlyFeeStrategy(5.7);

        // ParkingLot
        ParkingLot parkingLot = ParkingLot.getInstance();
        parkingLot.addParkingFloor(floor0);
        parkingLot.addParkingFloor(floor1);
        parkingLot.addParkingFloor(floor2);
        parkingLot.addParkingFloor(floor3);

        parkingLot.setParkingStrategy(parkingStrategy);
        parkingLot.setFeeStrategy(feeStrategy);

        return parkingLot;
    }

    public static void testVariableFeeStrategy(ParkingLot parkingLot) throws InterruptedException
    {
        System.out.println("\n--- Test 7: Variable Free ---");

        Vehicle car = new Car("KA-02");

        Optional<ParkingTicket> ticketOpt = parkingLot.parkVehicle(car);

        if (ticketOpt.isEmpty()) {
            System.out.println("FAIL: Could not park vehicle");
            return;
        }

        ParkingTicket ticket = ticketOpt.get();
        System.out.println("Parked. Ticket ID = " + ticket.getTicketId());

        Thread.sleep(2000);

        double fee = parkingLot.unparkVehicle(ticket.getTicketId());
        System.out.println("Unparked. Fee = " + fee);
    }
    public static void testBasicFlow(ParkingLot parkingLot) throws InterruptedException {
        System.out.println("\n--- Test 1: Basic Park & Unpark ---");

        Vehicle car = new Car("KA-01");

        Optional<ParkingTicket> ticketOpt = parkingLot.parkVehicle(car);

        if (ticketOpt.isEmpty()) {
            System.out.println("FAIL: Could not park vehicle");
            return;
        }

        ParkingTicket ticket = ticketOpt.get();
        System.out.println("Parked. Ticket ID = " + ticket.getTicketId());

        Thread.sleep(2000);

        double fee = parkingLot.unparkVehicle(ticket.getTicketId());
        System.out.println("Unparked. Fee = " + fee);
    }

    public static void testLotFull(ParkingLot parkingLot) {
        System.out.println("\n--- Test 2: Lot Full ---");

        Vehicle v1 = new Car("A");
        Vehicle v2 = new Car("B");

        parkingLot.parkVehicle(v1);

        Optional<ParkingTicket> t2 = parkingLot.parkVehicle(v2);

        if (t2.isEmpty()) {
            System.out.println("PASS: Lot full handled correctly");
        } else {
            System.out.println("FAIL: Lot full not handled");
        }
    }

    public static void testSizeMismatch(ParkingLot parkingLot) {
        System.out.println("\n--- Test 3: Size Mismatch ---");

        Vehicle truck = new Truck("TR-01");
        Optional<ParkingTicket> ticketOpt = parkingLot.parkVehicle(truck);

        if (ticketOpt.isEmpty()) {
            System.out.println("PASS: Size mismatch handled");
        } else {
            System.out.println("FAIL: Size mismatch ignored");
        }
    }

    public static void testDoubleUnpark(ParkingLot parkingLot) {
        System.out.println("\n--- Test 4: Double Unpark ---");

        Vehicle bike = new Bike("X1");
        ParkingTicket ticket = parkingLot.parkVehicle(bike).get();

        parkingLot.unparkVehicle(ticket.getTicketId());

        try {
            parkingLot.unparkVehicle(ticket.getTicketId());
            System.out.println("FAIL: Double unpark allowed");
        } catch (Exception e) {
            System.out.println("PASS: Double unpark blocked");
        }
    }

    public static void testLowerFloorFirst(ParkingLot parkingLot) {
        System.out.println("\n--- Test 5: Lower Floor First ---");

        Vehicle truck = new Truck("Truck 01");
        ParkingTicket ticket = parkingLot.parkVehicle(truck).get();
        System.out.println(ticket.getTicketId());

    }
    public static void testUpperFloorFirst(ParkingLot parkingLot) {
        System.out.println("\n--- Test 6: Upper Floor First ---");

        Vehicle truck = new Truck("Truck 02");
        ParkingTicket ticket = parkingLot.parkVehicle(truck).get();

    }





}

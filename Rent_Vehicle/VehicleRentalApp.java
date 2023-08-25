package Rent_Vehicle;

import Rent_Vehicle.data.BranchRepository;
import Rent_Vehicle.data.CalendarRepository;
import Rent_Vehicle.data.CityRepository;
import Rent_Vehicle.exceptions.BranchNotExistsException;
import Rent_Vehicle.exceptions.InvalidVehicleException;
import Rent_Vehicle.exceptions.SlotNotAvailableException;
import Rent_Vehicle.exceptions.VehicleNotFoundException;
import Rent_Vehicle.model.Branch;
import Rent_Vehicle.model.VechicleType;
import Rent_Vehicle.model.Vehicle;
import Rent_Vehicle.service.VehicleBookingService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static Rent_Vehicle.model.VechicleType.New;

public class VehicleRentalApp {
    private static final String city = "‘koramangala’";
    private static void onBoarding(){
        Branch branch = new Branch("Branch1");
        CityRepository.getInstance().addBranch(city,branch);

        Vehicle vehicle1 = new Vehicle("ABCD123",VechicleType.HATCHBACK, 100);
        Vehicle vehicle2 = new Vehicle("ABCD123",VechicleType.SEDAN, 200);
        Vehicle vehicle3 = new Vehicle("ABCD123",VechicleType.SUV, 300);
        Vehicle vehicle4 = new Vehicle("ABCD123",VechicleType.HATCHBACK, 400);

        BranchRepository instance = BranchRepository.getInstance();
        CalendarRepository calendarInstance = CalendarRepository.getInstance();

        instance.addVehicleToBranch(branch, vehicle1);
        calendarInstance.addVehicle(vehicle1);

        instance.addVehicleToBranch(branch, vehicle2);
        calendarInstance.addVehicle(vehicle2);

        instance.addVehicleToBranch(branch, vehicle3);
        calendarInstance.addVehicle(vehicle3);

        instance.addVehicleToBranch(branch, vehicle4);
        calendarInstance.addVehicle(vehicle4);
    }

    private static Branch selectBranch() throws BranchNotExistsException {
        Scanner sc = new Scanner(System.in);

        List<Branch> branchList = VehicleBookingService.getInstance().getAllBranchesofCity(city);
        if(branchList == null)
            throw new BranchNotExistsException("Vehicle not present");
        System.out.println(branchList);
        System.out.println("Select a branch");
        Integer option = sc.nextInt();

        return branchList.get(option);
    }

    private static void addNewVehicle() throws BranchNotExistsException {
        Scanner sc = new Scanner(System.in);

        Branch branch = selectBranch();

        System.out.println("Enter vehicle Details\n" +
                "1.licence No\n" +
                "2.Vehicle Type\n" +
                "3.Vehicle price");
        String licenceNo = sc.nextLine();
        VechicleType vechicleType = VechicleType.getType(sc.nextLine());
        int price = sc.nextInt();
        Vehicle vehicle = new Vehicle(licenceNo, vechicleType, price);
        BranchRepository.getInstance().addVehicleToBranch( branch, vehicle);
        CalendarRepository.getInstance().addVehicle(vehicle);
    }

    private static void displayVehicle() throws VehicleNotFoundException, BranchNotExistsException {
        Branch branch = selectBranch();
        if(VehicleBookingService.getInstance().getAllVehicleOfBranch(branch) == null)
            throw new VehicleNotFoundException("Vehicle not present");
        System.out.println(VehicleBookingService.getInstance().getAllVehicleOfBranch(branch));
    }

    private static void bookVehicle() throws BranchNotExistsException, InvalidVehicleException, SlotNotAvailableException {
        Scanner sc = new Scanner(System.in);

        Branch branch = selectBranch();
        System.out.println("Get Slot number");
        int slotNo = sc.nextInt(); sc.nextLine();
        System.out.println("Get Vehicle Type");
        VechicleType vechicleType = VechicleType.getType(sc.nextLine());
        if(vechicleType.equals(New))
                throw new InvalidVehicleException("Vehicle Type not present");
        Optional<Vehicle> vehicle = VehicleBookingService.getInstance().bookSlot(branch, vechicleType, slotNo);
        if(vehicle.isPresent())
            System.out.println("Vehicle booked "+ vehicle.get());
        else {
            System.out.println("Vehicle Not available");
            throw new SlotNotAvailableException("Slot not available");
        }
    }

    public static void main(String[] args) throws VehicleNotFoundException, BranchNotExistsException, InvalidVehicleException, SlotNotAvailableException {
        onBoarding();
        Scanner sc = new Scanner(System.in);
        int option = 0;
        while(option != 4){
            System.out.println("Menu\n" +
                    "1.Onboard New Vehicle\n" +
                    "2.Display All Vehicles\n" +
                    "3.Rent a Vehicle\n" +
                    "4.Exit");
            option = sc.nextInt();
            switch(option){
                case 1 :
                    addNewVehicle();
                    break;
                case 2:
                    displayVehicle();
                    break;
                case 3:
                    bookVehicle();
                    break;
            }
        }
        System.out.println("End of App");
    }
}
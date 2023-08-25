package Rent_Vehicle.data;

import Rent_Vehicle.model.Branch;
import Rent_Vehicle.model.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BranchRepository {
    private Map<Branch, List<Vehicle>> vehicleMap;
    private static BranchRepository branch;

    private BranchRepository(){
        vehicleMap = new HashMap<>();
    }

    public static BranchRepository getInstance(){
        if(branch == null) branch = new BranchRepository();
        return branch;
    }

    public void addVehicleToBranch(Branch branch, Vehicle vehicle){
        vehicleMap.putIfAbsent(branch, new ArrayList<>());
        vehicleMap.get(branch).add(vehicle);
    }

    public List<Vehicle> getVehiclesOfBranch(Branch branch){
        return vehicleMap.getOrDefault(branch, new ArrayList<>());
    }
}

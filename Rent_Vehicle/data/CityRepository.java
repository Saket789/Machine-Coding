package Rent_Vehicle.data;

import Rent_Vehicle.model.Branch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CityRepository {
    private static Map<String, List<Branch>> branchMap;
    private static CityRepository instance;

    private CityRepository(){
        branchMap = new HashMap<>();
    }

    public static CityRepository getInstance(){
        if(instance == null) instance = new CityRepository();
        return instance;
    }

    public void addBranch(String city, Branch branch){
        branchMap.putIfAbsent(city, new ArrayList<>());
        branchMap.get(city).add(branch);
    }

    public List<Branch> getBranchesOfCity(String city){
        return branchMap.get(city);
    }
}


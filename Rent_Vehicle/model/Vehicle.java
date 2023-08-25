package Rent_Vehicle.model;

public class Vehicle {
    private String id;
    private VechicleType vechicleType;
    private int rentalPrice;

    @Override
    public String toString(){
        return id + " " +vechicleType + " " + rentalPrice;
    }

    public Vehicle(String id, VechicleType vechicleType, Integer price){
        this.id = id;
        this.vechicleType = vechicleType;
        this.rentalPrice = price;
    }

    public int getRentalPrice() {
        return rentalPrice;
    }

    public VechicleType getVechicleType() {
        return vechicleType;
    }

    public String getId() {
        return id;
    }

}

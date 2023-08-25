package Rent_Vehicle.model;

public enum VechicleType {
    SUV("suv"),
    SEDAN("sedan"),
    HATCHBACK("hatchback"),
    New("new");

    private String type;

    private VechicleType(String type){
        this.type = type;
    }

    public String getType(){
        return this.type;
    }

    public static VechicleType getType(String type){
        if(SUV.getType().equals(type)) return SUV;
        if(SEDAN.getType().equals(type)) return SEDAN;
        if(HATCHBACK.getType().equals(type)) return HATCHBACK;
        return New;
    }
}

package Rent_Vehicle.model;

public class Branch {
    private String name;

    public Branch(String name){
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }

}

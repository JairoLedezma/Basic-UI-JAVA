package sample;

import java.util.Random;


public class Computers extends Assets implements miaintenance, ipconfg{
    double gigahertz;

    Computers(String id, String AName,String AType, String ALocation, String UseB, String AState){
        super(id, AName, AType, ALocation,UseB,AState);
    }

    public double getgigahertz(){// special function
        Random rand = new Random();
        gigahertz = rand.nextDouble();
        return gigahertz;
    }

    public String togigahertz(){ // gets the gigahertzt the computer runs at  ( special function for computers only)
        return (getgigahertz() + "gigahertz");
    }

    @Override
    public void getmaintenance(){ // prints out the maintenance info
        System.out.println("There needs to be security upgrades");
    }

    @Override
    public void getconfig() { // prints out the ip config
        System.out.println("The computers need to get an ip dynamically if its a laptop");
    }
}

package sample;

import java.util.Random;

public class printer extends Assets implements miaintenance, ipconfg{
    int printSpeed;

    printer(String id, String AName, String AType, String ALocation, String UseB, String AState) {
        super(id, AName, AType, ALocation, UseB, AState);
    }

    public double getPrinterSpeed(){
        Random rand = new Random();
        printSpeed = rand.nextInt(1000);
        return printSpeed;
    }

    public String toPrinterSpeed(){
        return (getPrinterSpeed() + " pages per minute");
    }

    @Override
    public void getmaintenance() { // prints out the maintennace
        System.out.println("Printers need driver software to be updated and needs cleaning, cartridges replacements");
    }

    @Override
    public void getconfig() { // prints out the ip config info
        System.out.println("The Asset needs to get its ip from a static pool of IP's");
    }
}

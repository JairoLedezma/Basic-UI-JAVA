package sample;

import java.util.Random;

public class AudioVideo extends Assets{

    double frequency;
    AudioVideo(String id, String AName, String AType, String ALocation, String UseB, String AState) {
        super(id, AName, AType, ALocation, UseB, AState);
    }

    public double getSoundFrequency(){ // special fucntio
        Random rand = new Random();
        frequency =rand.nextDouble();
        return frequency;
    }

    public String toSoundFrequency(){
        return (getSoundFrequency() + " is the frequency of this device");
    }



}

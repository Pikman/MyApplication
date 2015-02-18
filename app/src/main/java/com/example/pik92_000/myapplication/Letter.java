package com.example.pik92_000.myapplication;

/**
 * Created by pik92_000 on 18.02.2015.
 */
public class Letter {
    public String Receiver, Theme;
    public Integer OutNum, InpNum;
    public Double Lat, Lng;

    public String toString() {
        return Receiver+" "+Theme;
    }
}



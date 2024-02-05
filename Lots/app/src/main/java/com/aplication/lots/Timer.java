package com.aplication.lots;

public final class Timer {

    private Timer(){}


    public static void delay(double seconds ){

        double miliseconds = System.currentTimeMillis();
        double expMillis = miliseconds + (seconds * 1000);

        while (true){


            miliseconds = System.currentTimeMillis();

            if (miliseconds >= expMillis )
                break;

        }

    }
}

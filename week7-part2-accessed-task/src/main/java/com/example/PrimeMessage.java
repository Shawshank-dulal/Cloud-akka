package com.example;

public class PrimeMessage {
    public final String number;
    public final boolean isPrime;

    public PrimeMessage(String number, boolean isPrime){
        this.number=number;
        this.isPrime=isPrime;
    }

    public String getNumber() {
        return number;
    }

    public boolean isPrime() {
        return isPrime;
    }
}

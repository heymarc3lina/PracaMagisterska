package com.agata.pietrzycka.dto;



public record NumbersToSend(int serviceANumber, int serviceBNumber) {
    public NumbersToSend( int serviceANumber, int serviceBNumber){
        this.serviceANumber = serviceANumber;
        this.serviceBNumber = serviceBNumber;
    }
}
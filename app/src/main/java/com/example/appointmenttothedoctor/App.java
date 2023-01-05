package com.example.appointmenttothedoctor;

public class App {
    String specialization;
    String specialist;
    String date;
    String doctor;



    public App(String specialization, String specialist, String date, String doctor){
  this.specialization = specialization;
  this.specialist = specialist;
  this.doctor = doctor;
  this.date = date;
    }

    public String getSpecialization(){
        return specialization;
    }
    public String getSpecialist(){
        return specialist;
    }
    public String getDoctor(){
        return date;
    }
    public String getDate(){
        return doctor;
    }

    public void setData(String specialization, String specialist, String doctor, String date){
        this.specialization = specialization;
        this.specialist = specialist;
        this.doctor = doctor;
        this.date = date;
    }
}

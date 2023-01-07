package com.example.appointmenttothedoctor;

public class App {
    String specialization;
    String specialist;
    String date;
    String service;
    String patientName;
    String patientId;

    public App() {};

    public App(String specialization, String specialist, String date, String service, String patientName, String patientId){
  this.specialization = specialization;
  this.specialist = specialist;
  this.service = service;
  this.date = date;
  this.patientName = patientName;
  this.patientId = patientId;
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
        return service;
    }
    public String getPatientName() {return patientName;}

    public void setData(String specialization, String specialist, String service, String date, String patientName, String patientId){
        this.specialization = specialization;
        this.specialist = specialist;
        this.service = service;
        this.date = date;
        this.patientName = patientName;
        this.patientId = patientId;
    }
}

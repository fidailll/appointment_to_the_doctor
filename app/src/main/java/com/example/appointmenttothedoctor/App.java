package com.example.appointmenttothedoctor;

public class App {
    private String specializationApp;
    private String specialistApp;
    private String dateApp;
    private String serviceApp;
    private String patientName;
    private String patientId;

    public App() {};

    public App(String specializationApp, String specialistApp, String dateApp, String serviceApp, String patientName, String patientId){
        this.dateApp = dateApp;
        this.patientId = patientId;
        this.patientName = patientName;
        this.serviceApp = serviceApp;
        this.specialistApp = specialistApp;
        this.specializationApp = specializationApp;
    }

    public String getSpecializationApp(){
        return specializationApp;
    }
    public String getSpecialistApp(){return specialistApp;}
    public String getDateApp(){
        return dateApp;
    }
    public String getServiceApp(){
        return serviceApp;
    }
    public String getPatientName() {return patientName;}
    public String getPatientId() {return patientId;}


    public void setData(String specializationApp, String specialistApp, String serviceApp, String dateApp, String patientName, String patientId){
        this.specializationApp = specializationApp;
        this.specialistApp = specialistApp;
        this.serviceApp = serviceApp;
        this.dateApp = dateApp;
        this.patientName = patientName;
        this.patientId = patientId;
    }
}

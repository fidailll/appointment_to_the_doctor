package com.example.appointmenttothedoctor;

public class AwesomeSpecialization {
    String service;


    public AwesomeSpecialization(){}

   public AwesomeSpecialization(String specialization){
       this.service = specialization;
   }

   public void setService(String service) {
       this.service = service;
   }

    public String getService(){
       return service;
    }

}

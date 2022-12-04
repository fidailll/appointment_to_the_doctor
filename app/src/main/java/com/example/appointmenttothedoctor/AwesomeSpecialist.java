package com.example.appointmenttothedoctor;

public class AwesomeSpecialist {
    String name;
    String experience;
    String image;

    public AwesomeSpecialist(){
    }

    public AwesomeSpecialist(String name, String experience, String image){
        this.name = name;
        this.experience = experience;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}

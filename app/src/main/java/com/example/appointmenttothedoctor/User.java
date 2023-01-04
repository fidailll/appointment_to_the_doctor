package com.example.appointmenttothedoctor;

import java.util.Date;

public class User {

    private String surname;
    private String name;
    private String patronymic;
    private String email;
    private String phone;
    private Date birthday;
    private String id;

    public User(){

    }

    public User(String surname, String name, String patronymic, String email, String phone, Date birthday, String id){
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.email = email;
        this.phone = phone;
        this.birthday = birthday;
        this.id = id;

    }
    public String getSurname(){
        return surname;
    }

    public void setSurname(String surname){
        this.surname = surname;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getPatronymic(){
        return patronymic;
    }

    public void setPatronymic(String patronymic){
        this.patronymic = patronymic;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone(){
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthday(){
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getId(){
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}


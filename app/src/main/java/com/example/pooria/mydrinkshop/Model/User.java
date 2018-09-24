package com.example.pooria.mydrinkshop.Model;

public class User {
    private String phone,address,name,birthdate,error_msg,avatar_Url;

    public User(){

    }

    public String getAvatar_Url() {
        return avatar_Url;
    }

    public void setAvatar_Url(String avatar_Url) {
        this.avatar_Url = avatar_Url;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getError_msg() {
        return error_msg;
    }

    public void setError_msg(String error_msg) {
        this.error_msg = error_msg;
    }
}

package com.coolkas01.hexavara;

public class User {
    private String username;
    private String address;
    private String email;
    private String token;
    private String photo;
    private String fullname;

    public String getUsername () {
        return username;
    }

    public void setUsername (String username) {
        this.username = username;
    }

    public String getAddress () {
        return address;
    }

    public void setAddress (String address) {
        this.address = address;
    }

    public String getEmail () {
        return email;
    }

    public void setEmail (String email) {
        this.email = email;
    }

    public String getToken () {
        return token;
    }

    public void setToken (String token) {
        this.token = token;
    }

    public String getPhoto () {
        return photo;
    }

    public void setPhoto (String photo) {
        this.photo = photo;
    }

    public String getFullname () {
        return fullname;
    }

    public void setFullname (String fullname) {
        this.fullname = fullname;
    }
}


package com.eea.eeaandroidapplication.modelclass;

public class CustomerLogin
{

    private Integer id;
    private String username;
    private String password;
    private int status;

    public CustomerLogin(){

    }

    public CustomerLogin(String username, String password){
        this.username  = username;
        this.password = password;
        this.status = 1;
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

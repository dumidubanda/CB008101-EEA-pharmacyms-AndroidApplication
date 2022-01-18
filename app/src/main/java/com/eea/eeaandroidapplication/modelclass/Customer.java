package com.eea.eeaandroidapplication.modelclass;

public class Customer
{

    private Integer id;
    private String name;
    private String email;
    private String mobile;
    private CustomerLogin customerLogin;


    public Customer() {
    }

    public Customer(String name, String email, String mobile, CustomerLogin customerLogin) {
        this.name = name;
        this.email = email;
        this.mobile = mobile;
        this.customerLogin = customerLogin;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public CustomerLogin getCustomerLogin() {
        return customerLogin;
    }

    public void setCustomerLogin(CustomerLogin customerLogin) {
        this.customerLogin = customerLogin;
    }
}

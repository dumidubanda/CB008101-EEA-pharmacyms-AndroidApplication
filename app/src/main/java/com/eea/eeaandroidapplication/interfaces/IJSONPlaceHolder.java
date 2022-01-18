package com.eea.eeaandroidapplication.interfaces;

import com.eea.eeaandroidapplication.modelclass.Customer;
import com.eea.eeaandroidapplication.modelclass.CustomerLogin;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface IJSONPlaceHolder
{



    @POST("savecustomer")
    Call<ResponseBody> saveCustomer(@Body Customer customer);

    @GET("getcustomerlogin/{email}")
    Call<CustomerLogin> checkCustomerLogin(@Path("email") String email);


}

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


//    @GET("users")
//    Call<List<User>> getUsers();
//
//    @GET("getbyid/{id}")
//    Call<Employee> getEmployee(@Path("id") int empId);
//
//    @POST("saveemployee")
//    Call<ResponseBody> saveEmployee(@Body Employee employee);
//
//    @PUT("updateemployee/{id}")
//    Call<ResponseBody> updateEmployee(@Path("id") int empId,@Body Employee employee);

    @DELETE("deleteemployee/{id}")
    Call<ResponseBody> deleteEmployee(@Path("id") int empId);

    @POST("savecustomer")
    Call<ResponseBody> saveCustomer(@Body Customer customer);

    @GET("getcustomerlogin/{email}")
    Call<CustomerLogin> checkCustomerLogin(@Path("email") String email);


}

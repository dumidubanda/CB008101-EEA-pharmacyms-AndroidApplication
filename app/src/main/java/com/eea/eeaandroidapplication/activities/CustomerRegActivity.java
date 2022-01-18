package com.eea.eeaandroidapplication.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.eea.eeaandroidapplication.R;
import com.eea.eeaandroidapplication.interfaces.IJSONPlaceHolder;
import com.eea.eeaandroidapplication.modelclass.Customer;
import com.eea.eeaandroidapplication.modelclass.CustomerLogin;

import at.favre.lib.crypto.bcrypt.BCrypt;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CustomerRegActivity extends AppCompatActivity
{

    private EditText txtname,txtemail,txtmobile,txtpasword;
    private Button btnregister;
    private static final String TAG = "CustomerRegActivity";
    private static final String BASE_URL = "http://192.168.1.4:8080";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_reg);

        txtname = findViewById(R.id.txt_creg_name);
        txtemail = findViewById(R.id.txt_creg_email);
        txtmobile = findViewById(R.id.txt_creg_mobile);
        txtpasword = findViewById(R.id.txt_creg_password);
        btnregister = findViewById(R.id.btn_creg_register);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IJSONPlaceHolder ijsonPlaceHolder = retrofit.create(IJSONPlaceHolder.class);

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name,email,mobile,password;
                name = txtname.getText().toString();
                email = txtemail.getText().toString();
                mobile = txtmobile.getText().toString();
                password = txtpasword.getText().toString();

                if(name.equals("") || email.equals("") || mobile.equals("") || password.equals("")){
                    Toast.makeText(CustomerRegActivity.this, "Please fill all the fields...", Toast.LENGTH_SHORT).show();
                    return;
                }
                String hashPassword = BCrypt.withDefaults().hashToString(12,password.toCharArray());
                CustomerLogin customerLogin = new CustomerLogin(email,hashPassword);
                Customer customer = new Customer(name,email,mobile,customerLogin);

                Call<ResponseBody> call = ijsonPlaceHolder.saveCustomer(customer);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(!response.isSuccessful()){
                            Toast.makeText(CustomerRegActivity.this, "Registration failed :"+response.code(), Toast.LENGTH_SHORT).show();
                            Log.e(TAG,"Response Error : "+response.code());
                            return;
                        }
                        Toast.makeText(CustomerRegActivity.this, "Successfully Registered...", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(CustomerRegActivity.this, "Registration failed : "+t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e(TAG,t.getMessage());
                    }
                });
            }
        });
    }
}
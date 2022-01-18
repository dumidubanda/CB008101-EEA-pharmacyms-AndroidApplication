package com.eea.eeaandroidapplication.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.eea.eeaandroidapplication.R;
import com.eea.eeaandroidapplication.interfaces.IJSONPlaceHolder;
import com.eea.eeaandroidapplication.modelclass.CustomerLogin;

import at.favre.lib.crypto.bcrypt.BCrypt;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CustomerLoginActivity extends AppCompatActivity
{

    private EditText txtuname, txtpass;
    private Button btnlogin;
    private TextView txtnewuserregister;
    private static final String TAG = "CustomerLoginActivity";
    private static final String BASE_URL = "http://192.168.1.4:8080";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_login);

        txtuname = findViewById(R.id.txt_clogin_name);
        txtpass = findViewById(R.id.txt_xlogin_pass);
        btnlogin = findViewById(R.id.btn_clogin_login);

        txtnewuserregister = findViewById(R.id.not_admin_login_link);

        txtnewuserregister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                //Load new activity
                Intent intent = new Intent(CustomerLoginActivity.this, CustomerRegActivity.class);
                startActivity(intent);
            }
        });




        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IJSONPlaceHolder ijsonPlaceHolder = retrofit.create(IJSONPlaceHolder.class);

        btnlogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String uname, pass;
                uname = txtuname.getText().toString();
                pass = txtpass.getText().toString();

                if (uname.equals("") || pass.equals(""))
                {
                    Toast.makeText(CustomerLoginActivity.this, "Please fill both fields...", Toast.LENGTH_SHORT).show();
                    return;
                }

                Call<CustomerLogin> call = ijsonPlaceHolder.checkCustomerLogin(uname);
                call.enqueue(new Callback<CustomerLogin>()
                {
                    @Override
                    public void onResponse(Call<CustomerLogin> call, Response<CustomerLogin> response)
                    {
                        if (!response.isSuccessful())
                        {
                            Toast.makeText(CustomerLoginActivity.this, "Invalid username or password, please try again...", Toast.LENGTH_SHORT).show();
                            Log.e(TAG, "Invalid Credentials : " + response.code());
                            return;
                        }
                        CustomerLogin customerLogin = response.body();
                        BCrypt.Result result = BCrypt.verifyer().verify(pass.toCharArray(), customerLogin.getPassword());
                        if (result.verified == false)
                        {
                            Toast.makeText(CustomerLoginActivity.this, "Invalid username or password, please try again...", Toast.LENGTH_SHORT).show();
                            Log.e(TAG, "Invalid Password");
                            return;
                        }
                        else if (customerLogin.getStatus() != 1)
                        {
                            Toast.makeText(CustomerLoginActivity.this, "Your account has been disabled...", Toast.LENGTH_SHORT).show();
                            Log.e(TAG, "Locked account");
                            return;
                        }
                        else
                        {
                            Toast.makeText(CustomerLoginActivity.this, "Valid Customer", Toast.LENGTH_SHORT).show();

                            //Load new activity
                            Intent intent = new Intent(CustomerLoginActivity.this, ActivityPharmaItems.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<CustomerLogin> call, Throwable t)
                    {
                        Toast.makeText(CustomerLoginActivity.this, "Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e(TAG, t.getMessage());
                    }
                });
            }
        });

    }
}
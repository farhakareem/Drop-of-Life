package com.example.farha.dropoflife;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    private EditText username,userpass,useremail,userphone;
    private Button register,signin;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        username=(EditText)findViewById(R.id.name);
        userpass=(EditText)findViewById(R.id.password);
        useremail=(EditText)findViewById(R.id.email);
        userphone=(EditText)findViewById(R.id.phone);
        register=(Button)findViewById(R.id.accountreg);
        signin=(Button)findViewById(R.id.signin);
        mAuth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    //upload data to database
                    String user_email=useremail.getText().toString().trim();
                    String user_password=userpass.getText().toString().trim();
                    mAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(RegistrationActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
                            }
                            else
                            { Toast.makeText(RegistrationActivity.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }

            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
            }
        });
    }
    private Boolean validate()
    {
        Boolean result=false;

        String name=username.getText().toString();
        String pass=userpass.getText().toString();
        String email=useremail.getText().toString();
        String phone=userphone.getText().toString();

        if(name.isEmpty()||pass.isEmpty()||email.isEmpty()||phone.isEmpty())
        {
            Toast.makeText(this,"Please enter all the details",Toast.LENGTH_SHORT).show();
        }
        else
        {
            result=true;
        }
        return result;
    }
}

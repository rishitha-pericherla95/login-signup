package com.example.project1;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.basgeekball.awesomevalidation.AwesomeValidation;

import java.util.HashMap;


import static com.basgeekball.awesomevalidation.ValidationStyle.BASIC;

public class SignupActivity extends AppCompatActivity {
    private Button m_btnmainSignup;
    private EditText edtsUsername;
    private EditText edtsPassword;
    private EditText edtsPassword2;
    private EditText edtsEmail;
    private EditText edtsPhone;
    AwesomeValidation awesomeValidation;

    HashMap<String, String> credentials = new HashMap<>();
    Context context2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        awesomeValidation = new AwesomeValidation(BASIC);
        context2 = getApplicationContext();
        edtsUsername = findViewById(R.id.edtsUsername);
        edtsEmail = findViewById(R.id.edtsEmail);
        edtsPhone = findViewById(R.id.edtsPhone);
        edtsPassword2 = findViewById(R.id.edtsPassword2);
        m_btnmainSignup = findViewById(R.id.btnmainSignup);
        edtsPassword = findViewById(R.id.edtsPassword);
        //performing the validations using the awesome validation
        //username should have minimum length of 3 and maximum of 15 and can have alphabets(a-z),numbers(0-9),- and _
        awesomeValidation.addValidation(SignupActivity.this, R.id.edtsUsername, "[a-z0-9_-]{3,15}", R.string.err_name);
        //password should be of length 8 or more- should have alphabets,numbers,special characters
        String regexPassword = "(?=.*[a-z])(?=.*[A-Z])(?=.*[\\d])(?=.*[~`!@#\\$%\\^&\\*\\(\\)\\-_\\+=\\{\\}\\[\\]\\|\\;:\"<>,./\\?]).{8,}";
        //mobile number should be of length 10 and should only have 0-9 numbers
        String MobilePattern = "[0-9]{10}";
        awesomeValidation.addValidation(SignupActivity.this, R.id.edtsPassword, regexPassword, R.string.err_password);
        // checks if the passwords match
        awesomeValidation.addValidation(SignupActivity.this, R.id.edtsPassword2, R.id.edtsPassword, R.string.err_password_confirmation);
        awesomeValidation.addValidation(SignupActivity.this, R.id.edtsEmail, android.util.Patterns.EMAIL_ADDRESS, R.string.err_email);
        awesomeValidation.addValidation(SignupActivity.this, R.id.edtsPhone, MobilePattern, R.string.err_number);
        if ((HashMap<String, String>) getIntent().getSerializableExtra("data") != null)
        {
            credentials =  (HashMap<String, String>) getIntent().getSerializableExtra("data");

        }
     //When the signup button is clicked
        m_btnmainSignup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                    submitForm();

            }
        });

    }

    private void submitForm() {

        if(awesomeValidation.validate()) {
            String suname="";
            String spassword = "";
            suname = edtsUsername.getText().toString();
            spassword = edtsPassword.getText().toString();
            //checking if the entered username is already present in the hashmap
            boolean duplicateuname = credentials.containsKey(suname);
            if(!duplicateuname) {
                credentials.put(suname, spassword);
                Intent login = new Intent(context2, LoginActivity.class);
                login.putExtra("data", credentials);
                startActivity(login);
            }
            else{
                Toast.makeText(context2,"Username is already taken!",Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(context2,"Try Again",Toast.LENGTH_SHORT).show();
        }
    }
}

package com.example.project1;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {
    private Button m_btnLogin;
    private EditText edtUsername;
    private EditText edtPassword;
    private Button m_btnSignup;
   HashMap<String, String> credentials = new HashMap<String, String>();
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        context = getApplicationContext();
        edtUsername = findViewById(R.id.edtUsername);
        m_btnLogin = findViewById(R.id.btnLogin);
        m_btnSignup = findViewById(R.id.btnSignup);
        edtPassword = findViewById(R.id.edtPassword);

        if ((HashMap<String, String>) getIntent().getSerializableExtra("data") != null)
        {
            credentials =  (HashMap<String, String>) getIntent().getSerializableExtra("data");
        }
        //if login button is clicked
        m_btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String luname="";
                String lpassword = "";

                luname = edtUsername.getText().toString();
                lpassword = edtPassword.getText().toString();
                // checking if the username is present in the hashmap and if the corresponding password is matching then go to welcome activity
                boolean isKeyPresent = credentials.containsKey(luname);
                String value = credentials.get(luname);
                if(isKeyPresent && value.equals(lpassword)){
                    Intent welcome = new Intent(context, WelcomeActivity.class);
                    // sending the username to the welcome activity
                    welcome.putExtra("usertext",luname);
                    startActivity(welcome);
                }
                else{
                    //incase the username and password do not match, password field is cleared and a toast message is shown
                    edtPassword.getText().clear();
                    Toast toast = Toast.makeText(context, "Invalid Username or Password", Toast.LENGTH_SHORT);
                    toast.show();
                }


            }
        });
        // if signup button is clicked
        m_btnSignup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent signup = new Intent(context, SignupActivity.class);
                signup.putExtra("data", credentials);
                startActivity(signup);
            }
        });

    }
}

package com.example.orented;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SignInPage extends AppCompatActivity {
    DBAdapter dba;
    EditText etUsername, etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_page);

        dba = new DBAdapter(getApplicationContext());

        etUsername = findViewById(R.id.inp_username);
        etPassword = findViewById(R.id.inp_password);

        Button signInButton = findViewById(R.id.sign_in_button);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentus = new Intent(v.getContext(), MainActivity.class);

                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                ArrayList<User> allUsers = dba.getAllUser();

                for(User u: allUsers){
                    Log.d("DB Check", u.getId() + " - " + u.getUsername() + " - " + u.getPassword());
                }

                boolean valid = false;
                for(User u : allUsers){
                    if(u.getUsername().equals(username) && u.getPassword().equals(password)){
                        valid = true;
                    }
                }

                if(valid){
                    intentus.putExtra("inpusername", username);
                    startActivity(intentus);

                    etUsername.setText("");
                    etPassword.setText("");
                }
                else {
                    Toast.makeText(getApplicationContext(), "Invalid username or password!", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}

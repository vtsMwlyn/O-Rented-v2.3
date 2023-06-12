package com.example.orented;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class SignUpPage extends AppCompatActivity {
    DBAdapter dba;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_page);

        dba = new DBAdapter(getApplicationContext());

        Button signUpButton = findViewById(R.id.sign_up_button);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText etUsername = findViewById(R.id.inp_username);
                EditText etPassword = findViewById(R.id.inp_password);

                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();

                User newUser = new User(generateId(), username, password, "name", "2000-01-01", "Somewhere", "08xxxxxxxxxx", username + "@email.com");
                dba.insertUsers(newUser);

                Toast.makeText(getApplicationContext(), "Successfully created an account!", Toast.LENGTH_SHORT).show();

                Intent intentus = getIntent();
                setResult(RESULT_OK, intentus);
                finish();
            }
        });
    }

    public String generateId(){
        String userIdCandidate = "";
        do{
            userIdCandidate = "";
            userIdCandidate += "U";
            for(int i = 0; i < 3; i++){
                int n = (int) (0 + Math.random() * 10);
                userIdCandidate += n;
            }
        }
        while(!idUnique(userIdCandidate));

        return userIdCandidate;
    }

    public boolean idUnique(String idCandidate){
        dba = new DBAdapter(getApplicationContext());
        ArrayList<User> allUsers = dba.getAllUser();

        for(User u : allUsers){
            if(u.equals(idCandidate)){
                return false;
            }
        }

        return true;
    }
}

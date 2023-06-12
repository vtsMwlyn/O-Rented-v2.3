package com.example.orented;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome_page);

        //this.deleteDatabase("orentedDB.db");

        Button signUpButton = findViewById(R.id.sign_up_button);
        Button signInButton = findViewById(R.id.sign_in_button);

        signInButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intentus = new Intent(v.getContext(), SignInPage.class);
                startActivity(intentus);
            }
        });

        signUpButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intentus = new Intent(v.getContext(), SignUpPage.class);
                startActivity(intentus);
            }
        });
    }
}

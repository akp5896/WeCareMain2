package com.example.wecaremain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class userSignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up);
        EditText etNewName;
        EditText etNewPass;
        Button btnCreate;
        etNewName = findViewById(R.id.etNewName);
        etNewPass = findViewById(R.id.etNewPass);
        btnCreate = findViewById(R.id.btnCreate);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseUser user = new ParseUser();
                user.setUsername(etNewName.getText().toString());
                user.setPassword(etNewPass.getText().toString());

                user.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e != null)
                        {
                            Toast.makeText(userSignUp.this, "Cannot create account", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Toast.makeText(userSignUp.this, "User created!", Toast.LENGTH_SHORT).show();
//                        Intent i = new Intent(userSignUp.this, LoginActivity.class);
//                        startActivity(i);
                        finish();
                    }
                });
            }
        });

        // Create the ParseUser
        ParseUser user = new ParseUser();
// Set core properties
        user.setUsername("joestevens");
        user.setPassword("secret123");
        user.setEmail("email@example.com");
// Set custom properties
        user.put("phone", "650-253-0000");
// Invoke signUpInBackground
        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    // Hooray! Let them use the app now.
                } else {
                    // Sign up didn't succeed. Look at the ParseException
                    // to figure out what went wrong
                }
            }
        });
    }
}


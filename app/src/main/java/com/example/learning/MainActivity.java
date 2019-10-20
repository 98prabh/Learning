package com.example.learning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    public EditText emailId, password;
    Button btSignup, btlogin;
    FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get instance of firebase auth
        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btSignup = findViewById(R.id.button1);
        btlogin = findViewById(R.id.button2);

        btSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailId.getText().toString();
                String pass = password.getText().toString();

                if(email.isEmpty())
                {
                    emailId.setError("Provide Email Address");
                    emailId.requestFocus();
                }
                else if(pass.isEmpty())
                {
                    password.setError("Enter Password");
                    password.requestFocus();
                }
                else if(email.isEmpty() && pass.isEmpty())
                {
                    Toast.makeText(MainActivity.this,"Enter Details", Toast.LENGTH_SHORT).show();
                }
                else if(!(email.isEmpty() && pass.isEmpty()))
                {
                    mFirebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!(task.isSuccessful()))
                            {
                                Toast.makeText(MainActivity.this,"Sign Up Unsuccessful ", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                startActivity(new Intent(MainActivity.this , HomeActivity.class));

                            }
                        }
                    });
                }

                else
                {
                    Toast.makeText(MainActivity.this,"Error Ocurred!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }


}

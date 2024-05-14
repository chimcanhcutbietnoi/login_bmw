package com.example.login_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.login_app.databinding.ActivitySigninBinding;
import com.example.login_app.databinding.ActivitySignupBinding;

public class SigninActivity extends AppCompatActivity {

    ActivitySigninBinding binding;
    SQLiteConnector connector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySigninBinding.inflate(getLayoutInflater());
        EdgeToEdge.enable(this);
        setContentView(binding.getRoot());

        connector = new SQLiteConnector(this);

        binding.signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = binding.signinUsername.getText().toString();
                String password = binding.signinPassword.getText().toString();

                if (username.equals("") || password.equals(""))
                    Toast.makeText(SigninActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();
                else {
                    if (connector.checkUser(username, password)){
                        Toast.makeText(SigninActivity.this, "Sign In success!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("username", username);
                        startActivity(intent);
                    } else
                        Toast.makeText(SigninActivity.this, "The account does not exist, please create a new account.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);
            }
        });
    }
}
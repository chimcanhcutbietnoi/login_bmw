package com.example.login_app;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        // Nhận tên người dùng từ Intent
        String username = getIntent().getStringExtra("username");

        // Hiển thị lời chào có tên người dùng trên TextView
        TextView welcomeMessageTextView = findViewById(R.id.welcomeMessageTextView);
        String greetingMessage = "Welcome, " + username + "!";
        welcomeMessageTextView.setText(greetingMessage);

        Button logoutButton = findViewById(R.id.logoutButton);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xóa thông tin đăng nhập (ví dụ: thông tin tên người dùng)
                // Đặt lại giá trị của username thành null hoặc empty string
                // username = null;

                // Chuyển hướng về màn hình đăng nhập
                Intent intent = new Intent(MainActivity.this, SigninActivity.class);
                startActivity(intent);
                finish(); // Kết thúc MainActivity để ngăn người dùng quay lại bằng nút back
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
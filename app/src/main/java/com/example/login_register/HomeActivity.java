package com.example.login_register;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView tvUsername = findViewById(R.id.tvUsername);
        String username = getIntent().getStringExtra("username");
        tvUsername.setText(username);
    }
}

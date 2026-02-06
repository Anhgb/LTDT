package com.example.login_register;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

public class loginActivity extends AppCompatActivity {

    private TextInputLayout tilUser, tilPass;
    private MaterialButton btnLogin;
    private ProgressBar progressBar;
    private TextView tvToReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_main);

        tilUser = findViewById(R.id.tilUser);
        tilPass = findViewById(R.id.tilPass);
        btnLogin = findViewById(R.id.btnLogin);
        progressBar = findViewById(R.id.progressBarLogin);
        tvToReg = findViewById(R.id.tvToRegister);

        tvToReg.setOnClickListener(v -> {
            startActivity(new Intent(this, registerActivity.class));
        });

        btnLogin.setOnClickListener(v -> handleLogin());
    }

    private void handleLogin() {
        if (tilUser.getEditText() == null || tilPass.getEditText() == null) return;

        String inputUser = tilUser.getEditText().getText().toString().trim();
        String inputPass = tilPass.getEditText().getText().toString().trim();

        tilUser.setError(null);
        tilPass.setError(null);

        if (inputUser.isEmpty() || inputPass.isEmpty()) {
            Toast.makeText(this, "Vui lòng nhập đầy đủ", Toast.LENGTH_SHORT).show();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        btnLogin.setEnabled(false);

        new Handler().postDelayed(() -> {
            SharedPreferences pref = getSharedPreferences("UserPrefs", MODE_PRIVATE);
            String savedUser = pref.getString("username", "");
            String savedPass = pref.getString("password", "");

            progressBar.setVisibility(View.GONE);
            btnLogin.setEnabled(true);

            if (inputUser.equals(savedUser) && inputPass.equals(savedPass) && !savedUser.isEmpty()) {
                Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, HomeActivity.class);
                intent.putExtra("username", savedUser);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
            }
        }, 1500);
    }
}

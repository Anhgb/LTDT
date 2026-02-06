package com.example.login_register;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

public class registerActivity extends AppCompatActivity {

    private TextInputLayout tilUser, tilPass, tilConfirm;
    private MaterialButton btnReg;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity_main);

        tilUser = findViewById(R.id.tilRegUsername);
        tilPass = findViewById(R.id.tilRegPassword);
        tilConfirm = findViewById(R.id.tilRegConfirm);
        btnReg = findViewById(R.id.btnRegister);
        progressBar = findViewById(R.id.progressBarReg);

        btnReg.setOnClickListener(v -> handleRegister());
    }

    private void handleRegister() {
        if (tilUser.getEditText() == null || tilPass.getEditText() == null || tilConfirm.getEditText() == null) return;

        String user = tilUser.getEditText().getText().toString().trim();
        String pass = tilPass.getEditText().getText().toString().trim();
        String confirm = tilConfirm.getEditText().getText().toString().trim();

        tilUser.setError(null);
        tilPass.setError(null);
        tilConfirm.setError(null);

        if (user.isEmpty()) {
            tilUser.setError("Vui lòng nhập tên đăng nhập");
            return;
        }
        if (pass.length() < 6) {
            tilPass.setError("Mật khẩu phải ít nhất 6 ký tự");
            return;
        }
        if (!pass.equals(confirm)) {
            tilConfirm.setError("Mật khẩu xác nhận không khớp");
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        btnReg.setEnabled(false);

        new Handler().postDelayed(() -> {
            SharedPreferences pref = getSharedPreferences("UserPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.putString("username", user);
            editor.putString("password", pass);
            editor.apply();

            progressBar.setVisibility(View.GONE);
            btnReg.setEnabled(true);
            Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
            finish();
        }, 2000);
    }
}

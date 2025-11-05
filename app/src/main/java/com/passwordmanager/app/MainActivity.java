package com.passwordmanager.app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Password> passwords;
    private LinearLayout passwordList;
    private EditText titleInput, usernameInput, passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        passwords = new ArrayList<>();
        
        // Tạo layout động
        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setPadding(32, 32, 32, 32);
        
        // Title
        TextView title = new TextView(this);
        title.setText("Quản Lý Mật Khẩu");
        title.setTextSize(24);
        title.setPadding(0, 0, 0, 32);
        mainLayout.addView(title);
        
        // Input fields
        titleInput = new EditText(this);
        titleInput.setHint("Tiêu đề (VD: Gmail, Facebook)");
        mainLayout.addView(titleInput);
        
        usernameInput = new EditText(this);
        usernameInput.setHint("Tên đăng nhập");
        mainLayout.addView(usernameInput);
        
        passwordInput = new EditText(this);
        passwordInput.setHint("Mật khẩu");
        mainLayout.addView(passwordInput);
        
        // Add button
        Button addButton = new Button(this);
        addButton.setText("Thêm Mật Khẩu");
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addPassword();
            }
        });
        mainLayout.addView(addButton);
        
        // Password list
        passwordList = new LinearLayout(this);
        passwordList.setOrientation(LinearLayout.VERTICAL);
        passwordList.setPadding(0, 32, 0, 0);
        mainLayout.addView(passwordList);
        
        setContentView(mainLayout);
    }
    
    private void addPassword() {
        String title = titleInput.getText().toString();
        String username = usernameInput.getText().toString();
        String password = passwordInput.getText().toString();
        
        if (title.isEmpty() || username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return;
        }
        
        Password pwd = new Password(title, username, password);
        passwords.add(pwd);
        
        displayPassword(pwd);
        
        // Clear inputs
        titleInput.setText("");
        usernameInput.setText("");
        passwordInput.setText("");
        
        Toast.makeText(this, "Đã thêm mật khẩu!", Toast.LENGTH_SHORT).show();
    }
    
    private void displayPassword(Password pwd) {
        TextView item = new TextView(this);
        item.setText("📱 " + pwd.title + "\n👤 " + pwd.username + "\n🔒 " + pwd.password);
        item.setPadding(16, 16, 16, 16);
        item.setTextSize(16);
        item.setBackgroundColor(0xFFE0E0E0);
        
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(0, 0, 0, 16);
        item.setLayoutParams(params);
        
        passwordList.addView(item);
    }
    
    private class Password {
        String title, username, password;
        
        Password(String title, String username, String password) {
            this.title = title;
            this.username = username;
            this.password = password;
        }
    }
}

package com.example.inclusiridebicisyscooter.Views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.inclusiridebicisyscooter.DAO.UserDBHelper;
import com.example.inclusiridebicisyscooter.Models.User;
import com.example.inclusiridebicisyscooter.R;

public class ActivityLogin extends AppCompatActivity {

    private EditText txtUsername, txtPassword;
    private CheckBox rememberMeCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
        rememberMeCheckBox = findViewById(R.id.saveSesion);
    }

    public void authenticateUser(View view) {
        String username = txtUsername.getText().toString().trim();
        String password = txtPassword.getText().toString().trim();

        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            UserDBHelper dbHelper = new UserDBHelper(this);
            User user = dbHelper.validateCredentials(username, password);

            if (user != null) {
                if (rememberMeCheckBox.isChecked()) {
                    saveSharedPreference(user.getId(), user.getUsername(), user.getPassword());
                }
                Toast.makeText(this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, ActivityHome.class);
                intent.putExtra("usuario", user);
                startActivity(intent);
                finish();
            } else {
                clearFields();
                Toast.makeText(this, "Nombre de usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error al autenticar: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void saveSharedPreference(long id, String username, String password) {
        try {
            SharedPreferences sp = getSharedPreferences("credentials", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putLong("id",id);
            editor.putString("username", username);
            editor.putString("password", password);
            editor.apply();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void clearFields(){
        txtUsername.setText("");
        txtPassword.setText("");
        txtUsername.requestFocus();
    }
}